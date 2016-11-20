package com.zescs.config.mvc;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.event.LoggerListener;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import com.zescs.dossier.model.permissions.domain.Position;
import com.zescs.dossier.webapp.web.security.GoodsUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = Logger.getLogger(SecurityConfig.class);

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(new Md5PasswordEncoder());
	}

	/**
	 * 放行路径
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/**/*.jsp", "/ws/**"); //
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.authorizeRequests().accessDecisionManager(accessDecisionManager())
				.expressionHandler(webSecurityExpressionHandler())
				.antMatchers("/", "/AJAXLoginOut", "/user/admin/login","/user/admin/logout","/errors/**",
						"/securityCode","/login","/quit/logout","/login.action","/logout", "/ws/**","/websocket/archives/**")
				.permitAll()
				.antMatchers("/user/admin/info/**")
				.hasAnyRole(com.zescs.dossier.model.permissions.domain.Position.DEVELOPER.name(), Position.SUPERADMIN.name(), Position.ADMINISTRATOR.name(),Position.SALESMAN.name(),Position.USER.name())
				.antMatchers("/user/**", "/system/**","/archives/**")
				.hasAnyRole(Position.DEVELOPER.name(), Position.SUPERADMIN.name(), Position.ADMINISTRATOR.name())
				.antMatchers("/log/**")
				.hasRole(Position.USER.name())
				.antMatchers("/permissions/**")
				.hasAnyRole(Position.USER.name()).anyRequest().authenticated().and().exceptionHandling()
				.accessDeniedPage("/errors/jurisdiction");
				// 自定义登录页面
				http.csrf().disable().formLogin().loginPage("/quit/logout").failureUrl("/quit/logout")
						.loginProcessingUrl("/j_spring_security_check").usernameParameter("j_username")
						.passwordParameter("j_password").permitAll();
				// 自定义注销
				http.logout().logoutUrl("/user/admin/logout").logoutSuccessUrl("/login").invalidateHttpSession(true);
	}

	@Bean
	public GoodsUserDetailsService userDetailsService() {
		logger.info("GoodsUserDetailsService");
		GoodsUserDetailsService userDetailsService = new GoodsUserDetailsService();
		return userDetailsService;
	}

	@Bean
	public LoggerListener loggerListener() {
		logger.info("org.springframework.security.authentication.event.LoggerListener");
		LoggerListener loggerListener = new LoggerListener();
		return loggerListener;
	}

	@Bean
	public org.springframework.security.access.event.LoggerListener eventLoggerListener() {
		logger.info("org.springframework.security.access.event.LoggerListener");
		org.springframework.security.access.event.LoggerListener eventLoggerListener = new org.springframework.security.access.event.LoggerListener();
		return eventLoggerListener;
	}

	/*
	 * 
	 * 这里可以增加自定义的投票器
	 */
	@Bean(name = "accessDecisionManager")
	public AccessDecisionManager accessDecisionManager() {
		logger.info("AccessDecisionManager");
		List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
		decisionVoters.add(new RoleVoter());
		decisionVoters.add(new AuthenticatedVoter());
		decisionVoters.add(webExpressionVoter());// 启用表达式投票器
		AffirmativeBased accessDecisionManager = new AffirmativeBased(decisionVoters);
		return accessDecisionManager;
	}

	/*
	 * 表达式控制器
	 */
	@Bean(name = "expressionHandler")
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
		logger.info("DefaultWebSecurityExpressionHandler");
		DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		return webSecurityExpressionHandler;
	}

	/*
	 * 表达式投票器
	 */
	@Bean(name = "expressionVoter")
	public WebExpressionVoter webExpressionVoter() {
		logger.info("WebExpressionVoter");
		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		webExpressionVoter.setExpressionHandler(webSecurityExpressionHandler());
		return webExpressionVoter;
	}
}
