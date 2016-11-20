package com.zescs.dossier.common.pagination;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Lists;

public class Condition implements Serializable {
	private static final long serialVersionUID = 3264999062734565360L;
	private List<Criteria> criterias;
	private LinkedList<Order> orders;
	private Boolean distinct;

	public Condition() {
		criterias = Lists.newArrayList();
		orders = Lists.newLinkedList();
	}

	public Condition(Boolean distinct) {
		this();
		this.distinct = distinct;
	}

	public Boolean getDistinct() {
		return distinct;
	}

	public void setDistinct(Boolean distinct) {
		this.distinct = distinct;
	}

	public List<Criteria> getCriterias() {
		return criterias;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void addCriteria(Criteria criteria) {
		if (criteria != null) {
			criterias.add(criteria);
		}
	}

	public void addOrder(Order order) {
		if (order != null) {
			orders.add(order);
		}
	}

	public void addOrder(LinkedList<Order> order) {
		if (order != null) {
			orders.addAll(order);
		}

	}
}
