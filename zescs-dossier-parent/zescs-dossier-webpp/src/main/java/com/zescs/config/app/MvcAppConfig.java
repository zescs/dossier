package com.zescs.config.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.zescs.config.dubbo.DubboConsumerConfig;

/**
 * 
 * @ClassName: MvcAppConfig
 * @Description: TODO(其他配置)
 * @author 郑建平
 * @date 2015年12月4日 下午2:43:34
 *
 */
@Configuration
@Import({DubboConsumerConfig.class})
public class MvcAppConfig {
	
}
