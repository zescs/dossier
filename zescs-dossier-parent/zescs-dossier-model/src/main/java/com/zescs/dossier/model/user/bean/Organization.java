package com.zescs.dossier.model.user.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: Organization
 * @Description: TODO(部门类)
 * @author zescs.com 郑建平
 * @date 2016年11月1日 下午6:12:13
 *
 */
public class Organization implements Serializable {
	private static final long serialVersionUID = 3184866222944078052L;

	private Integer organizationId;

	private String branch;

	private Integer userId;

	private Integer parentId;

	private Boolean expanded;

	private Boolean isLeaf;

	private Integer hierarchy;

	private Date createDate;

	private String remark;

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch == null ? null : branch.trim();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
}