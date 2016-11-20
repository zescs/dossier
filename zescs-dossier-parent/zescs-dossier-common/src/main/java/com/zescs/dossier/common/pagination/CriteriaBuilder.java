package com.zescs.dossier.common.pagination;

import com.zescs.dossier.common.pagination.domain.QueryModel;

public final class CriteriaBuilder {

	public static Criteria build(String property, Object value, QueryModel model) {
		if (model == null) {
			model = QueryModel.EQUAL;
		}
		Criteria criteria = new Criteria();
		criteria.setProperty(property);
		criteria.setValue(getValue(value, model));
		criteria.setExpression(model.getQueryString());
		if (model == QueryModel.IN || model == QueryModel.NOTIN) {
			criteria.setIsIn(true);
		} else {
			criteria.setSingleValue(true);
		}
		return criteria;
	}

	public static Criteria build(String property, Object startValue, Object endValue) {
		QueryModel model = QueryModel.BETWEEN;
		Criteria criteria = new Criteria();
		criteria.setProperty(property);
		criteria.setStartValue(startValue);
		criteria.setEndValue(endValue);
		criteria.setBetweenValue(true);
		criteria.setExpression(model.getQueryString());
		if (model == QueryModel.IN || model == QueryModel.NOTIN) {
			criteria.setIsIn(true);
		}
		return criteria;
	}

	public static Criteria build(String property, Object value) {
		return build(property, value, null);
	}

	private static Object getValue(Object value, QueryModel model) {
		if (model == QueryModel.LIKE || model == QueryModel.NOTLIKE) {
			value = "%" + String.valueOf(value) + "%";
		}
		return value;
	}
}
