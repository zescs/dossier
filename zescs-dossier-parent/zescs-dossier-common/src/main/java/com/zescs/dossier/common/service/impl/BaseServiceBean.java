package com.zescs.dossier.common.service.impl;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zescs.dossier.common.service.BaseService;

@Transactional(propagation = Propagation.REQUIRED)
public abstract class BaseServiceBean<T extends Serializable> implements BaseService<T> {
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseServiceBean.class);
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	public StringRedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
		return threadPoolTaskExecutor;
	}

	@Override
	public String getHashKey() {
		return null;
	}

	@Override
	public BoundHashOperations<String, String, String> getBoundHashOperations() {
		try {
			return redisTemplate.boundHashOps(getHashKey());
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}
}
