package com.zescs.dossier.core.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zescs.dossier.common.service.exception.PersistAddException;
import com.zescs.dossier.common.service.impl.BaseServiceBean;
import com.zescs.dossier.core.log.service.LoginInfoService;
import com.zescs.dossier.model.log.bean.LoginInfo;
import com.zescs.dossier.repository.log.mapper.LoginInfoMapper;

@Service("loginInfoService")
public class LoginInfoServiceBean extends BaseServiceBean<LoginInfo> implements LoginInfoService {
	@Autowired
	private LoginInfoMapper loginInfoMapper;
	
	@Override
	public Boolean add(LoginInfo record) {
		try {
			return loginInfoMapper.insert(record)>0;
		} catch (Exception e) {
			throw new PersistAddException(e);
		}
	}

	@Override
	public Boolean update(LoginInfo record) {
		return null;
	}

	@Override
	public LoginInfo findById(Integer id) {
		return null;
	}

}
