package com.zescs.dossier.repository.permissions.mapper;

import java.util.List;
import java.util.Map;

import com.zescs.dossier.model.permissions.bean.Role;

/**
 * 
 * @ClassName: RoleMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zescs.com 郑建平
 * @date 2016年11月3日 下午6:55:34
 *
 */
public interface RoleMapper {

	int insert(Role record);

	Role findById(Integer roleId);

	int update(Role record);

	List<Role> queryUserRoles(Integer userId);

	Role findByUserIdAndLevel(Map<String, Integer> param);
}