package com.zescs.dossier.common.web.date.domain;

import java.io.Serializable;
import java.util.Date;

public class DateEntity implements Serializable {
	private static final long serialVersionUID = -1214145502537346234L;
	private Date firstDate;/* 第一天 */
	private Date lastDate;/* 第一天 */

	public Date getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DateEntity() {

	}

	public DateEntity(Date firstDate, Date lastDate) {
		this.firstDate = firstDate;
		this.lastDate = lastDate;
	}
}
