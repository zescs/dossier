package com.zescs.config.initializer;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(4)
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return true;
	}
}