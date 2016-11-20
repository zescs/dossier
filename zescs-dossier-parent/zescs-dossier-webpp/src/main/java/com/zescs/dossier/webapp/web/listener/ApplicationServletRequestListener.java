package com.zescs.dossier.webapp.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import com.zescs.dossier.common.web.action.ActionContext;

@WebListener
public class ApplicationServletRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		ActionContext.remove();
	}

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		ActionContext.put((HttpServletRequest) (event.getServletRequest()));
	}

}
