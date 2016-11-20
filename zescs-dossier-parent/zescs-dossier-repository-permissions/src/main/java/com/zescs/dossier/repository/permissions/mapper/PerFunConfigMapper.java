package com.zescs.dossier.repository.permissions.mapper;

import com.zescs.dossier.model.permissions.bean.PerFunConfig;

public interface PerFunConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(PerFunConfig record);

    int insertSelective(PerFunConfig record);

    PerFunConfig selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(PerFunConfig record);

    int updateByPrimaryKey(PerFunConfig record);
}