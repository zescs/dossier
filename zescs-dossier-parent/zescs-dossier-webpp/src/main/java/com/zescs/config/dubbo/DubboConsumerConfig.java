/*
 * Copyright 2006-2014 handu.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zescs.config.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.zescs.dossier.config.R;

/**
 * @author Jinkai.Ma
 */
@Configuration
public class DubboConsumerConfig {
	private static final Logger LOG = LoggerFactory.getLogger(DubboConsumerConfig.class);
	public static final String APPLICATION_NAME = "zescs-dossier-webpp";

	@Bean
	public static AnnotationBean annotationBean() {
		LOG.info("dubbo:annotationBean");
		AnnotationBean annotationBean = new AnnotationBean();
		annotationBean.setPackage(R.App.DUBBO_ANNOTATION_PACKAGE);
		return annotationBean;
	}

	@Bean
	public ApplicationConfig applicationConfig() {
		LOG.info("dubbo:applicationConfig");
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName(APPLICATION_NAME);
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
		protocolConfig.setPort(20880);
		protocolConfig.setThreadpool("fixed");
		protocolConfig.setThreads(100);
		protocolConfig.setAccepts(1000);
		// 序列化方式
		protocolConfig.setSerialization(R.App.DUBBO_SERIALIZATION);
		return protocolConfig;
	}
}
