package com.zescs.config.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.zescs.config.dubbo.DubboProviderConfig;
import com.zescs.config.redis.RedisConfig;
import com.zescs.config.repository.DaoConfig;
import com.zescs.dossier.config.R;

@Configuration
@ComponentScan(basePackages = R.App.BASEPACKAGES_SCAN, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class, ControllerAdvice.class }) })
@Import({ DaoConfig.class, DubboProviderConfig.class, RedisConfig.class })
public class ApplicationConfig extends BaseAppConfig{
	
}
