package com.zescs.dossier.core.test;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.zescs.config.redis.RedisConfig;

@Configuration
@ComponentScan(basePackages = "com.zescs.dossier.core.test", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class, ControllerAdvice.class }) })
@Import({ RedisConfig.class })
public class TestAppConfig {
	private static final Logger logger = Logger.getLogger(TestAppConfig.class);

	/**
	 * 
	 * @Title: placehodlerConfigurer
	 * @Description: TODO(这个类必须有，而且必须声明为static，否则不能正常解析)
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer placehodlerConfigurer() {
		logger.info("PropertySourcesPlaceholderConfigurer");
		return new PropertySourcesPlaceholderConfigurer();
	}

}
