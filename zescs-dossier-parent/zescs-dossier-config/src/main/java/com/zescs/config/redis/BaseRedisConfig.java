package com.zescs.config.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;
/**
 * 
* @ClassName: BaseRedisConfig 
* @Description: TODO() 
* @author zescs 建平 
* @date Nov 17, 2016 11:25:59 PM 
*
 */
public class BaseRedisConfig {
	private static final Logger logger = Logger.getLogger(RedisConfig.class);

	/**
	 * 获取配置文件中的值
	 */
	@Autowired
	private Environment env;

	@Bean(name = "jedisPoolConfig")
	public JedisPoolConfig jedisPoolConfig() {
		logger.info("jedisPoolConfig");
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(Integer.parseInt(env.getProperty("redis.maxIdle")));
		config.setMaxWaitMillis(Long.parseLong(env.getProperty("redis.maxWait")));
		config.setTestOnBorrow(Boolean.parseBoolean(env.getProperty("redis.testOnBorrow")));
		return config;
	}

	@Bean(name = "jedisConnectionFactory")
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig());
		jedisConnectionFactory.setUsePool(true);
		jedisConnectionFactory.setHostName(env.getProperty("redis.host"));
		jedisConnectionFactory.setPort(Integer.parseInt(env.getProperty("redis.port").trim()));
		jedisConnectionFactory.setTimeout(Integer.parseInt(env.getProperty("redis.timeout").trim()));
		jedisConnectionFactory.setDatabase(Integer.parseInt(env.getProperty("redis.default.db").trim()));
		return jedisConnectionFactory;
	}

	@Bean(name = "redisTemplate")
	public StringRedisTemplate redisTemplate() {
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
}
