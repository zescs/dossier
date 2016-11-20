package com.zescs.dossier.common.web.poi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zescs.dossier.config.R;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelField {
	/**
	 * 显示的名称
	 * 
	 * @return
	 */
	String title() default "";

	/**
	 * 显示的顺序
	 * 
	 * @return
	 */
	int displayOrder() default 0;

	/**
	 * 导出属性名称
	 * 
	 * @return
	 */
	String exportPropertyName() default "";

	/**
	 * 导入属性名称
	 * 
	 * @return
	 */
	String importPropertyName() default "";

	/**
	 * 是否是日期字段
	 * 
	 * @return
	 */
	boolean isDateField() default false;

	/**
	 * 格式化字符串
	 * 
	 * @return
	 */
	String pattern() default R.Pattern.DATE_FORMAT_DEFAULT;

}
