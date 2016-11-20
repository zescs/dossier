package com.zescs.dossier.common.pagination;

import com.zescs.dossier.common.pagination.domain.OrderType;

public final class OrderBuilder {

	public static Order build(String property, OrderType type) {
		Order order = new Order();
		order.setProperty(property);
		order.setOrderType(type.getQueryString());
		return order;
	}

}
