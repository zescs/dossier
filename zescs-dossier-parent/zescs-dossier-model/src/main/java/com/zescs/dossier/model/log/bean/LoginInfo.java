package com.zescs.dossier.model.log.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: LoginInfo
 * @Description: TODO(用户登录信息)
 * @author zescs.com 郑建平
 * @date 2016年11月3日 下午8:21:37
 *
 */
public class LoginInfo implements Serializable {
	private static final long serialVersionUID = -5512368907475481677L;

	private Integer infoId;

	private Integer userId;

	private Date loginDate=new Date();

	private String ip;

	private String address;

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LoginInfo() {
	}

	public LoginInfo(Integer userId,String ip) {
		this.userId = userId;
		this.ip = ip;
	}
}