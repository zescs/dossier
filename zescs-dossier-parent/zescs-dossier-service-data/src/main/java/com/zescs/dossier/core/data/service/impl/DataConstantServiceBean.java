package com.zescs.dossier.core.data.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.zescs.dossier.common.pagination.Condition;
import com.zescs.dossier.common.pagination.CriteriaBuilder;
import com.zescs.dossier.common.pagination.Order;
import com.zescs.dossier.common.pagination.Pageable;
import com.zescs.dossier.common.pagination.domain.QueryModel;
import com.zescs.dossier.common.pagination.util.PageInfoUtils;
import com.zescs.dossier.common.service.exception.PersistAddException;
import com.zescs.dossier.common.service.exception.PersistRemoveException;
import com.zescs.dossier.common.service.exception.PersistUpdateException;
import com.zescs.dossier.common.service.impl.BaseServiceBean;
import com.zescs.dossier.common.web.util.StringUtils;
import com.zescs.dossier.config.R;
import com.zescs.dossier.core.data.service.DataConstantService;
import com.zescs.dossier.model.data.bean.DataConstant;
import com.zescs.dossier.repository.data.mapper.DataConstantMapper;

@Service("dataConstantService")
public class DataConstantServiceBean extends BaseServiceBean<DataConstant> implements DataConstantService {
	private static final Logger LOG = LoggerFactory.getLogger(DataConstantService.class);
	
	@Autowired
	private DataConstantMapper dataConstantMapper;
	
	@Override
	public String getHashKey() {
		return R.Cache.Key.DOSSIER_DATA_CONSTANT;
	}
	
	@Override
	public Boolean add(DataConstant record) {
		try {
			int rs= dataConstantMapper.insert(record);
			if(rs>0){
				getThreadPoolTaskExecutor().execute(()->{
					try {
						getBoundHashOperations().put(record.getSystemConfigId().toString(), JSON.toJSONString(record));
						LOG.info("当前数据添加成功，且成功放入redis缓存服务器中");
					} catch (Exception e) {
						LOG.error("当前数据放入缓存失败::"+e.getMessage());
					}
				});
			}
			return rs >0;
		} catch (Exception e) {
			throw new PersistAddException(e);
		}
	}

	@Override
	public Boolean update(DataConstant record) {
		try {
			int rs= dataConstantMapper.update(record);
			if(rs>0){
				try {
					//删除
					getBoundHashOperations().delete(record.getSystemConfigId());
					//在添加
					getBoundHashOperations().put(record.getSystemConfigId().toString(), JSON.toJSONString(record));
					LOG.info("当前数据添加成功，且成功放入redis缓存服务器中");
				} catch (Exception e) {
					LOG.error("当前数据放入缓存失败::"+e.getMessage());
				}
			}
			return rs >0;
		} catch (Exception e) {
			throw new PersistUpdateException(e);
		}
	}

	@Override
	public DataConstant findById(Integer id) {
		//获取缓存中的数据
		DataConstant entity = null;
		BoundHashOperations<String, String, String> bs=null;
		try {
			bs = getRedisTemplate().boundHashOps(R.Cache.Key.DOSSIER_DATA_CONSTANT);
			String rs = bs.get(id);
			if(StringUtils.isNotEmpty(rs)){
				entity=JSON.parseObject(rs, DataConstant.class);
				return entity;
			}
			LOG.info("该条数据尚未加入缓存");
		} catch (Exception e) {
			LOG.error("从缓存中获取数据失败::"+e.getMessage());
		}
		if(entity==null){
			entity = dataConstantMapper.findById(id);
			if(bs!=null){
				bs.delete(String.valueOf(entity.getSystemConfigId()));
				bs.put(String.valueOf(entity.getSystemConfigId()), JSON.toJSONString(entity));
				LOG.info("数据成功放入缓存");
			}
		}
		return entity;
	}

	@Override
	public DataConstant getValueByKey(String key) {
		return dataConstantMapper.getValueByKey(key);
	}

	@Override
	public PageInfo<DataConstant> queryListBySearch(Pageable pageable,DataConstant entity, LinkedList<Order> orderBy) {
		PageHelper.startPage(pageable.getPageIndex(), pageable.getPageSize());
		Condition conn = new Condition();
		//添加排序信息
		conn.addOrder(orderBy);
		//添加分组信息
		if(entity!=null){
			if(StringUtils.isNotEmpty(entity.getSysKey())){
				conn.addCriteria(CriteriaBuilder.build("sysKey", entity.getSysKey(), QueryModel.LIKE));
			}
		}
		List<DataConstant> datas =dataConstantMapper.queryListByCondition(conn);
		PageInfo<DataConstant> page = PageInfoUtils.buildPageInfo(datas);
		return page;
	}

	@Override
	public Boolean fieldIsExists(String propertyName, String propertyValue) {
		Map<String,String> param = Maps.newHashMap();
		param.put("propertyName", propertyName);
		param.put("propertyValue", propertyValue);
		Integer result = dataConstantMapper.countByProperty(param);
		return result >0?Boolean.TRUE:Boolean.FALSE;
	}

	@Override
	public Boolean removeByIds(List<Integer> deleIds) {
		try {
			if(!deleIds.isEmpty()){
				for (Integer id : deleIds) {
					dataConstantMapper.deleteById(id);
				}
				getThreadPoolTaskExecutor().execute(()->{
					for (Integer id : deleIds) {
						try {
							getBoundHashOperations().delete(String.valueOf(id));
							LOG.info("数据成功从redis缓存中删除");
						} catch (Exception e) {
							LOG.error("从redis缓存中删除数据失败 ::"+e.getMessage());
						}
					}
				});
			}
			return true;
		} catch (Exception e) {
			throw new PersistRemoveException(e);
		}
	}
}
