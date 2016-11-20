package com.zescs.dossier.common.web.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: PropertiesUtils
 * @Description: TODO(获取属性文件)
 * @author 郑建平
 * @date 2015年1月5日 下午9:01:29
 *
 */
public final class PropertiesUtils {
	/**
	 * 屬性文件集合
	 */
	private Map<String, Properties> map = new HashMap<String, Properties>();
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PropertiesUtils.class);

	private PropertiesUtils() {

	}

	private static final class PropertiesUtilsHolder {
		private static final PropertiesUtils instance = new PropertiesUtils();
	}

	public static PropertiesUtils getInstance() {
		return PropertiesUtilsHolder.instance;
	}

	/**
	 * 
	 * @Title: getProperties
	 * @Description: TODO(获取属性文件，注意属性文件不能带有后缀名)
	 * @param name
	 *            不带属性后缀名的属性文件
	 * @return
	 */
	public synchronized Properties getProperties(String name) {
		Properties properties = null;
		try {
			if (map.get(name) != null) {
				properties = map.get(name);
			} else {
				properties = new Properties();
				properties.load(this.getClass().getResourceAsStream(
						"/" + name + ".properties"));
				map.put(name, properties);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			properties = null;
		}
		return properties;
	}

	public void remove(String name) {
		map.remove(name);
	}
}
