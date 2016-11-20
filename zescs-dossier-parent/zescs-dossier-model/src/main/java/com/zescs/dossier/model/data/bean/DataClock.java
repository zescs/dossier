/*
 * 
 */
package com.zescs.dossier.model.data.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: DataClock
 * @Description: TODO()
 * @author zescs 建平
 * @date 2016年11月8日 下午12:21:07
 *
 */
public class DataClock implements Serializable {
	private static final long serialVersionUID = -3804133638135757597L;
	
	private Integer systemClock;

	private String clockName;

	private String clocKey;

	private String clockValue;

	private Integer displayOrder;

	private Integer type;

	private Date createDate;

	private String remark;

	public Integer getSystemClock() {
		return systemClock;
	}

	public void setSystemClock(Integer systemClock) {
		this.systemClock = systemClock;
	}

	public String getClockName() {
		return clockName;
	}

	public void setClockName(String clockName) {
		this.clockName = clockName;
	}

	public String getClocKey() {
		return clocKey;
	}

	public void setClocKey(String clocKey) {
		this.clocKey = clocKey;
	}

	public String getClockValue() {
		return clockValue;
	}

	public void setClockValue(String clockValue) {
		this.clockValue = clockValue;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public DataClock() {
	}
}