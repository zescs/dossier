package com.zescs.dossier.model.permissions.bean;

import java.io.Serializable;

import com.zescs.dossier.model.permissions.domain.MenuSerial;
import com.zescs.dossier.model.permissions.domain.MenuType;

/**
 * 
 * @ClassName: Menu
 * @Description: TODO(功能菜单)
 * @author zescs.com 郑建平
 * @date 2016年11月3日 上午1:00:18
 *
 */
public class Menu implements Serializable {
	private static final long serialVersionUID = -3901333715557578014L;

	private Integer menuId;
	// 显示名称
	private String displayName;
	// 代码
	private String code;
	// 显示顺序
	private Integer displayOrder;
	// 跳转地址
	private String url;
	// 是否显示
	private Boolean display;
	// 是否展开
	private Boolean expanded;
	// 叶子
	private Boolean isLeaf;
	// 层级 系统默认三级
	private Integer hierarchy;
	// 图片
	private String img;
	// 菜单类型
	private MenuType menuType = MenuType.FUN;
	/* 代码标记方便查找 */
	private MenuSerial serial = MenuSerial.MENU;
	// 备注
	private String remark;

	private Integer parentId;
	// 当前节点的父节点
	private Menu parent;

	public Menu() {

	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getDisplay() {
		return display;
	}

	public void setDisplay(Boolean display) {
		this.display = display;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Integer getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public MenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}

	public MenuSerial getSerial() {
		return serial;
	}

	public void setSerial(MenuSerial serial) {
		this.serial = serial;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}