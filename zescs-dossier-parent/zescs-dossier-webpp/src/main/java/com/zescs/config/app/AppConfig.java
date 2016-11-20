package com.zescs.config.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.zescs.config.dubbo.DubboConsumerConfig;
import com.zescs.config.redis.WebRedisConfig;
import com.zescs.config.web.KaptchaConfig;
import com.zescs.dossier.config.R;

@Configuration
@ComponentScan(basePackages = R.App.BASEPACKAGES_SCAN, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class, ControllerAdvice.class }) })
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({ WebRedisConfig.class, KaptchaConfig.class,DubboConsumerConfig.class})
public class AppConfig extends BaseAppConfig{

}
