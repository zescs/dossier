package com.zescs.dossier.webapp.web.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zescs.dossier.core.permissions.facade.RoleFacade;
import com.zescs.dossier.core.user.facade.UserFacade;
import com.zescs.dossier.model.permissions.bean.Role;

/**
 * Description: <类功能描述>. <br>
 * <p>
 * <使用说明>
 * </p>
 * Makedate:2014年9月4日 下午4:58:33
 * 
 * @author Administrator
 * @version V1.0
 */
public class GoodsUserDetailsService implements UserDetailsService {
	private static final Logger logger = Logger.getLogger(GoodsUserDetailsService.class);
	@Reference
	private UserFacade userFacade;
	@Reference
	private RoleFacade roleFacade;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("GoodsUserDetailsService loadUserByUsername");
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		// 获取用户
		com.zescs.dossier.model.user.bean.User user = userFacade.findByUserName(username);
		// 获取该用户的所有权限
		List<Role> role = roleFacade.queryUserRoles(user.getUserId());
		for (Role r : role) {
			authorities.add(new SimpleGrantedAuthority(r.getPosition().getSecurityValue()));
		}
		return new User(user.getUserName(), user.getPassword(), true, true, true, true, authorities);
	}

}
