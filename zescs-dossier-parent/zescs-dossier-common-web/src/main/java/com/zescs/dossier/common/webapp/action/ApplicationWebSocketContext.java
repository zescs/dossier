package com.zescs.dossier.common.webapp.action;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

/**
 * 
 * @ClassName: ApplicationServletContext
 * @Description: TODO()
 * @author 郑建平
 * @date 2015年1月9日 下午11:06:29
 *
 */
public class ApplicationWebSocketContext {
	private static Map<String, Session> map = new HashMap<String, Session>();

	public Session get(String userId) {
		return map.get(userId);
	}

	public void set(String userId, Session session) {
		map.put(userId, session);
	}
}
