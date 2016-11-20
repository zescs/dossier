package com.zescs.dossier.common.pagination;

/**
 * 
 * @ClassName: PageContext
 * @Description: TODO(分页上下文对象)
 * @author
 * @date 2015年6月24日 下午9:44:12
 *
 */
public final class PaginContext {
	private static ThreadLocal<Pageable> pageable = new ThreadLocal<Pageable>();

	public static Pageable getPageable() {
		return pageable.get();
	}

	public static void setPageable(Pageable page) {
		pageable.set(page);
	}

	public static void remove() {
		pageable.remove();
	}

}
