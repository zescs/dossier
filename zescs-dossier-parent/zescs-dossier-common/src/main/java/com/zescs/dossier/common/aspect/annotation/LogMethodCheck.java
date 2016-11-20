package com.zescs.dossier.common.aspect.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zescs.dossier.common.aspect.util.OperationType;

/**
 * 
 * @ClassName: LogCheck
 * @Description: TODO(日志检查)
 * @author sjth-郑建平
 * @date 2014年5月19日 上午10:32:43
 */
@Target({ ElementType.METHOD })
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LogMethodCheck {
	/**
	 * 
	 * @Title: value
	 * @Description: TODO(显示的日志)
	 * @return
	 */
	public String value() default "";

	/**
	 * 类型
	 * 
	 * @return
	 */
	public OperationType type() default OperationType.QUERY;
	/**
	 * 电子文件
	 * @return
	 */
	public boolean isElectFile() default false;
}
