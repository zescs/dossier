package com.zescs.dossier.core.user.service;

import java.util.LinkedList;

import com.github.pagehelper.PageInfo;
import com.zescs.dossier.common.pagination.Order;
import com.zescs.dossier.common.pagination.Pageable;
import com.zescs.dossier.common.service.BaseService;
import com.zescs.dossier.model.user.bean.User;
import com.zescs.dossier.model.user.domain.OnlineState;

/**
 * 
 * @ClassName: UserService
 * @Description: TODO(用户业务逻辑层接口)
 * @author zescs.com 郑建平
 * @date 2016年11月1日 下午9:30:35
 *
 */
public interface UserService extends BaseService<User> {
	/**
	 * 根据用户名查询用户
	 * 
	 * @param userName
	 * @return
	 */
	User findByUserName(String userName);

	/**
	 * 更改用户IP和在线状态
	 * 
	 * @param userId
	 * @param ip
	 * @param onlineState
	 * @return
	 */
	Boolean updateUserOnlineStatus(Integer userId, OnlineState onlineState);

	/**
	 * 
	 * @param entity
	 * @param orderBy
	 * @return
	 */
	PageInfo<User> queryPageBySearch(Pageable pageable,User entity, LinkedList<Order> orderBy);

}
