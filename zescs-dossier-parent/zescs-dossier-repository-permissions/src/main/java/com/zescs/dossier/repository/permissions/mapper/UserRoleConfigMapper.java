package com.zescs.dossier.repository.permissions.mapper;

import com.zescs.dossier.model.permissions.bean.UserRoleConfig;

public interface UserRoleConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(UserRoleConfig record);

    int insertSelective(UserRoleConfig record);

    UserRoleConfig selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(UserRoleConfig record);

    int updateByPrimaryKey(UserRoleConfig record);
}