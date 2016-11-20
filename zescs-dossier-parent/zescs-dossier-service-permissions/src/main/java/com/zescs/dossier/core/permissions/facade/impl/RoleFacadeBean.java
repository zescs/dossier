package com.zescs.dossier.core.permissions.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zescs.dossier.core.permissions.facade.RoleFacade;
import com.zescs.dossier.core.permissions.service.RoleService;
import com.zescs.dossier.model.permissions.bean.Role;
import com.zescs.dossier.model.permissions.domain.Level;

@Service
public class RoleFacadeBean implements RoleFacade {
	@Autowired
	private RoleService roleService;

	@Override
	public List<Role> queryUserRoles(Integer userId) {
		return roleService.queryUserRoles(userId);
	}

	@Override
	public Role findByUserIdAndLevel(Integer userId, Level level) {
		return roleService.findByUserIdAndLevel(userId,level);
	}

}
