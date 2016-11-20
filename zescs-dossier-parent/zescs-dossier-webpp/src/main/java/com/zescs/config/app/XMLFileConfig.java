package com.zescs.config.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
/**
 * 导入xml配置文件
 * @author admin
 *
 */
@Configuration
@ImportResource(locations = { "classpath:/config/dubbo/dubbo-consumer.xml" })
public class XMLFileConfig {
	
}
