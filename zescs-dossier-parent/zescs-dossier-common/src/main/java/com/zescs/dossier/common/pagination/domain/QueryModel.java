package com.zescs.dossier.common.pagination.domain;

public enum QueryModel {
	EQUAL, OR, IN, NOTIN, LIKE, NOTLIKE, ISNULL, NOTNULL, QL, GT, LT, GTEQ, LTEQ, NOTEQUAL, EMPTY,BETWEEN;
	public String getQueryString() {
		switch (this) {
		case EQUAL:
			return "=";
		case IN:
			return "IN";
		case NOTIN:
			return "NOT IN";
		case LIKE:
			return "LIKE";
		case NOTLIKE:
			return "NOT LIKE";
		case ISNULL:
			return "IS NULL";
		case NOTNULL:
			return "IS NOT NULL";
		case GT:
			return ">";
		case QL:
			return "<";
		case LT:
			return "<";
		case GTEQ:
			return ">=";
		case LTEQ:
			return "<=";
		case NOTEQUAL:
			return "!=";
		case OR:
			return "OR";
		default:
			return "";
		}
	}
}
