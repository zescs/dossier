package com.zescs.dossier.webapp.user.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.zescs.dossier.common.pagination.Pageable;
import com.zescs.dossier.common.pagination.PaginContext;
import com.zescs.dossier.common.pagination.util.OrderBuilderUtils;
import com.zescs.dossier.config.R;
import com.zescs.dossier.core.user.facade.UserFacade;
import com.zescs.dossier.model.user.bean.User;
import com.zescs.dossier.webapp.web.controller.BaseController;

@Controller
@Scope(R.App.SCOPE_PROTOTYPE)
@SessionAttributes({ R.SystemConfig.LOGIN_USER_ADMIN,R.ProJectConfig.SECURITY_CODE })
@RequestMapping(R.Path.Mapping.USER_ADMIN)
public class UserController extends BaseController{
	@Reference
	private UserFacade userFacade;
	
	@RequestMapping(value=R.Path.Mapping.GLOBAL_LIST_ACTION,method=RequestMethod.GET)
	public String list(Model model){
		return "user/list";
	}
	
	@RequestMapping(value=R.Path.Mapping.GLOBAL_LIST_ACTION,method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<User> list(
			User entity,
			@RequestParam(value = "sortField", required = false) String sortField,
			@RequestParam(value = "sortOrder", required = false) String sortOrder){
		Pageable pageable = PaginContext.getPageable();
		return userFacade.queryPageBySearch(pageable,entity,OrderBuilderUtils.getOrderBy(sortField, sortOrder));
	}
	
}
