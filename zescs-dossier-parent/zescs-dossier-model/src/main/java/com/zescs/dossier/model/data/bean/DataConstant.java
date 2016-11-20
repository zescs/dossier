package com.zescs.dossier.model.data.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: DataConstant
 * @Description: TODO()
 * @author zescs 建平
 * @date 2016年11月8日 下午12:32:38
 *
 */
public class DataConstant implements Serializable {
	private static final long serialVersionUID = -8634593076774906151L;

	private Integer systemConfigId;

	private String sysKey;

	private String sysValue;

	private Boolean enableState;

	private Boolean flush;

	private Date createDate;

	private String remark;

	public Integer getSystemConfigId() {
		return systemConfigId;
	}

	public void setSystemConfigId(Integer systemConfigId) {
		this.systemConfigId = systemConfigId;
	}

	public String getSysKey() {
		return sysKey;
	}

	public void setSysKey(String sysKey) {
		this.sysKey = sysKey;
	}

	public String getSysValue() {
		return sysValue;
	}

	public void setSysValue(String sysValue) {
		this.sysValue = sysValue;
	}

	public Boolean getEnableState() {
		return enableState;
	}

	public void setEnableState(Boolean enableState) {
		this.enableState = enableState;
	}

	public Boolean getFlush() {
		return flush;
	}

	public void setFlush(Boolean flush) {
		this.flush = flush;
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

	public DataConstant() {
	}
}