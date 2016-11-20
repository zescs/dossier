package com.zescs.dossier.common.service;

import java.io.Serializable;

import org.springframework.data.redis.core.BoundHashOperations;

public interface BaseService<T extends Serializable> {
	Boolean add(T record);

	Boolean update(T record);

	T findById(Integer id);
	
	BoundHashOperations<String, String, String> getBoundHashOperations();
	
	String getHashKey();
}
