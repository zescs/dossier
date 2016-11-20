package com.zescs.dossier.core.permissions.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.zescs.dossier.core.permissions.facade.MenuFacade;
import com.zescs.dossier.core.permissions.service.MenuService;
import com.zescs.dossier.model.permissions.bean.Menu;

@Service
public class MenuFacadeBean implements MenuFacade {
	@Autowired
	private MenuService menuService;

	@Override
	public List<Menu> loadUserMenus(Integer userId, Integer menuId) {
		return menuService.loadUserMenus(userId, menuId);
	}

}
