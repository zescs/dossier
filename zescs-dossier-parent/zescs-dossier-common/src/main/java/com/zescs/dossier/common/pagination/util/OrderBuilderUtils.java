package com.zescs.dossier.common.pagination.util;

import java.util.LinkedList;

import com.google.common.collect.Lists;
import com.zescs.dossier.common.pagination.Order;
import com.zescs.dossier.common.pagination.OrderBuilder;
import com.zescs.dossier.common.pagination.domain.OrderType;
import com.zescs.dossier.common.web.util.StringUtils;

public class OrderBuilderUtils {
	
	public static LinkedList<Order> getOrderBy(String sortField, String sortOrder) {
		LinkedList<Order> orderBy = Lists.newLinkedList();
		if (StringUtils.isNotEmpty(sortField)) {
			
			OrderType type = OrderType.valueOf(sortOrder.toUpperCase());
			orderBy.add(OrderBuilder.build(sortField,type));
		}
		return orderBy;
	}
}
