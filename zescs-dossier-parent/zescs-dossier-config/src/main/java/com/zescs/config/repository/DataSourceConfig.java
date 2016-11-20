package com.zescs.config.repository;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @ClassName: DataSourceConfig
 * @Description: TODO(数据源配置)
 * @author 郑建平
 * @date 2015年12月4日 下午1:53:38
 *
 */
@Configuration
// 加载资源文件
@PropertySource({ "classpath:/config/persistent/jdbc.properties" })
public class DataSourceConfig {
	private static final Logger logger = Logger.getLogger(DataSourceConfig.class);
	/*
	 * 绑定资源属性
	 */
	@Value("${jdbc.driverClass}")
	private String driverClass;
	@Value("${jdbc.jdbcUrl}")
	private String jdbcUrl;
	@Value("${jdbc.user}")
	private String userName;
	@Value("${jdbc.password}")
	private String passWord;
	@Value("${jdbc.maxPoolSize}")
	private String maxPoolSize;
	@Value("${jdbc.minPoolSize}")
	private String minPoolSize;
	@Value("${jdbc.initialPoolSize}")
	private String initialPoolSize;

	@Bean(name = "dataSource", destroyMethod = "close")
	public DataSource dataSource() {
		try {
			logger.info("DataSource");
			DruidDataSource ds = new DruidDataSource();
			ds.setDriverClassName(driverClass);
			ds.setUrl(jdbcUrl);
			ds.setUsername(userName);
			ds.setPassword(passWord);
			ds.setInitialSize(Integer.parseInt(initialPoolSize.trim()));
			ds.setMinIdle(Integer.parseInt(minPoolSize.trim()));
			ds.setMaxActive(Integer.parseInt(maxPoolSize.trim()));
			ds.setTestOnBorrow(false);
			ds.setTestOnReturn(false);
			ds.setTestWhileIdle(true);
			ds.setTimeBetweenEvictionRunsMillis(60000);
			ds.setMinEvictableIdleTimeMillis(25200000);
			ds.setRemoveAbandoned(true);
			ds.setRemoveAbandonedTimeout(1800);
			ds.setFilters("mergeStat");
			return ds;
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
}
