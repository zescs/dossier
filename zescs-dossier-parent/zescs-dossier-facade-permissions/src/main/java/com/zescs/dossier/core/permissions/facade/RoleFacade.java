package com.zescs.dossier.core.permissions.facade;

import java.util.List;

import com.zescs.dossier.model.permissions.bean.Role;
import com.zescs.dossier.model.permissions.domain.Level;

/**
 * 
 * @ClassName: RoleFacade
 * @Description: TODO()
 * @author zescs.com 郑建平
 * @date 2016年11月3日 下午6:52:58
 *
 */
public interface RoleFacade {

	List<Role> queryUserRoles(Integer userId);

	/**
	 * 
	 * @param userId
	 * @param main
	 * @return
	 */
	Role findByUserIdAndLevel(Integer userId, Level level);

}
