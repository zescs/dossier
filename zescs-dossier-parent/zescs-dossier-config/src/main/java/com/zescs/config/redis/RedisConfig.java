package com.zescs.config.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * @ClassName: DaoConfig
 * @Description: TODO(数据持久层配置)
 * @author 郑建平
 * @date 2015年12月4日 下午1:54:03
 *
 */

@Configuration // 加载资源文件
@PropertySource({ "classpath:/config/persistent/redis.properties" })
public class RedisConfig extends BaseRedisConfig{
	
}
