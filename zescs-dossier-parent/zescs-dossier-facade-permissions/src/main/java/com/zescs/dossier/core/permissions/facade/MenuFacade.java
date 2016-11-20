package com.zescs.dossier.core.permissions.facade;

import java.util.List;

import com.zescs.dossier.model.permissions.bean.Menu;
/**
 * 
* @ClassName: MenuFacade 
* @Description: TODO(菜单服务接口) 
* @author zescs.com 郑建平 
* @date 2016年11月3日 上午10:47:36 
*
 */
public interface MenuFacade {
	/**
	 * 加载用户的菜单
	 * @param userId
	 * @param menuId
	 * @return
	 */
	List<Menu> loadUserMenus(Integer userId, Integer menuId);

}
