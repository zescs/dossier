package com.zescs.dossier.core.user.facade;

import java.util.LinkedList;

import com.github.pagehelper.PageInfo;
import com.zescs.dossier.common.pagination.Order;
import com.zescs.dossier.common.pagination.Pageable;
import com.zescs.dossier.model.user.bean.User;
import com.zescs.dossier.model.user.domain.OnlineState;

/**
 * 
 * @ClassName: UserFacade
 * @Description: TODO(用户服务接口)
 * @author zescs.com 郑建平
 * @date 2016年11月1日 下午9:27:19
 *
 */
public interface UserFacade {
	/**
	 * 
	 * @param id
	 * @return 一个用户对象
	 */
	User findById(Integer id);

	/**
	 * 
	 * @param pageable
	 *            分页对象
	 * @param userName
	 *            用户名
	 * @return 分页对象
	 */
	PageInfo<User> queryPageByUserName(Pageable pageable, String userName);

	/**
	 * 
	 * @param userName
	 *            用户名
	 * @return 用户对象
	 */
	User findByUserName(String userName);

	/**
	 * 更改用户的IP和在线状态
	 * 
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
