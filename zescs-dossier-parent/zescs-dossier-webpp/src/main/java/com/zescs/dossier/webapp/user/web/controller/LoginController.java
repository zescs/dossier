package com.zescs.dossier.webapp.user.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zescs.dossier.common.web.action.ActionContext;
import com.zescs.dossier.common.web.encrypt.rsa.PublicKeyMap;
import com.zescs.dossier.common.web.encrypt.rsa.RSAUtils;
import com.zescs.dossier.common.web.json.Message;
import com.zescs.dossier.config.R;
import com.zescs.dossier.core.log.facade.LoginInfoFacade;
import com.zescs.dossier.core.permissions.facade.RoleFacade;
import com.zescs.dossier.core.user.facade.UserFacade;
import com.zescs.dossier.model.log.bean.LoginInfo;
import com.zescs.dossier.model.permissions.bean.Role;
import com.zescs.dossier.model.permissions.domain.Level;
import com.zescs.dossier.model.user.bean.LoginUser;
import com.zescs.dossier.model.user.bean.User;
import com.zescs.dossier.model.user.domain.OnlineState;
import com.zescs.dossier.model.user.domain.Status;
import com.zescs.dossier.webapp.web.controller.BaseController;
import com.zescs.dossier.webapp.web.interceptor.login.annotation.LoginPass;
import com.zescs.dossier.webapp.web.security.GoodsUserDetailsService;
import com.zescs.dossier.webapp.web.util.WebUtils;

@Controller
@Scope(R.App.SCOPE_PROTOTYPE)
@SessionAttributes({ R.SystemConfig.LOGIN_USER_ADMIN,R.ProJectConfig.SECURITY_CODE })
public class LoginController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	@Reference
	private UserFacade userFacade;
	@Autowired
	private GoodsUserDetailsService goodsUserDetailsService;
	@Reference
	private RoleFacade roleFacade;
	@Reference
	private LoginInfoFacade loginInfoFacade;
	
	@LoginPass
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String login(Model model) {
		PublicKeyMap map = RSAUtils.getPublicKeyMap();
		model.addAttribute(R.DigestConfig.MODULUS, map.getModulus());
		model.addAttribute(R.DigestConfig.EXPONENT, map.getExponent());
		return "login/login";
	}

	@RequestMapping(value =R.Path.Mapping.LOGIN_ACTION, method = RequestMethod.POST)
	@ResponseBody
	@LoginPass
	public Message login(
			Model model, 
			User user, 
			@ModelAttribute(R.ProJectConfig.SECURITY_CODE) String sessionCode,
			@RequestParam("securityCode") String securityCode,
			@RequestParam(value = "state", required = true) Boolean state,
			HttpServletRequest request, HttpServletResponse response) {
		User user_login = userFacade.findByUserName(user.getUserName());
		setFlag(false);
		//验证码检查
		if (!(securityCode.toLowerCase()).equals(sessionCode)) {
			setMessage(WebUtils.getMessage(R.MessageKey.GLOBAL_MESSAGE_ADMIN_LOGIN_ERROR_SERURITY_CODE));
			return Message.newInstance(getFlag(),getMessage());
		}
		//用户检查
		if (user_login == null) {
			setMessage(WebUtils.getMessage(R.MessageKey.GLOBAL_MESSAGE_ADMIN_LOGIN_ERROR_USER_NOTEXISTS));
			return Message.newInstance(getFlag(),getMessage());
		}
		//密码检查
		//解密 在加密
		user.setPassword(RSAUtils.decryptStringByJs(user.getPassword()));
		if (!BCrypt.checkpw(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()), user_login.getPassword())) {
			setMessage(WebUtils.getMessage(R.MessageKey.GLOBAL_MESSAGE_ADMIN_LOGIN_ERROR_PASSWORD));
			return Message.newInstance(getFlag(),getMessage());
		}
		//冻结检查
		if(user_login.getStatus()==Status.FREEZE){
			setMessage(WebUtils.getMessage(R.MessageKey.MESSAGE_ADMIN_LOGIN_ERROR_USER_FREEZE));
			return Message.newInstance(getFlag(),getMessage());
		}
		//检测同一浏览器是否两次登录
		//是否分配角色
		Role role = roleFacade.findByUserIdAndLevel(user_login.getUserId(),Level.MAIN);
		if(role==null){
			setMessage("用户未设置主要角色，请联系管理员为用户分配主要角色");
			return Message.newInstance(getFlag(),getMessage());
		}
		//插入日志信息
		loginInfoFacade.add(new LoginInfo(user_login.getUserId(),WebUtils.getIpAddr(ActionContext.get())));
		//用户当前登录ip 在线状态 
		userFacade.updateUserOnlineStatus(user_login.getUserId(),OnlineState.ONLINE);
		setMessage(WebUtils.getMessage(R.MessageKey.ADMIN_LOGIN_SUCCESS_MESSAGE));
		LoginUser loginUser = new LoginUser(user_login.getUserId(), user_login.getUserName(),
				user_login.getUserName(), user_login.getOrganizationId(),user_login.getOrganization().getBranch(),role.getPosition().getDisplayName(), role.getRoleId(), null);
		model.addAttribute(R.SystemConfig.LOGIN_USER_ADMIN, loginUser);
		// Security设置
		UserDetails userDetails = goodsUserDetailsService.loadUserByUsername(user_login.getUserName());
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),userDetails.getAuthorities());
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		ActionContext.get().getSession().setAttribute(R.App.SPRING_SECURITY_CONTEXT,securityContext);
		//cookie设置
		// 写入cookie
		try {
			new Thread(new Runnable() {
				@Override
				public void run() {
					// 获取当前cookie
					Cookie currCookie = WebUtils.getCookie(request,
							R.ProJectConfig.USER_COOKIE_NAME);
					if (state) {
						Boolean cookieFlag = false;
						// 检测状态
						if (currCookie == null) {// 客户端用户第一次登陆
							cookieFlag = true;
						} else {
							String cookieUserName = currCookie.getValue();
							if (cookieUserName != null&& !cookieUserName.equals(user.getUserName())) {// 客户端用户登陆用户不一致，就要更换用户
								cookieFlag = true;
							}
						}
						// 写入cookie
						if (cookieFlag) {
							Cookie cookie = new Cookie(
									R.ProJectConfig.USER_COOKIE_NAME,
									user.getUserName());
							cookie.setMaxAge(604800);
							cookie.setPath("/");
							response.addCookie(cookie);
						}
					} else {
						Cookie cookie = new Cookie(
								R.ProJectConfig.USER_COOKIE_NAME, null);
						cookie.setMaxAge(-1);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
			}).start();
		} catch (Exception e) {
			LOG.error("保存cookie失败::" + e.getMessage());
		}
		return Message.newInstance(true, getMessage());
	}

	@RequestMapping(value = R.Path.Mapping.MAIN, method = RequestMethod.GET)
	public String main() {
		return "main/main";
	}

	@RequestMapping(value = R.Path.Mapping.WELCOME, method = RequestMethod.GET)
	public String welcome() {
		return "welcome/welcome";
	}

	@RequestMapping(value = R.Path.Mapping.LOGIN_OUT, method = RequestMethod.GET)
	@LoginPass
	public String loginOut(SessionStatus sessionStatus, HttpSession session) {
		LoginUser user = (LoginUser) session.getAttribute(R.SystemConfig.LOGIN_USER_ADMIN);
		BoundHashOperations<String, String, String> bs = null;
		// 将该用户写入缓存
		try {
			bs = getRedisTemplate().boundHashOps(R.Cache.Key.DOSSIER_USER);
			bs.delete(user.getUserName());
			LOG.info("redis:成功从缓存中删除用户信息");
		} catch (Exception e) {
			LOG.error("redis:从缓存中删除用户信息失败::" + e.getMessage());
		}
		if (user != null && user.getUserId() != null) {
			session.invalidate();
			sessionStatus.setComplete();
		}
		return "redirect:/login.action";
	}
	
	@RequestMapping(value = "/quit/logout", method = RequestMethod.GET)
	@LoginPass
	public String logout(HttpSession session) {
		LoginUser user = (LoginUser) session.getAttribute(R.SystemConfig.LOGIN_USER_ADMIN);
		BoundHashOperations<String, String, String> bs = null;
		// 将该用户写入缓存
		try {
			bs =getRedisTemplate().boundHashOps(R.Cache.Key.DOSSIER_USER);
			bs.delete(user.getUserName());
			LOG.info("redis:成功从缓存中删除用户信息");
		} catch (Exception e) {
			LOG.error("redis:从缓存中删除用户信息失败::" + e.getMessage());
		}
		return "login/logout";
	}
}
