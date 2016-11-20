package com.zescs.dossier.common.pagination;

import java.io.Serializable;

public class Order implements Serializable {
	private static final long serialVersionUID = 7874037532627603713L;
	private String property;
	private String orderType;

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Order() {
	}
}
