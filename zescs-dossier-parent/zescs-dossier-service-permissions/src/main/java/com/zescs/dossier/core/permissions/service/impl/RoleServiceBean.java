package com.zescs.dossier.core.permissions.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.zescs.dossier.common.service.impl.BaseServiceBean;
import com.zescs.dossier.core.permissions.service.RoleService;
import com.zescs.dossier.model.permissions.bean.Role;
import com.zescs.dossier.model.permissions.domain.Level;
import com.zescs.dossier.repository.permissions.mapper.RoleMapper;

@Service("roleService")
public class RoleServiceBean extends BaseServiceBean<Role> implements RoleService {
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Boolean add(Role record) {
		return null;
	}

	@Override
	public Boolean update(Role record) {
		return null;
	}

	@Override
	public Role findById(Integer id) {
		return null;
	}

	@Override
	public List<Role> queryUserRoles(Integer userId) {
		return roleMapper.queryUserRoles(userId);
	}

	@Override
	public Role findByUserIdAndLevel(Integer userId, Level level) {
		Map<String,Integer> param =Maps.newHashMap();
		param.put("userId", userId);
		param.put("level", level.getIndex());
		return roleMapper.findByUserIdAndLevel(param);
	}

}
