package com.zescs.dossier.repository.permissions.mapper;

import com.zescs.dossier.model.permissions.bean.Function;

public interface FunctionMapper {
    int deleteByPrimaryKey(Integer functionId);

    int insert(Function record);

    int insertSelective(Function record);

    Function selectByPrimaryKey(Integer functionId);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);
}