package com.zescs.dossier.common.webapp.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * 
 * @ClassName: LoginUserContext
 * @Description: TODO(登录用户存储)
 * @author 郑建平
 * @date 2015年1月9日 下午9:15:45
 *
 */
public final class LoginUserContext {
	private static Map<String, HttpSession> users = new HashMap<String, HttpSession>();

	/**
	 * 存储系统已经登录的用户信息，用于用户多台机器登录，下线操作
	 */
	private LoginUserContext() {

	}

	/**
	 * 
	 * @Title: getUsers
	 * @Description: TODO(获取所有的用户)
	 * @return
	 */
	public static Map<String, HttpSession> getUsers() {
		return users;
	}

	/**
	 * 
	 * @Title: put
	 * @Description: TODO(加入用户 信息)
	 * @param userName
	 * @param session
	 */
	public static synchronized void put(String userName, HttpSession session) {
		getUsers().put(userName, session);
	}

	/**
	 * 
	 * @Title: remove
	 * @Description: TODO(移除用户信息)
	 * @param userName
	 */
	public static synchronized void remove(String userName) {
		getUsers().remove(userName);
	}

	/**
	 * 
	 * @Title: get
	 * @Description: TODO(获取session)
	 * @param userName
	 * @return
	 */
	public static synchronized HttpSession get(String userName) {
		return getUsers().get(userName);
	}
}
