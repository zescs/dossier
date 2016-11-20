package com.zescs.dossier.repository.log.mapper;

import com.zescs.dossier.model.log.bean.LoginInfo;

public interface LoginInfoMapper {
    int deleteByPrimaryKey(Integer infoId);

    int insert(LoginInfo record);

    int insertSelective(LoginInfo record);

    LoginInfo selectByPrimaryKey(Integer infoId);

    int updateByPrimaryKeySelective(LoginInfo record);

    int updateByPrimaryKey(LoginInfo record);
}