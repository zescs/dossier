package com.zescs.dossier.model.permissions.bean;

import java.io.Serializable;
import java.util.Date;

import com.zescs.dossier.model.permissions.domain.Position;
/**
 * 
* @ClassName: Role 
* @Description: TODO(角色) 
* @author zescs.com 郑建平 
* @date 2016年11月3日 上午12:53:36 
*
 */
public class Role implements Serializable {
	private static final long serialVersionUID = -5943434675097070624L;
	
	private Integer roleId;
	//角色名称
	private Position position;
	//角色名称
	private String roleName;
	//角色类型
	private Integer roleType;
	//创建时间
	private Date createDate;
	//备注
	private String remark;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
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

	public Role() {
	}
}