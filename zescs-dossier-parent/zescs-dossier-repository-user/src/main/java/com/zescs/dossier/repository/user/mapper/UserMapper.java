package com.zescs.dossier.repository.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zescs.dossier.common.pagination.Condition;
import com.zescs.dossier.model.user.bean.User;

/**
 * 
 * @ClassName: UserMapper
 * @Description: TODO()
 * @author zescs.com 郑建平
 * @date 2016年11月1日 下午9:31:50
 *
 */
public interface UserMapper {

	int insert(User record);

	User findById(@Param("userId") Integer userId);

	int update(User record);

	User findByUserName(@Param("userName") String userName);

	int updateUserOnlineStatus(User user);
	
	List<User> queryListByCondition(Condition condition);
}