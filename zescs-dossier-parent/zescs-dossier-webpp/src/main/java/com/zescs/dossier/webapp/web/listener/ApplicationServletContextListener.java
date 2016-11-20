package com.zescs.dossier.webapp.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zescs.dossier.common.web.action.ApplicationServletContext;
import com.zescs.dossier.config.R;

/**
 * 
 * @ClassName: ApplicationServletContextListener
 * @Description: TODO(系统启动监听器,要放在spring容器启动之后执行)
 * @author 郑建平
 * @date 2015年12月4日 下午4:37:11
 *
 */
public class ApplicationServletContextListener implements ServletContextListener {
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationServletContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try {
			LOG.info("系统结束==============================");
		} catch (Exception e) {
			LOG.info("系统结束异常发生::" + e.getMessage());
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			LOG.info("系统初始化开始初始化");
			LOG.info("======================================");
			ServletContext servletContext = event.getServletContext();
			ApplicationServletContext.setServletContext(servletContext);
			// 系统访问路径
			servletContext.setAttribute(R.SystemConfig.CONTEXT_PTH, servletContext.getContextPath());
			servletContext.setAttribute(R.SystemConfig.CTX, servletContext.getContextPath());
			//分页
			servletContext.setAttribute(R.ConfigMap.Key.APP_PAGESIZE,R.ConfigMap.Value.DEFAULT_PAGESIZE);
			//文件服务器路径
			//文件服务器操作接口
			//solr接口
			//日期格式
			servletContext.setAttribute(R.ConfigMap.Key.APP_DATE_FORMAT,R.Pattern.DATE_FORMAT_DEFAULT);
			LOG.info("======================================");
			LOG.info("系统初始化成功......");
		} catch (Exception e) {
			LOG.info("初始化异常::" + e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}

}
