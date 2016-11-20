package com.zescs.config.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.zescs.dossier.config.R;

public abstract class BaseDubboConfig {
	private static final Logger LOG = LoggerFactory.getLogger(BaseDubboConfig.class);
	/**
	 * 设置包名
	 * @return
	 */
	public abstract String getName();
	
	/**
	 * 设置端口
	 * @return
	 */
	public abstract Integer getPort();
	
	@Bean
	public ApplicationConfig applicationConfig() {
		LOG.info("dubbo:applicationConfig");
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName(getName());
		return applicationConfig;
	}

	@Bean
	public RegistryConfig registryConfig() {
		LOG.info("dubbo:registryConfig");
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setAddress(R.App.DUBBO_REGISTRY_ADDRESS);
		return registryConfig;
	}

	@Bean
	public AnnotationBean annotationBean() {
		LOG.info("dubbo:annotationBean");
		AnnotationBean annotationBean = new AnnotationBean();
		annotationBean.setPackage(R.App.DUBBO_ANNOTATION_PACKAGE);
		return annotationBean;
	}

	@Bean
	public ProviderConfig providerConfig() {
		LOG.info("dubbo:providerConfig");
		ProviderConfig providerConfig = new ProviderConfig();
		providerConfig.setDelay(-1);
		providerConfig.setTimeout(10000);
		providerConfig.setRetries(2);
		return providerConfig;
	}

	@Bean
	public ProtocolConfig protocolConfig() {
		LOG.info("dubbo:protocolConfig");
		ProtocolConfig protocolConfig = new ProtocolConfig();
		protocolConfig.setName(R.App.DUBBO_SERVICE_PROTOCO_NAME);
		protocolConfig.setPort(getPort());
		protocolConfig.setThreadpool("fixed");
		protocolConfig.setThreads(100);
		protocolConfig.setAccepts(1000);
		return protocolConfig;
	}

	@Bean
	public ConsumerConfig consumerConfig() {
		LOG.info("dubbo:consumerConfig");
		ConsumerConfig consumerConfig = new ConsumerConfig();
		// 全部启动不依赖监察
		consumerConfig.setCheck(false);
		return consumerConfig;
	}

	@Bean
	public MonitorConfig monitorConfig() {
		LOG.info("dubbo:monitorConfig");
		MonitorConfig monitorConfig = new MonitorConfig();
		monitorConfig.setProtocol("registry");
		return monitorConfig;
	}
}
