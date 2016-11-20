package com.zescs.dossier.webapp.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.zescs.dossier.common.service.exception.PersistAddException;
import com.zescs.dossier.common.service.exception.PersistRemoveException;
import com.zescs.dossier.common.service.exception.PersistUpdateException;
import com.zescs.dossier.common.web.exception.FormSubmitException;
import com.zescs.dossier.common.web.exception.ValidationException;
import com.zescs.dossier.common.web.json.Message;
import com.zescs.dossier.common.web.util.StringUtils;
import com.zescs.dossier.config.R;
import com.zescs.dossier.webapp.web.util.WebUtils;

/**
 * 
 * @ClassName: ExceptionHandlerController
 * @Description: TODO()
 * @author 郑建平
 * @date 2015年1月18日 下午7:01:28
 *
 */
@ControllerAdvice
public class ExceptionHandlerController extends BaseController {
	private static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);
	private FastJsonJsonView view = new FastJsonJsonView();

	@ExceptionHandler
	public ModelAndView exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex,
			Object handler) {
		String requestType = request.getHeader("X-Requested-With");
		response.setStatus(500);
		setFlag(false);
		String message = ex.getMessage();
		LOGGER.error("异常类型::"+ex.getClass());
		if (StringUtils.isNotEmpty(message) && message.contains("message:")) {
			int start = message.indexOf(":", message.indexOf("message"));
			message = message.substring(start + 1);
			setMessage(message);
		} else {
			if (ex instanceof PersistAddException) {
				setMessage(WebUtils.getMessage(R.MessageKey.GLOBAL_MESSAGE_OPERATION_ADD_FAILURE));
			} else if (ex instanceof PersistUpdateException) {
				setMessage(WebUtils.getMessage(R.MessageKey.GLOBAL_MESSAGE_OPERATION_UPDATE_FAILURE));
			} else if (ex instanceof PersistRemoveException) {
				setMessage(WebUtils.getMessage(R.MessageKey.GLOBAL_MESSAGE_OPERATION_DELETE_FAILURE));
			} else if (ex instanceof MaxUploadSizeExceededException) {
				setMessage(WebUtils.getMessage(R.MessageKey.GLOBAL_MESSAGE_OPERATION_FILEUPLOAD_ERROR));
			} else if (ex instanceof ValidationException) {
				setMessage(WebUtils.getMessage(R.MessageKey.GLOBAL_MESSAGE_OPERATION_VALIDATION_ERROR));
			} else if (ex instanceof FormSubmitException) {
				setMessage(WebUtils.getMessage(R.MessageKey.GLOBAL_MESSAGE_OPERATION_FORMSUBMIT_ERROR));
			} else {
				setMessage(WebUtils.getMessage(R.MessageKey.GLOBAL_MESSAGE_OPERATION_ERROR));
			}
		}
		if (requestType != null) {
			return new ModelAndView(view, "error",
					Message.newInstance(getFlag(), getMessage(), WebUtils.setFormCode(request)));
		} else {
			return new ModelAndView("errors/500", "error", ex.getMessage());
		}
	}
}
