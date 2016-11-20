package com.zescs.dossier.core.log.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zescs.dossier.core.log.facade.LoginInfoFacade;
import com.zescs.dossier.core.log.service.LoginInfoService;
import com.zescs.dossier.model.log.bean.LoginInfo;

@Service
public class LoginInfoFacadeBean implements LoginInfoFacade {
	@Autowired
	private LoginInfoService loginInfoService;

	@Override
	public Boolean add(LoginInfo entity) {
		Boolean result =  loginInfoService.add(entity);
		System.out.println(entity.getInfoId());
		return result;
	}

}
