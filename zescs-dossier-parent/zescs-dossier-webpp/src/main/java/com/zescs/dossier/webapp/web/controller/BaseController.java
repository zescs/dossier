package com.zescs.dossier.webapp.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.zescs.dossier.common.web.action.ActionContext;
import com.zescs.dossier.webapp.web.util.WebUtils;

/**
 * 
 * @ClassName: BaseController
 * @Description: TODO()
 * @author john
 * @date 2014年8月8日 上午10:51:05
 *
 */
public abstract class BaseController {

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
	
	private String message;
	private Boolean flag;
	private String other;
	private String formCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getFormCode() {
		if (ActionContext.get() != null) {
			return WebUtils.setFormCode();
		}
		return formCode;
	}

	public String getMessageValue(String code) {
		return WebUtils.getMessage(code);
	}

}
