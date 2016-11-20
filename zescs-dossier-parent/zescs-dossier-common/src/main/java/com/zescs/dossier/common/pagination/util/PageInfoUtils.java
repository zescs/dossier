package com.zescs.dossier.common.pagination.util;

import java.util.List;

import com.github.pagehelper.PageInfo;

public final class PageInfoUtils {

	public static <T> PageInfo<T> buildPageInfo(List<T> datas) {
		return new PageInfo<T>(datas);
	}
	
}
