package com.zescs.dossier.core.user.facade.impl;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zescs.dossier.common.pagination.Order;
import com.zescs.dossier.common.pagination.Pageable;
import com.zescs.dossier.core.user.facade.UserFacade;
import com.zescs.dossier.core.user.service.UserService;
import com.zescs.dossier.model.user.bean.User;
import com.zescs.dossier.model.user.domain.OnlineState;

@Service
public class UserFacadeBean implements UserFacade {
	@Autowired
	private UserService userService;

	@Override
	public User findById(Integer id) {
		return userService.findById(id);
	}

	@Override
	public PageInfo<User> queryPageByUserName(Pageable pageable, String userName) {
		return null;
	}

	@Override
	public User findByUserName(String userName) {
		return userService.findByUserName(userName);
	}

	@Override
	public Boolean updateUserOnlineStatus(Integer userId,OnlineState onlineState) {
		return userService.updateUserOnlineStatus(userId,onlineState);
	}

	@Override
	public PageInfo<User> queryPageBySearch(Pageable pageable,User entity, LinkedList<Order> orderBy) {
		return userService.queryPageBySearch(pageable,entity,orderBy);
	}

}
