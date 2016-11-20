package com.zescs.dossier.model.permissions.domain;

public enum Level {
	MAIN(1, "主要权限"), /* 用户的主要权限 */
	FUN(2, "功能权限"), /* 用户的功能权限 */
	DC(3, "数据权限")/* 数据权限 */;
	private Integer index;

	private String displayName;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	private Level(Integer index, String displayName) {
		this.index = index;
		this.displayName = displayName;
	}

}
