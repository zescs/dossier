package com.zescs.dossier.core.permissions.service;

import java.util.List;

import com.zescs.dossier.common.service.BaseService;
import com.zescs.dossier.model.permissions.bean.Menu;
/**
 * 
* @ClassName: MenuService 
* @Description: TODO() 
* @author zescs.com 郑建平 
* @date 2016年11月3日 上午10:50:42 
*
 */
public interface MenuService extends BaseService<Menu> {
	/**
	 * 
	 * @param userId
	 * @param menuId
	 * @return
	 */
	List<Menu> loadUserMenus(Integer userId, Integer menuId);

}
