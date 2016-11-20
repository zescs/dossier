package com.zescs.config.dubbo;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboProviderConfig extends BaseDubboConfig {

	public static final String APPLICATION_NAME = "zescs-dossier-service-log";

	@Override
	public String getName() {
		return APPLICATION_NAME;
	}

	@Override
	public Integer getPort() {
		return 20885;
	}

}