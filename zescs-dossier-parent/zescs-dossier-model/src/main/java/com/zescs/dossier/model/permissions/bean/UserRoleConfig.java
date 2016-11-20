package com.zescs.dossier.model.permissions.bean;

import java.io.Serializable;
import java.util.Date;

import com.zescs.dossier.model.permissions.domain.Level;
import com.zescs.dossier.model.user.bean.User;

/**
 * 
 * @ClassName: UserRoleConfig
 * @Description: TODO(用户角色对应表)
 * @author zescs.com 郑建平
 * @date 2016年11月3日 上午1:11:30
 *
 */
public class UserRoleConfig implements Serializable {
	private static final long serialVersionUID = 5670875903170908744L;
	//
	private Integer configId;
	//
	private Integer userId;
	// 用户
	private User user;
	//
	private Integer roleId;
	// 角色
	private Role role;
	// 层级
	private Level level;

	private Date createDate;

	private String remark;

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
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
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UserRoleConfig() {
	}
}