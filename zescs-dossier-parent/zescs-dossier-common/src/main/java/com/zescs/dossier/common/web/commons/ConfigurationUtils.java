package com.zescs.dossier.common.web.commons;

import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.configuration.EnvironmentConfiguration;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zescs.dossier.config.R;

/**
 * 
 * @ClassName: ConfigUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author
 * @date 2015年7月2日 上午10:28:34
 *
 */
public final class ConfigurationUtils {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ConfigurationUtils.class);

	private static final class ConfigurationUtilsHolder {
		private static final ConfigurationUtils instance = new ConfigurationUtils();
	}

	private ConfigurationUtils() {

	}

	public static ConfigurationUtils getInstance() {
		return ConfigurationUtilsHolder.instance;
	}

	/**
	 * 
	 * @Title: getPropertiesConfiguration
	 * @Description: TODO(根据配置文件名称获取属性配置)
	 * @param name
	 * @return
	 */
	public CompositeConfiguration getPropertiesConfiguration(String name) {
		CompositeConfiguration config = null;
		try {
			config = new CompositeConfiguration();
			config.addConfiguration(new PropertiesConfiguration(name
					+ ".properties"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			config = null;
		}
		return config;
	}

	/**
	 * 
	 * @Title: getXmlConfiguration
	 * @Description: TODO)
	 * @param name
	 * @return
	 */
	public CompositeConfiguration getXmlConfiguration(String name) {
		CompositeConfiguration config = null;
		try {
			config = new CompositeConfiguration();
			config.addConfiguration(new XMLConfiguration(name + ".xml"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			config = null;
		}
		return config;
	}

	/**
	 * 
	 * @Title: getIniConfiguration
	 * @Description: TODO()
	 * @param name
	 *            不带后缀名
	 * @return
	 */
	public CompositeConfiguration getIniConfiguration(String name) {
		CompositeConfiguration config = null;
		try {
			config = new CompositeConfiguration();
			config.addConfiguration(new HierarchicalINIConfiguration(name
					+ ".ini"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			config = null;
		}
		return config;
	}

	/**
	 * 
	 * @Title: getEnvConfiguration
	 * @Description: TODO(获取环境变量)
	 * @return
	 */
	public CompositeConfiguration getEnvConfiguration() {
		CompositeConfiguration config = null;
		try {
			config = new CompositeConfiguration();
			config.addConfiguration(new EnvironmentConfiguration());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			config = null;
		}
		return config;
	}

	/**
	 * 
	 * @Title: getConfig
	 * @Description: TODO(获取配置文件信息)
	 * @param name
	 *            文件全名 带后缀名
	 * @return
	 * @throws ConfigurationException
	 */
	public Configuration getConfig(String name) {
		try {
			DefaultConfigurationBuilder builder = getConfigurationBuilder();
			boolean load = true;
			CombinedConfiguration config = builder.getConfiguration(load);
			config.setExpressionEngine(new XPathExpressionEngine());
			if (StringUtils.isEmpty(name)) {
				return config;
			} else {
				return config.getConfiguration(name);
			}
		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	private DefaultConfigurationBuilder getConfigurationBuilder() {
		DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder();
		builder.setFileName(R.Config_Path.CONFIGURATION_NAME);
		return builder;
	}

	/**
	 * 
	 * @Title: getConfig
	 * @Description: TODO(获取环境变量)
	 * @return
	 */
	public Configuration getConfig() {
		return getConfig(null);
	}
}
