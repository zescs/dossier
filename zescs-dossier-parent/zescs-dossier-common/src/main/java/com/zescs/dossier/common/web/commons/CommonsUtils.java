package com.zescs.dossier.common.web.commons;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.zescs.dossier.common.web.action.ActionContext;
import com.zescs.dossier.common.web.action.ApplicationServletContext;
import com.zescs.dossier.config.R;

@SuppressWarnings("unchecked")
public final class CommonsUtils {
	// 随机字符串
	private static final String SOURCE_CODE = "qwertyuioplkj_hgfdsazxcvbnm012345678-9";

	public static String getRandomCode(Integer length) {
		if (length == null) {
			length = SOURCE_CODE.length() / 2;
		}
		if (length > SOURCE_CODE.length()) {
			length = SOURCE_CODE.length() / 2;
		}
		if (length <= 0) {
			length = SOURCE_CODE.length() / 2;
		}
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int number = rand.nextInt(SOURCE_CODE.length());
			sb.append(SOURCE_CODE.charAt(number));
		}
		return sb.toString().toUpperCase();
	}

	public static <T> T mapToBean(Map<String, Object> map, Class<?> clazz) {
		try {
			T bean = (T) clazz.newInstance();
			/*
			 * 将数据封装到bean中
			 */
			BeanUtils.populate(bean, map);
			/*
			 * 返回
			 */
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @Title: close
	 * @Description: TODO(关闭流)
	 * @param streams
	 */
	public static <T extends Closeable> void close(T... streams) {
		for (T stream : streams) {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @Title: createFormCode
	 * @Description: TODO()
	 * @return
	 */
	public static String createFormCode() {
		return createFormCode(null);
	}

	/**
	 * 
	 * @Title: createFormCode
	 * @Description: TODO()
	 * @param length
	 * @return
	 */
	public static String createFormCode(Integer length) {
		// 根据当前session中的用户名，当前时间，任意4个数字加字符
		String source = "*-+qwertyuioplkjhgfdsazxcvbnm0123456789";
		if (length == null) {
			length = source.length() / 2;
		}
		if (length > source.length()) {
			length = source.length() / 2;
		}
		if (length <= 0) {
			length = source.length() / 2;
		}
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int number = rand.nextInt(source.length());
			sb.append(source.charAt(number));
		}
		sb.append(getUUID());
		return sb.toString();
	}

	/**
	 * 
	 * @Title: getUUID
	 * @Description: TODO(获取UUID)
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	/**
	 * 
	 * @Title: setFormCode
	 * @Description: TODO()
	 * @param request
	 * @return
	 */
	public static String setFormCode(HttpServletRequest request) {
		if (request == null) {
			request = ActionContext.get();
		}
		// 清除session
		request.getSession().removeAttribute(R.ProJectConfig.FORM_SESSION_CODE);
		String fromCode = createFormCode();
		// 写入request
		request.setAttribute(R.ProJectConfig.FORM_CODE, fromCode);
		// 重新写入
		request.getSession().setAttribute(R.ProJectConfig.FORM_SESSION_CODE, fromCode);
		return fromCode;
	}

	/**
	 * 
	 * @Title: setFormCode
	 * @Description: TODO()
	 * @return
	 */
	public static String setFormCode() {
		return setFormCode(null);
	}

	/**
	 * 获取为null的属性的名称
	 * 
	 * @param source
	 * @return
	 */
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	/**
	 * 合并多个数组
	 * 
	 * @param first
	 * @param rest
	 * @return
	 */
	public static <T> T[] concatAll(T[] first, T[]... rest) {
		int totalLength = first.length;
		for (T[] array : rest) {
			totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (T[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}
	/**
	 * 
	 * @Title: getContextPath
	 * @Description: TODO(获取文件的绝对路径)
	 * @param request
	 * @param dir
	 * @return
	 */
	public static String getContextPath(String dir) {
		try {
			return ApplicationServletContext.getServletContext().getRealPath(dir);
		} catch (Throwable t) {
			return Thread.currentThread().getContextClassLoader().getResource("/").getPath().replace("WEB-INF/classes/",
					dir);
		}
	}
}
