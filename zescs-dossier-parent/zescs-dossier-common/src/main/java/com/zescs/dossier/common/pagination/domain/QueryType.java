package com.zescs.dossier.common.pagination.domain;

public enum QueryType {
	AND, OR;
	public String getQueryString() {
		switch (this) {
		case AND:
			return "AND";
		default:
			return "OR";
		}
	}
}
