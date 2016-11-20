package com.zescs.config.initializer;

import javax.servlet.ServletContext;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@Order(2)
public class SessionInitializer extends AbstractHttpSessionApplicationInitializer {

	@Override
	protected void afterSessionRepositoryFilter(ServletContext servletContext) {
		servletContext.addListener(new HttpSessionEventPublisher());
	}
}
