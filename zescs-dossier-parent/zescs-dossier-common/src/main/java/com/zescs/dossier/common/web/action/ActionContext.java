package com.zescs.dossier.common.web.action;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName: ActionContext
 * @Description: TODO(存储每次访问的HttpServletRequest)
 * @author 郑建平
 * @date 2015年1月9日 下午10:09:29
 *
 */
public final class ActionContext {
	/**
	 * 每次访问的对象集合
	 */
	private static ThreadLocal<HttpServletRequest> requests = new ThreadLocal<HttpServletRequest>();

	private ActionContext() {
	}

	/**
	 * 
	 * @Title: get
	 * @Description: TODO(获取当前请求的request对象)
	 * @return
	 */
	public static synchronized HttpServletRequest get() {
		return requests.get();
	}

	/**
	 * 
	 * @Title: put
	 * @Description: TODO(加入当前请求的request)
	 * @param request
	 */
	public static synchronized void put(HttpServletRequest request) {
		requests.set(request);
	}

	/**
	 * 
	 * @Title: remove
	 * @Description: TODO(删除当前请求的request对象)
	 */
	public static synchronized void remove() {
		requests.remove();
	}

}
