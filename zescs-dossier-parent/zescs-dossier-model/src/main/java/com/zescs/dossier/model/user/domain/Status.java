package com.zescs.dossier.model.user.domain;

/**
 * 状态
 */
public enum Status {
	/** 冻结 */
	FREEZE(1, "冻结"),
	/** 解冻 */
	UNFREEZE(2, "启用");

	private Integer index;

	private String displayName;

	public Integer getIndex() {
		return index;
	}

	public String getDisplayName() {
		return displayName;
	}

	private Status() {
		
	}

	private Status(Integer index, String displayName) {
		this.index = index;
		this.displayName = displayName;
	}

	public String getStatusText() {
		if (this == FREEZE) {
			return "冻结";
		} else {
			return "启用";
		}
	}
}
