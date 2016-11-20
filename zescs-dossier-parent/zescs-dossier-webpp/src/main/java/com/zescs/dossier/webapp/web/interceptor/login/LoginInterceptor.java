package com.zescs.dossier.webapp.web.interceptor.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zescs.dossier.config.R;
import com.zescs.dossier.model.user.bean.LoginUser;
import com.zescs.dossier.webapp.web.interceptor.login.annotation.LoginPass;

/**
 * 
 * @ClassName: LoginInterceptor
 * @Description: TODO(登录拦截器)
 * @author john
 * @date 2014年8月6日 下午1:00:03
 * 
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	private final Logger LOGGER = Logger.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Boolean flag = true;
		String requestType = request.getHeader("X-Requested-With");
		// 检测目标类型
		if (handler instanceof HandlerMethod) {
			// 转换当前类型
			HandlerMethod type = (HandlerMethod) handler;
			// 获取目标类上的注解
			LoginPass loginCheck = type.getBeanType().getAnnotation(
					LoginPass.class);
			if (loginCheck != null) {
				// 检测用户是否输入了提示值
				String value = loginCheck.value();
				if (value != null && value.trim().length() != 0) {
					LOGGER.info(value);
				}
			} else {
				// 获取目标方法上的注解
				loginCheck = type.getMethodAnnotation(LoginPass.class);
				if (loginCheck != null) {
					// 检测用户是否输入了提示值
					String value = loginCheck.value();
					if (value != null && value.trim().length() != 0) {
						LOGGER.info(value);
					}
				} else {
					// 检测session中是否有user对象
					LoginUser user = (LoginUser) request
							.getSession()
							.getAttribute(R.SystemConfig.LOGIN_USER_ADMIN);
					if (user == null) {
						flag = false;
						// 如果调转到登录超时的界面提醒用户重新登录
						if(requestType!=null){
							response.sendRedirect(request.getContextPath()+"/AJAXLoginOut");
						}else{
							response.sendRedirect(request.getContextPath()+"/quit/logout");
						}
					}
				}
			}
		}
		return flag;
	}
}
