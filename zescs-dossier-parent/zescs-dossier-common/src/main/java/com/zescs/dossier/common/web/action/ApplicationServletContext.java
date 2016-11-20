package com.zescs.dossier.common.web.action;

import javax.servlet.ServletContext;

/**
 * 
 * @ClassName: ApplicationServletContext
 * @Description: TODO()
 * @author 郑建平
 * @date 2015年1月9日 下午11:06:29
 *
 */
public class ApplicationServletContext {
	/**
	 * 
	 */
	private static ServletContext servletContext;

	private ApplicationServletContext() {
	}

	public static synchronized ServletContext getServletContext() {
		return servletContext;
	}

	public static synchronized void setServletContext(ServletContext servletContext) {
		ApplicationServletContext.servletContext = servletContext;
	}

	/**
	 * 设置内存中的值
	 * 
	 * @param key
	 * @param value
	 */
	public static synchronized void setAttribute(String key, Object value) {
		servletContext.setAttribute(key, value);
	}

	/**
	 * 移除值
	 * 
	 * @param key
	 */
	public static synchronized void remove(String key) {
		servletContext.removeAttribute(key);
	}

	/**
	 * 获取内存中的值
	 * 
	 * @param key
	 * @return
	 */
	public static synchronized Object getAttribute(String key) {
		return servletContext.getAttribute(key);
	}
}
