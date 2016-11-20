package com.zescs.dossier.repository.permissions.mapper;

import java.util.List;
import java.util.Map;

import com.zescs.dossier.model.permissions.bean.Menu;
/**
 * 
* @ClassName: MenuMapper 
* @Description: TODO() 
* @author zescs.com 郑建平 
* @date 2016年11月3日 上午11:17:51 
*
 */
public interface MenuMapper {

	int insert(Menu record);

	Menu findById(Integer menuId);

	int update(Menu record);

	List<Menu> queryListParentIsNuLLByUserId(Integer userId);

	List<Menu> queryListByParentIdBAndUserId(Map<String, Integer> map);
}