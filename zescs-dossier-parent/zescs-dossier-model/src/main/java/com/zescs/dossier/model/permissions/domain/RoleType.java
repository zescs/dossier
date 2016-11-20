package com.zescs.dossier.model.permissions.domain;

/**
 * 
 * @ClassName: RoleType
 * @Description: TODO(角色类型)
 * @author Lambert
 * @date 2015年1月29日 上午11:51:33
 *
 */
public enum RoleType {
	/**
	 * 后台角色
	 */
	ADMIN,
	/**
	 * 前台角色
	 */
	MEMBER;

	public String getText() {
		switch (this) {
		case ADMIN:
			return "后台";
		default:
			return "前台";
		}
	}
}
