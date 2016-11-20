package com.zescs.config.initializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.zescs.config.app.AppConfig;
import com.zescs.config.app.MvcAppConfig;
import com.zescs.config.mvc.SecurityConfig;
import com.zescs.config.mvc.WebMvcConfigAdapter;
import com.zescs.dossier.config.R;

@Order(1)
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	/*
	 * DispatcherServlet的映射路径
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/", "*"+R.App.MAPPING_SUFFIX };
	}

	/*
	 * 应用上下文，除web部分
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Class[] getRootConfigClasses() {
		return new Class[] {AppConfig.class,SecurityConfig.class};
	}

	/*
	 * web上下文
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Class[] getServletConfigClasses() {
		return new Class[] { WebMvcConfigAdapter.class,MvcAppConfig.class};
	}

	/*
	 * 注册过滤器，映射路径与DispatcherServlet一致，
	 * 路径不一致的过滤器需要注册到另外的WebApplicationInitializer中
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter };
	}

	/*
	 * 可以注册DispatcherServlet的初始化参数，等等
	 */
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setLoadOnStartup(1);
	}

}
