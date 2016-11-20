package com.zescs.config.app;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.zescs.dossier.config.holder.SpringContextHolder;

/**
 * 
 * @ClassName: BaseAppConfig
 * @Description: TODO()
 * @author zescs 建平
 * @date Nov 17, 2016 11:08:54 PM
 *
 */
public class BaseAppConfig {
	private static final Logger logger = Logger.getLogger(BaseAppConfig.class);

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

	@Bean
	public SpringContextHolder createSpringContextHolder() {
		logger.info("SpringContextHolder");
		return new SpringContextHolder();
	}

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
		threadPool.setCorePoolSize(5);
		threadPool.setKeepAliveSeconds(30000);
		threadPool.setMaxPoolSize(50);
		threadPool.setQueueCapacity(100);
		return threadPool;
	}
}
