package com.zescs.config.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.FormatterRegistry;

/**
 * 
 * @author admin
 *
 */
public final class MvcConfigUtils {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MvcConfigUtils.class);

	/**
	 * 添加枚举转换器
	 * 
	 * @param registry
	 */
	public static void addConverter(FormatterRegistry registry) {
		LOGGER.info("addConverter");
	}
}
