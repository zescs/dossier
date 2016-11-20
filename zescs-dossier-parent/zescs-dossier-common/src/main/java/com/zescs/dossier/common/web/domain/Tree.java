package com.zescs.dossier.common.web.domain;

import java.io.Serializable;

public class Tree implements Serializable {
	private static final long serialVersionUID = 7579851738234661450L;
	private String id; /* ID */
	private String displayName; /* 显示的名称 一个父菜单下不允许出现相同的节点 */
	private Integer displayOrder; /* 显示的顺序 */
	private String url; /* URL地址 */
	private Boolean expanded = null; /* 是否展开 */
	private Boolean isLeaf = null; /* 是否是叶子菜单 */
	private Integer hierarchy = 1;/* 层级 */
	private String img;// 菜单图标样式

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Tree() {
	}
}
