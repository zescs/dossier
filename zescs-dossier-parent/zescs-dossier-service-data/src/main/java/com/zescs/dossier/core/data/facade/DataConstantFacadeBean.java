package com.zescs.dossier.core.data.facade;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zescs.dossier.common.pagination.Order;
import com.zescs.dossier.common.pagination.Pageable;
import com.zescs.dossier.core.data.service.DataConstantService;
import com.zescs.dossier.model.data.bean.DataConstant;

@Service
public class DataConstantFacadeBean implements DataConstantFacade {
	@Autowired
	private DataConstantService dataConstantService;
	
	@Override
	public DataConstant getValueByKey(String key) {
		return dataConstantService.getValueByKey(key);
	}

	@Override
	public PageInfo<DataConstant> queryListBySearch(Pageable pageable,DataConstant entity, LinkedList<Order> orderBy) {
		return dataConstantService.queryListBySearch(pageable,entity,orderBy);
	}

	@Override
	public DataConstant findById(Integer systemConfigId) {
		return dataConstantService.findById(systemConfigId);
	}

	@Override
	public Boolean add(DataConstant entity) {
		return dataConstantService.add(entity);
	}

	@Override
	public Boolean update(DataConstant entity) {
		return dataConstantService.update(entity);
	}

	@Override
	public Boolean fieldIsExists(String propertyName, String propertyValue) {
		return dataConstantService.fieldIsExists(propertyName,propertyValue);
	}

	@Override
	public Boolean removeByIds(List<Integer> deleIds) {
		return dataConstantService.removeByIds(deleIds);
	}

}
