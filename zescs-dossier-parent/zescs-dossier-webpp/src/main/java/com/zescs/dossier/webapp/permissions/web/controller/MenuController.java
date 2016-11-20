package com.zescs.dossier.webapp.permissions.web.controller;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zescs.dossier.config.R;
import com.zescs.dossier.core.permissions.facade.MenuFacade;
import com.zescs.dossier.model.permissions.bean.Menu;
import com.zescs.dossier.model.user.bean.LoginUser;
import com.zescs.dossier.webapp.web.controller.BaseController;
/**
 * 
* @ClassName: MenuController 
* @Description: TODO(菜单控制器) 
* @author zescs.com 郑建平 
* @date 2016年11月3日 上午10:44:09 
*
 */
@Controller
@Scope(R.App.SCOPE_PROTOTYPE)
@RequestMapping(R.Path.Mapping.PERMISSIONS_MENU)
@SessionAttributes({ R.SystemConfig.LOGIN_USER_ADMIN })
public class MenuController extends BaseController {
	@Reference
	private MenuFacade menuFacade;
	
	@RequestMapping(value = R.Path.Mapping.PERMISS_MENU_LOADMENUS, method = RequestMethod.POST)
	@ResponseBody
	public List<Menu> loadMenus(
			@RequestParam(value = "menuId", required = false) Integer menuId,
			@ModelAttribute(R.SystemConfig.LOGIN_USER_ADMIN) LoginUser admin) {
		return menuFacade.loadUserMenus(admin.getUserId(), menuId);
	}
}
