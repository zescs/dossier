package com.zescs.dossier.repository.permissions.mapper;

import com.zescs.dossier.model.permissions.bean.Permissions;

public interface PermissionsMapper {
    int deleteByPrimaryKey(Integer permissionsId);

    int insert(Permissions record);

    int insertSelective(Permissions record);

    Permissions selectByPrimaryKey(Integer permissionsId);

    int updateByPrimaryKeySelective(Permissions record);

    int updateByPrimaryKey(Permissions record);
}