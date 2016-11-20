package com.zescs.dossier.model.permissions.domain;

public enum MenuType {
	FUN(1, "功能菜单"), ARCHIVES(2, "档案菜单");
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

	private MenuType(Integer index, String displayName) {
		this.index = index;
		this.displayName = displayName;
	}
}
