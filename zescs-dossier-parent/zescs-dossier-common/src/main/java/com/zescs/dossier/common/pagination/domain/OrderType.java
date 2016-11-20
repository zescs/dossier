package com.zescs.dossier.common.pagination.domain;

public enum OrderType {
	DESC, ASC;
	public String getQueryString() {
		switch (this) {
		case DESC:
			return "DESC";
		default:
			return "ASC";
		}
	}
}
