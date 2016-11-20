package com.zescs.dossier.common.pagination;

import java.io.Serializable;

public interface Pageable extends Serializable{
	/**
	 * 获取分页页码
	 * @return
	 */
	Integer getPageIndex();
	/**
	 * 获取分页的大小
	 * @return
	 */
	Integer getPageSize();
}
