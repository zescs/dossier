package com.zescs.dossier.core.data.facade;

import java.util.LinkedList;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zescs.dossier.common.pagination.Order;
import com.zescs.dossier.common.pagination.Pageable;
import com.zescs.dossier.model.data.bean.DataConstant;

/**
 * 
 * @ClassName: DataConstantFacade
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zescs 建平
 * @date Nov 14, 2016 4:12:37 PM
 *
 */
public interface DataConstantFacade {
	/**
	 * 根据key的值获取
	 * @param key
	 * @return
	 */
	DataConstant getValueByKey(String key);
	/**
	 * 查询结果
	 * @param entity
	 * @param orderBy
	 * @return
	 */
	PageInfo<DataConstant> queryListBySearch(Pageable pageable,DataConstant entity, LinkedList<Order> orderBy);
	/**
	 * 
	 * @param systemConfigId
	 * @return
	 */
	DataConstant findById(Integer systemConfigId);
	/**
	 * 
	 * @param entity
	 * @return
	 */
	Boolean add(DataConstant entity);
	/**
	 * 
	 * @param entity
	 * @return
	 */
	Boolean update(DataConstant entity);
	/**
	 * 
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	Boolean fieldIsExists(String propertyName, String propertyValue);
	/**
	 * 
	 * @param deleIds
	 * @return
	 */
	Boolean removeByIds(List<Integer> deleIds);
}
