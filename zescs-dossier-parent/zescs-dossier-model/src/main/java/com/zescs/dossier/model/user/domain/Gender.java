package com.zescs.dossier.model.user.domain;

/**
 * 性别
 */
public enum Gender {
	/** 男性 */
	MALE(1, "男"),
	/** 女性 */
	FEMALE(2, "女");
	private Integer index;
	private String displayName;

	public Integer getIndex() {
		return index;
	}

	public String getDisplayName() {
		return displayName;
	}

	private Gender() {
	}

	private Gender(Integer index, String displayName) {
		this.index = index;
		this.displayName = displayName;
	}

}
