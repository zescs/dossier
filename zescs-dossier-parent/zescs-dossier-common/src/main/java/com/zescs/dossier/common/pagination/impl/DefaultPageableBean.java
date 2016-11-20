package com.zescs.dossier.common.pagination.impl;

import com.zescs.dossier.common.pagination.Pageable;

public class DefaultPageableBean implements Pageable {
	private static final long serialVersionUID = -6298486633989187757L;
	private Integer pageIndex;
	private Integer pageSize;

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public Integer getPageIndex() {
		return pageIndex;
	}

	@Override
	public Integer getPageSize() {
		return pageSize;
	}

	public DefaultPageableBean(Integer pageIndex, Integer pageSize) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

}
