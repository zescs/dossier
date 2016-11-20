package com.zescs.dossier.common.web.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 
 * @ClassName: ReflectionUtils
 * @Description: TODO(反射工具类)
 * @author zjp
 * @date 2014年11月1日 下午5:44:52
 *
 */
public final class ReflectionUtils {
	/**
	 * 
	 * @Title: getGenericClass
	 * @Description: TODO(获取该抽象类或者父类的实际类型)
	 * @param clazz
	 * @param index
	 * @return
	 */
	public static Class<?> getGenericClass(Class<?> clazz, int index) {
		Type type = clazz.getGenericSuperclass();
		Class<?> entityClass = null;
		// 判断当前类型时是否是ParameterizedType
		if (!(type instanceof ParameterizedType)) {
			entityClass = Object.class;
		} else {
			Type[] types = ((ParameterizedType) type).getActualTypeArguments();
			if (index < types.length && index >= 0
					&& (types[index] instanceof Class)) {
				entityClass = (Class<?>) types[index];
			}

		}
		return entityClass;
	}

	/**
	 * 
	 * @Title: getGenericClass
	 * @Description: TODO(获取该抽象类或者父类的实际类型)
	 * @param clazz
	 * @param index
	 * @return
	 */
	public static Class<?> getGenericClass(Class<?> clazz) {
		return getGenericClass(clazz, 0);
	}
}
