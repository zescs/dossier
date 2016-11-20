package com.zescs.dossier.webapp.data.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.zescs.dossier.common.pagination.Pageable;
import com.zescs.dossier.common.pagination.PaginContext;
import com.zescs.dossier.common.pagination.util.OrderBuilderUtils;
import com.zescs.dossier.common.web.exception.ValidationException;
import com.zescs.dossier.common.web.guava.GuavaUtils;
import com.zescs.dossier.common.web.json.Message;
import com.zescs.dossier.config.R;
import com.zescs.dossier.core.data.facade.DataConstantFacade;
import com.zescs.dossier.model.data.bean.DataConstant;
import com.zescs.dossier.webapp.web.controller.BaseController;
import com.zescs.dossier.webapp.web.interceptor.form.annotation.FormCode;
import com.zescs.dossier.webapp.web.interceptor.form.util.Token;
import com.zescs.dossier.webapp.web.util.WebUtils;

@Controller
@Scope(R.App.SCOPE_PROTOTYPE)
@RequestMapping(R.Path.Mapping.DATA_CONSTANT)
public class DataConstantController extends BaseController {

	@Reference
	private DataConstantFacade dataConstantFacade;

	@RequestMapping(value = R.Path.Mapping.GLOBAL_LIST_ACTION, method = RequestMethod.GET)
	public String list(Model model) {
		return "data/constant/list";
	}

	@RequestMapping(value = R.Path.Mapping.GLOBAL_LIST_ACTION, method = RequestMethod.POST)
	@ResponseBody
	public PageInfo<DataConstant> list(DataConstant entity,
			@RequestParam(value = "sortField", required = false) String sortField,
			@RequestParam(value = "sortOrder", required = false) String sortOrder) {
		Pageable pageable = PaginContext.getPageable();
		return dataConstantFacade.queryListBySearch(pageable, entity,
				OrderBuilderUtils.getOrderBy(sortField, sortOrder));
	}

	////////////////////////////////////////////////////////////////////
	@RequestMapping(value = R.Path.Mapping.GLOBAL_GETEDITBYID_ACTION, method = RequestMethod.POST)
	@ResponseBody
	public DataConstant getEditById(@RequestParam("systemConfigId") Integer systemConfigId) {
		return dataConstantFacade.findById(systemConfigId);
	}

	////////////////////////////////////////////////////////////////////
	@RequestMapping(value = R.Path.Mapping.GLOBAL_ADD_ACTION, method = RequestMethod.GET)
	@FormCode(token = Token.SET)
	public String add(Model model) {
		return "data/constant/add";
	}

	@RequestMapping(value = R.Path.Mapping.GLOBAL_ADD_ACTION, method = RequestMethod.POST)
	@ResponseBody
	@FormCode(token = Token.GET)
	public Message add(@Validated DataConstant entity, BindingResult result) {
		if (result.hasErrors()) {
			throw new ValidationException();
		}
		entity.setCreateDate(new Date());
		setFlag(dataConstantFacade.add(entity));
		setMessage(getFlag() ? WebUtils.getMessage(R.MessageKey.GLOBAL_MESSAGE_OPERATION_ADD_SUCCESS)
				: WebUtils.getMessage(R.MessageKey.GLOBAL_MESSAGE_OPERATION_ADD_FAILURE));
		return Message.newInstance(getFlag(), getMessage(), getFormCode());
	}

	////////////////////////////////////////////////////////////////////
	@RequestMapping(value = R.Path.Mapping.GLOBAL_EDIT_ACTION, method = RequestMethod.POST)
	@ResponseBody
	@FormCode(token = Token.GET)
	public Message edit(@Validated DataConstant entity, BindingResult result) {
		if (result.hasErrors()) {
			throw new ValidationException();
		}
		setFlag(dataConstantFacade.update(entity));
		return Message.newInstance(getFlag(), getMessage(), getFormCode());
	}

	////////////////////////////////////////////////////////////////////
	@RequestMapping(value = R.Path.Mapping.GLOBAL_FIELDISEXISTS_ACTION, method = RequestMethod.POST)
	@ResponseBody
	public Message fieldIsExists(@RequestParam("propertyName") String propertyName,
			@RequestParam("propertyValue") String propertyValue) {
		setFlag(dataConstantFacade.fieldIsExists(propertyName, propertyValue));
		return Message.newInstance(getFlag(), getMessage(), getFormCode());
	}

	////////////////////////////////////////////////////////////////////
	@RequestMapping(value = R.Path.Mapping.GLOBAL_REMOVE_ACTION, method = RequestMethod.DELETE)
	@ResponseBody
	public Message remove(@RequestParam("delete_ids") String delete_ids) {
		List<String> deleIds_Str = GuavaUtils.Splitter(delete_ids);
		List<Integer> deleIds=Lists.newArrayList();
		for (String id : deleIds_Str) {
			deleIds.add(Integer.parseInt(id));
		}
		setFlag(dataConstantFacade.removeByIds(deleIds));
		setMessage(WebUtils.getMessage(R.MessageKey.GLOBAL_MESSAGE_OPERATION_DELEETE_SUCCESS));
		return Message.newInstance(getFlag(), getMessage());
	}
}
