package com.zescs.config.cache;

import org.apache.log4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.zescs.dossier.config.R;

/**
 * 
 * @ClassName: CachingConfig
 * @Description: TODO(缓存配置)
 * @author 郑建平
 * @date 2015年12月4日 下午1:58:02
 *
 */
@Configuration
@EnableCaching(proxyTargetClass = true)
public class CachingConfig extends CachingConfigurerSupport {
	private static final Logger logger = Logger.getLogger(CachingConfig.class);

	@Bean
	@Override
	public CacheManager cacheManager() {
		try {
			logger.info("cacheManager");
			net.sf.ehcache.CacheManager ehcacheCacheManager = new net.sf.ehcache.CacheManager(
					new ClassPathResource(R.Cache.Config.CONFIG_PATH).getInputStream());
			EhCacheCacheManager cacheCacheManager = new EhCacheCacheManager(ehcacheCacheManager);
			return cacheCacheManager;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}

}
