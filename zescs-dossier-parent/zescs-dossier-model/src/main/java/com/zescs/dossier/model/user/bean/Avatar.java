package com.zescs.dossier.model.user.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: Avatar
 * @Description: TODO(头像类)
 * @author zescs.com 郑建平
 * @date 2016年11月1日 下午6:11:49
 *
 */
public class Avatar implements Serializable {
	private static final long serialVersionUID = 4299153041011477875L;

	private Integer avatarId;

	private String avatarName;

	private String avatarPath;

	private String sourceFileName;

	private String sourceFilePath;

	private Date createDate;

	private String avatarSize;

	public Integer getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(Integer avatarId) {
		this.avatarId = avatarId;
	}

	public String getAvatarName() {
		return avatarName;
	}

	public void setAvatarName(String avatarName) {
		this.avatarName = avatarName == null ? null : avatarName.trim();
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath == null ? null : avatarPath.trim();
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName == null ? null : sourceFileName.trim();
	}

	public String getSourceFilePath() {
		return sourceFilePath;
	}

	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath == null ? null : sourceFilePath.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getAvatarSize() {
		return avatarSize;
	}

	public void setAvatarSize(String avatarSize) {
		this.avatarSize = avatarSize == null ? null : avatarSize.trim();
	}
}