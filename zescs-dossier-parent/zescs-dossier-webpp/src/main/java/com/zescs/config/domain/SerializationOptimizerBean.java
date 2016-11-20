package com.zescs.config.domain;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;

@SuppressWarnings("rawtypes")
public class SerializationOptimizerBean implements SerializationOptimizer {
	public Collection<Class> getSerializableClasses() {
		List<Class> classes = new LinkedList<Class>();
		return classes;
	}
}