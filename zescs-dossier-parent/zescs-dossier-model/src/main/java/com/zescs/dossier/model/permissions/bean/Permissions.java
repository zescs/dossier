package com.zescs.dossier.model.permissions.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: Permissions
 * @Description: TODO(权限)
 * @author zescs.com 郑建平
 * @date 2016年11月3日 上午1:09:48
 *
 */
public class Permissions implements Serializable {
	private static final long serialVersionUID = -1122009739021622236L;

	private Integer permissionsId;
	//
	private Integer roleId;
	// 角色
	private Role role;
	//
	private Integer menuId;
	// 菜单
	private Menu menu;
	//
	private Date createDate;

	public Integer getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(Integer permissionsId) {
		this.permissionsId = permissionsId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Permissions() {
	}
}