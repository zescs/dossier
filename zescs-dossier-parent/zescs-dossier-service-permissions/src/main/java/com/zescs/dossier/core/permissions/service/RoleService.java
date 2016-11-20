package com.zescs.dossier.core.permissions.service;

import java.util.List;

import com.zescs.dossier.common.service.BaseService;
import com.zescs.dossier.model.permissions.bean.Role;
import com.zescs.dossier.model.permissions.domain.Level;
/**
 * 
* @ClassName: RoleService 
* @Description: TODO() 
* @author zescs.com 郑建平 
* @date 2016年11月3日 下午6:53:50 
*
 */
public interface RoleService extends BaseService<Role>{

	List<Role> queryUserRoles(Integer userId);
	/**
	 * 
	 * @param userId
	 * @param level
	 * @return
	 */
	Role findByUserIdAndLevel(Integer userId, Level level);

}
