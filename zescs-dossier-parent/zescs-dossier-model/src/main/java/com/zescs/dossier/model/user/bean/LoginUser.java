package com.zescs.dossier.model.user.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: LoginUser
 * @Description: TODO(登录用户信息)
 * @author 郑建平
 * @date 2016年10月30日 上午11:40:12
 *
 */
public class LoginUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 449694845678199796L;
	private Integer userId;// 用户ID
	private String userName;// 用户名
	private String nickName;// 昵称
	private Integer organizationId;// 部门Id
	private String organizationName;// 部门名称
	private String roleName;
	private Integer roleId;
	private String avatarPath;// 头像路径

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LoginUser() {
	}

	public LoginUser(Integer userId, String userName, String nickName, Integer organizationId, String organizationName,
			String roleName, Integer roleId, String avatarPath) {
		this.userId = userId;
		this.userName = userName;
		this.nickName = nickName;
		this.organizationId = organizationId;
		this.organizationName = organizationName;
		this.roleName = roleName;
		this.roleId = roleId;
		this.avatarPath = avatarPath;
	}
}
