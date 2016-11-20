package com.zescs.dossier.model.permissions.domain;

public enum MenuSerial {
	ARCHIVES(1,"档案"), RETRIEVE(2,"检索"), MENU(3,"菜单"), SYSTEM(4,"系统"), LOG(5,"日志");
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

	private MenuSerial(Integer index, String displayName) {
		this.index = index;
		this.displayName = displayName;
	}

}
