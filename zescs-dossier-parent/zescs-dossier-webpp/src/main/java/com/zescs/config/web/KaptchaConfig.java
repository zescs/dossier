package com.zescs.config.web;

import java.util.Properties;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * 
 * @ClassName: KaptchaConfig
 * @Description: TODO(验证码配置)
 * @author 郑建平
 * @date 2015年12月4日 下午2:42:37
 *
 */
@Configuration
public class KaptchaConfig {
	private Logger LOGGER = org.slf4j.LoggerFactory.getLogger(KaptchaConfig.class);

	@Bean(name = "producer")
	public DefaultKaptcha defaultKaptcha() {
		LOGGER.info("defaultKaptcha");
		DefaultKaptcha kaptcha = new DefaultKaptcha();
		Properties prop = new Properties();
		prop.setProperty("kaptcha.border", "no");
		prop.setProperty("kaptcha.border.color", "105,179,90");
		prop.setProperty("kaptcha.textproducer.font.color", "215,79,37");
		prop.setProperty("kaptcha.image.width", "200");
		prop.setProperty("kaptcha.image.height", "50");
		prop.setProperty("kaptcha.textproducer.font.size", "35");
		prop.setProperty("kaptcha.textproducer.char.space", "10");
		prop.setProperty("kaptcha.session.key", "kaptcha_code");
		prop.setProperty("kaptcha.textproducer.char.length", "5");
		prop.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
		Config config = new Config(prop);
		kaptcha.setConfig(config);
		return kaptcha;
	}
}
