package com.zescs.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.zescs.dossier.webapp.web.interceptor.form.FormCehckInterceptor;
import com.zescs.dossier.webapp.web.interceptor.login.LoginInterceptor;

@Configuration
public class MvcBeanConfig {

	@Bean(name = "localeChangeInterceptor")
	public LocaleChangeInterceptor localeChangeInterceptor() {
		return new LocaleChangeInterceptor();
	}

	@Bean(name = "formCehckInterceptor")
	public FormCehckInterceptor formCehckInterceptor() {
		return new FormCehckInterceptor();
	}

	@Bean(name = "loginInterceptor")
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
}
