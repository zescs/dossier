package com.zescs.dossier.model.user.bean;

import java.io.Serializable;
import java.util.Date;

import com.zescs.dossier.model.user.domain.Diploma;
import com.zescs.dossier.model.user.domain.Gender;
import com.zescs.dossier.model.user.domain.OnlineState;
import com.zescs.dossier.model.user.domain.Status;
/**
 * 
* @ClassName: User
* @Description: TODO(用户类)
* @author 郑建平
* @date 2016年10月30日 上午11:42:37
*
 */
public class User implements Serializable {
	private static final long serialVersionUID = -5398605987947956048L;

	private Integer userId;
	//部门id
	private Integer organizationId;
	// 部门
	private Organization organization;
	//头像id
	private Integer avatarId;
	// 头像
	private Avatar avatar;
	// 用户名
	private String userName;
	// 昵称
	private String nickName;
	// 密码
	private String password;
	// 电话
	private String phone;
	// 邮件
	private String email;
	// 姓名
	private String name;
	// 性别
	private Gender gender;
	// 生日
	private Date birthday;
	// 地址
	private String address;
	// 创建日期
	private Date createDate;
	// 身份证号
	private String idCard;
	// 学历
	private Diploma diploma;
	// 毕业学校
	private String schoolName;
	// 专业
	private String professional;
	// 名族
	private String ethnic;
	/* 用户系统激活状态 */
	private Status status = Status.FREEZE;
	// 在线状态
	private OnlineState onlineState;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Integer getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(Integer avatarId) {
		this.avatarId = avatarId;
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Diploma getDiploma() {
		return diploma;
	}

	public void setDiploma(Diploma diploma) {
		this.diploma = diploma;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public OnlineState getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(OnlineState onlineState) {
		this.onlineState = onlineState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User() {
	}
}