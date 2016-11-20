package com.zescs.dossier.model.user.domain;

/**
 * 学历
 */
public enum Diploma {
	/** 小学 */
	PRIMARYSCHOOL(1, "小学"),
	/** 初中 */
	JUNIORHIGHTSCHOOL(2, "初中"),
	/** 高中 */
	SENIORHIGHSCHOOL(3, "高中"),
	/** 大专 */
	JUNIORCOLLEGE(4, "大专"),
	/** 本科 */
	UNDERGRADUATE(5, "本科"),
	/** 硕士 */
	MASTER(6, "硕士"),
	/** 博士 */
	LEARNED(7, " 博士");
	
	private Integer index;
	
	private String displayName;

	public Integer getIndex() {
		return index;
	}

	public String getDisplayName() {
		return displayName;
	}

	private Diploma(Integer index, String displayName) {
		this.index = index;
		this.displayName = displayName;
	}

	public String getDiplomaText() {
		switch (this) {
		case PRIMARYSCHOOL:
			return "小学";
		case JUNIORHIGHTSCHOOL:
			return "初中";
		case SENIORHIGHSCHOOL:
			return "高中";
		case JUNIORCOLLEGE:
			return "大专";
		case UNDERGRADUATE:
			return "本科";
		case MASTER:
			return "硕士";
		case LEARNED:
			return "博士";
		default:
			return "其它";
		}
	}

	public static Diploma getDiplomaByText(String text) {
		switch (text) {
		case "小学":
			return PRIMARYSCHOOL;
		case "初中":
			return JUNIORHIGHTSCHOOL;
		case "高中":
			return SENIORHIGHSCHOOL;
		case "大专":
			return JUNIORCOLLEGE;
		case "本科":
			return UNDERGRADUATE;
		case "硕士":
			return MASTER;
		default:
			return LEARNED;
		}
	}
}
