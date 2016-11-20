package com.zescs.dossier.webapp.web.interceptor.form.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zescs.dossier.webapp.web.interceptor.form.util.Token;

/**
 * 
 * @ClassName: FormCode
 * @Description: TODO(表单验证码生成)
 * @author john
 * @date 2014年8月6日 下午1:11:34
 * 
 */
/* 可以书写在类或者方法上 */
@Target({ ElementType.METHOD })
/* 运行时生效 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormCode {

	/**
	 * 
	 * @Title: value
	 * @Description: TODO(提交日志)
	 * @return
	 */
	String value() default "";

	/**
	 * 
	 * @return
	 */
	Token token() default Token.SET;
}
