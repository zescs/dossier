package com.zescs.config.mvc;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zescs.config.util.MvcConfigUtils;
import com.zescs.dossier.config.R;
import com.zescs.dossier.webapp.web.interceptor.form.FormCehckInterceptor;
import com.zescs.dossier.webapp.web.interceptor.login.LoginInterceptor;
import com.zescs.dossier.webapp.web.multipart.ApplicationCommonsMultipartResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = R.App.BASEPACKAGES_SCAN, useDefaultFilters = false, includeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
@SuppressWarnings("rawtypes")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({ MvcBeanConfig.class })
public class WebMvcConfigAdapter extends WebMvcConfigurerAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcConfigAdapter.class);
	@Autowired
	private FormCehckInterceptor formCehckInterceptor;
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	@Autowired
	private LoginInterceptor loginInterceptor;

	{
		LOGGER.info("==============使用 WebMvcConfigAdapter 初始化MVC配置");
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placehodlerConfigurer() {
		LOGGER.info("PropertySourcesPlaceholderConfigurer");
		return new PropertySourcesPlaceholderConfigurer();
	}

	// //////////////////////视图解析器///////////////////////////////
	@Bean
	public ViewResolver viewResolver() {
		LOGGER.info("ViewResolver");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix(R.App.VIEW_PREFIX);
		viewResolver.setSuffix(R.App.VIEW_SUFFIX_JSP);
		viewResolver.setOrder(2);
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(viewResolver());
	}

	// /////////////////////静态资源过滤////////////////////////////////
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		LOGGER.info("addResourceHandlers");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// ////////////////////消息转换器/////////////////////////////////
	@Bean
	public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
		LOGGER.info("fastJsonHttpMessageConverter");
		FastJsonHttpMessageConverter fastJson = new FastJsonHttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
		supportedMediaTypes.add(new MediaType("text", "html", DEFAULT_CHARSET));
		supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastJson.setSupportedMediaTypes(supportedMediaTypes);
		FastJsonConfig config = new FastJsonConfig();
		config.setCharset(Charset.forName("UTF-8"));
		config.setSerializerFeatures(SerializerFeature.WriteMapNullValue, SerializerFeature.QuoteFieldNames,
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
		fastJson.setFastJsonConfig(config);
		return fastJson;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new SourceHttpMessageConverter());
		converters.add(fastJsonHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		converters.add(new AllEncompassingFormHttpMessageConverter());
	}

	// ///////////////////////国际化配置//////////////////////////////
	@Bean(name = "localeResolver")
	public SessionLocaleResolver localeResolver() {
		LOGGER.info("localeResolver");
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(new Locale("en_US"));
		return sessionLocaleResolver;
	}

	@Override
	public Validator getValidator() {
		LOGGER.info("validator");
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		factory.setProviderClass(HibernateValidator.class);
		factory.setValidationMessageSource(messageSource());
		return factory;
	}

	/**
	 * 
	 * @Title: messageSource
	 * @Description: TODO(国际化资源切换)
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		LOGGER.info("MessageSource");
		ReloadableResourceBundleMessageSource rs = new ReloadableResourceBundleMessageSource();
		rs.setDefaultEncoding("UTF-8");// 设置编码
		rs.setCacheSeconds(60);
		rs.setUseCodeAsDefaultMessage(false);
		rs.setBasenames("classpath:messages/messages", "classpath:org/hibernate/validator/ValidationMessages");
		return rs;
	}

	// /////////////////////////// 文件上传/////////////////////////////////
	/**
	 * 文件上传解析器配置
	 * 
	 * @return
	 */
	@Bean(name = "multipartResolver")
	public ApplicationCommonsMultipartResolver commonsMultipartResolver() {
		LOGGER.info("commonsMultipartResolver");
		return new ApplicationCommonsMultipartResolver();
	}

	// /////////////////////////拦截器配置////////////////////////////
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LOGGER.info("addInterceptors");
		registry.addInterceptor(localeChangeInterceptor);
		registry.addInterceptor(loginInterceptor);
		registry.addInterceptor(formCehckInterceptor);
	}

	// //////////////////////转换器配置///////////////////////////////
	@Override
	public void addFormatters(FormatterRegistry registry) {
		LOGGER.info("addFormatters");
		// 转换器
		DateFormatter dateDefaultFormatter = new DateFormatter();
		dateDefaultFormatter.setPattern(R.Pattern.DATE_FORMAT_DEFAULT);
		registry.addFormatter(dateDefaultFormatter);
		// 枚举转换器
		MvcConfigUtils.addConverter(registry);
	}

	// /////////////////////////////////////////////////////
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/login");
	}
	// /////////////////////////////////////////////////////
}
