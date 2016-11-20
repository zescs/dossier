package com.zescs.dossier.model.permissions.domain;

/**
 * 
 * @ClassName: Position
 * @Description: TODO(系统角色枚举)
 * @author john
 * @date 2014年8月9日 下午3:22:45
 * @defaultResourdeName: Position
 */
@SuppressWarnings("static-access")
public enum Position {
	/* 系统开发者 */
	DEVELOPER(1, "系统开发人员"),
	/* 超级管理员 */
	SUPERADMIN(2, "超级管理员"),
	/* 系统管理员 */
	ADMINISTRATOR(3, "系统管理员"),
	/* 查询管理员 */
	SALESMAN(4, "查询管理员"),
	/* 档案管理员 */
	ARCHIVESMANAGER(5, "档案管理员"),
	/* 普通用户 */
	USER(6, "普通用户"),
	/* 著录用户 */
	RECORD(7, "著录用户");

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

	private Position(Integer index, String displayName) {
		this.index = index;
		this.displayName = displayName;
	}

	public Position getValue(Integer value) {
		for (Position p : this.values()) {
			if (p.ordinal() == value.intValue()) {
				return p;
			}
		}
		return null;
	}

	public String getSecurityValue() {
		return "ROLE_" + this.name();
	}
}
