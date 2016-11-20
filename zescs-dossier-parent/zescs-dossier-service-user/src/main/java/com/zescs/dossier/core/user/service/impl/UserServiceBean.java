package com.zescs.dossier.core.user.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zescs.dossier.common.pagination.Condition;
import com.zescs.dossier.common.pagination.Order;
import com.zescs.dossier.common.pagination.Pageable;
import com.zescs.dossier.common.pagination.util.PageInfoUtils;
import com.zescs.dossier.common.service.impl.BaseServiceBean;
import com.zescs.dossier.config.R;
import com.zescs.dossier.core.user.service.UserService;
import com.zescs.dossier.model.user.bean.User;
import com.zescs.dossier.model.user.domain.OnlineState;
import com.zescs.dossier.repository.user.mapper.UserMapper;

@Service("userService")
public class UserServiceBean extends BaseServiceBean<User> implements UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceBean.class);
	@Autowired
	private UserMapper userMapper;
	
    @Override
    public String getHashKey() {
    	return R.Cache.Key.DOSSIER_USER;
    }
	@Override
	public Boolean add(User record) {
		return userMapper.insert(record) != 0;
	}

	@Override
	public Boolean update(User record) {
		return null;
	}

	@Override
	public User findById(Integer id) {
		return userMapper.findById(id);
	}

	@Override
	public User findByUserName(String userName) {
		User user = null;
		// 将该用户写入缓存
		try {
			String rs_str = getBoundHashOperations().get(userName);
			user = JSON.parseObject(rs_str, User.class);
			LOG.info("redis:成功从缓存中获取到用户信息");
		} catch (Exception e) {
			LOG.error("redis:从缓存中获取用户信息失败::" + e.getMessage());
		}
		if (user == null) {
			user = userMapper.findByUserName(userName);
			if (getBoundHashOperations() != null && user != null) {
				try {
					getBoundHashOperations().put(userName, JSON.toJSONString(user));
					LOG.info("redis:将用户信息成功写入缓存");
				} catch (Exception e) {
					LOG.error("redis:将用户信息写入缓存失败::" + e.getMessage());
				}
			}
		}
		return user;
	}

	@Override
	public Boolean updateUserOnlineStatus(Integer userId, OnlineState onlineState) {
		User user = new User();
		user.setUserId(userId);
		user.setOnlineState(onlineState);
		return userMapper.updateUserOnlineStatus(user) > 0;
	}

	@Override
	@Transactional(readOnly=true)
	public PageInfo<User> queryPageBySearch(Pageable pageable,User entity, LinkedList<Order> orderBy) {
		PageHelper.startPage(pageable.getPageIndex(), pageable.getPageSize());
		Condition condition = new Condition();
		//添加排序信息
		condition.addOrder(orderBy);
		//添加分组信息
		if(entity!=null){
			
		}
		//开始设置分页
		PageHelper.startPage(pageable.getPageIndex(), pageable.getPageSize());
		List<User> datas= userMapper.queryListByCondition(condition);
		PageInfo<User> page = PageInfoUtils.buildPageInfo(datas);
		return page;
	}

}
