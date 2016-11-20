package com.zescs.dossier.webapp.web.interceptor.form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zescs.dossier.common.web.exception.FormSubmitException;
import com.zescs.dossier.common.web.util.StringUtils;
import com.zescs.dossier.webapp.web.interceptor.form.annotation.FormCode;
import com.zescs.dossier.webapp.web.interceptor.form.util.Token;
import com.zescs.dossier.webapp.web.util.WebUtils;

/**
 * 
 * @ClassName: FormCehckInterceptor
 * @Description: TODO(表单防止重复提交过滤器)
 * @author john
 * @date 2014年8月6日 下午1:10:07
 * 
 */
public class FormCehckInterceptor extends HandlerInterceptorAdapter {
	private final Logger LOG = Logger.getLogger(FormCehckInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Boolean flag = true;
		// 检测目标类型
		if (handler instanceof HandlerMethod) {
			// 获取当前类型
			HandlerMethod type = (HandlerMethod) handler;
			// 获取方法上的注解
			FormCode formCode = type.getMethodAnnotation(FormCode.class);
			if (formCode == null) {
				return flag;
			}
			// 获取方法上面的日志信息
			String message = formCode.value();
			if (StringUtils.isNotEmpty(message)) {
				LOG.info(message);
			}
			// 获取token值
			/*
			 * 如果token值为null则直接阻止程序运行
			 */
			Token token = formCode.token();

			if (token == null) {
				LOG.info("token没有设置值，程序终止执行");
				throw new FormSubmitException();
			}

			if (token == Token.GET) {
				// 检测session中的值是否相同
				if (!WebUtils.checkFormCode(request)) {
					flag = false;
					LOG.info("表单重复提交。。。。。。");
					throw new FormSubmitException();
				}
				WebUtils.setFormCode(request);
			} else if (token == Token.SET) {
				// 创建一个code写入session，且要写入request中
				WebUtils.setFormCode(request);
			} else {
				// 创建一个code写入session，且要写入request中
				WebUtils.setFormCode(request);
			}

		}
		return flag;
	}
}
