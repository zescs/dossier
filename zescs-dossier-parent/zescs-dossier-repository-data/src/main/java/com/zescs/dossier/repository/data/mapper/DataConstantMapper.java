package com.zescs.dossier.repository.data.mapper;

import java.util.List;
import java.util.Map;

import com.zescs.dossier.common.pagination.Condition;
import com.zescs.dossier.model.data.bean.DataConstant;

public interface DataConstantMapper {

	int insert(DataConstant record);

	DataConstant findById(Integer id);

	int update(DataConstant record);

	DataConstant getValueByKey(String key);

	List<DataConstant> queryListByCondition(Condition conn);

	Integer countByProperty(Map<String, String> param);

	Integer deleteById(Integer id);
}