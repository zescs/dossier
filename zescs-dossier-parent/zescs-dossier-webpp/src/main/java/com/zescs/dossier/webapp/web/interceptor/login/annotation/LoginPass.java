package com.zescs.dossier.webapp.web.interceptor.login.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
 * @ClassName: LoginCheck
 * @Description: TODO(登录放行验证)
 * @author john
 * @date 2014年8月6日 下午12:54:34
 * 
 */
/* 指定注解的有效范围 */
@Target({ElementType.METHOD, ElementType.TYPE})
/* 注解的生存周期 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginPass {
	/**
	 * 
	 * @Title: value
	 * @Description: TODO(注解默认值)
	 * @return
	 */
	String value() default "";
}
