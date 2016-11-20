package com.zescs.dossier.core.data.service;

import java.util.LinkedList;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zescs.dossier.common.pagination.Order;
import com.zescs.dossier.common.pagination.Pageable;
import com.zescs.dossier.common.service.BaseService;
import com.zescs.dossier.model.data.bean.DataConstant;

public interface DataConstantService extends BaseService<DataConstant>{

	DataConstant getValueByKey(String key);

	PageInfo<DataConstant> queryListBySearch(Pageable pageable,DataConstant entity, LinkedList<Order> orderBy);

	Boolean fieldIsExists(String propertyName, String propertyValue);

	Boolean removeByIds(List<Integer> deleIds);

}
