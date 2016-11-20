package com.zescs.config.repository;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.zescs.dossier.config.R;

/**
 * 
 * @ClassName: DaoConfig
 * @Description: TODO(数据持久层配置)
 * @author 郑建平
 * @date 2015年12月4日 下午1:54:03
 *
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages={R.App.BASEPACKAGES_MAPPER})
@Import({ DataSourceConfig.class })
public class DaoConfig {
	private static final Logger logger = Logger.getLogger(DaoConfig.class);
	@Autowired
	private DataSource dataSource;

	@Bean("sqlSessionFactory")
	public SqlSessionFactory buildSqlSessionFactory() throws Exception {
		logger.info("buildSqlSessionFactoryBean");
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		// 延迟加载
		configuration.setLazyLoadingEnabled(true);
		configuration.setAggressiveLazyLoading(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		// 设置数据源
		sqlSessionFactoryBean.setDataSource(dataSource);
		// 类型别名扫描包
		sqlSessionFactoryBean.setTypeAliasesPackage(R.App.TYPEALIASESPACKAGE);
		// 设置插件
		List<Interceptor> plugins_lists = Lists.newArrayList();
		// 分页插件
		PageHelper pageHelper = new PageHelper();
		Properties pageHelper_properties = new Properties();
		pageHelper_properties.setProperty("dialect", "mysql");
		pageHelper_properties.setProperty("offsetAsPageNum", "true");
		pageHelper_properties.setProperty("rowBoundsWithCount", "true");
		pageHelper_properties.setProperty("pageSizeZero", "true");
		pageHelper_properties.setProperty("reasonable", "true");
		pageHelper_properties.setProperty("supportMethodsArguments", "true");
		pageHelper_properties.setProperty("returnPageInfo", "check");
		pageHelper.setProperties(pageHelper_properties);
		plugins_lists.add(pageHelper);
		Interceptor[] plugins = new Interceptor[plugins_lists.size()];
		plugins = plugins_lists.toArray(plugins);
		sqlSessionFactoryBean.setPlugins(plugins);
		//
		return sqlSessionFactoryBean.getObject();
	}

	// 事物管理器
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager() {
		logger.info("transactionManager");
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}
}
