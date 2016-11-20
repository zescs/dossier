package com.zescs.config.initializer;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.util.IntrospectorCleanupListener;

import com.zescs.dossier.webapp.web.listener.ApplicationServletContextListener;

@Order(3)
public class CommonInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addListener(IntrospectorCleanupListener.class);
		// 应用程序启动监听器
		servletContext.addListener(ApplicationServletContextListener.class);
		// 请求方法过滤器
		HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
		FilterRegistration.Dynamic hiddenHttpMethodFilterRegistration = servletContext
				.addFilter("hiddenHttpMethodFilter", hiddenHttpMethodFilter);
		hiddenHttpMethodFilterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST,
				DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC), false, "/*");
	}
}
