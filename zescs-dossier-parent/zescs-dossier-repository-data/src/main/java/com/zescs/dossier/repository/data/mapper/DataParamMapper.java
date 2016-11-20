package com.zescs.dossier.repository.data.mapper;

import com.zescs.dossier.model.data.bean.DataParam;

public interface DataParamMapper {
    int deleteByPrimaryKey(Integer paramId);

    int insert(DataParam record);

    int insertSelective(DataParam record);

    DataParam selectByPrimaryKey(Integer paramId);

    int updateByPrimaryKeySelective(DataParam record);

    int updateByPrimaryKey(DataParam record);
}