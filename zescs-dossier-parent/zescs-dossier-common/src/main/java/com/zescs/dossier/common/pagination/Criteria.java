package com.zescs.dossier.common.pagination;

import java.io.Serializable;

public class Criteria implements Serializable {
	private static final long serialVersionUID = -2572335374641876408L;
	private String property;// 查询属性
	private Object value;// 对应值 单个值
	private Object startValue;// 区间开始值
	private Object endValue;// 区间结束值
	private String expression;// 条件表达式
	private String connection;// 链接表达式
	private Boolean isIn = false;// 是否是in查询
	private Boolean betweenValue = false;// 是否区间查询
	private Boolean singleValue = false;// 单个值

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getStartValue() {
		return startValue;
	}

	public void setStartValue(Object startValue) {
		this.startValue = startValue;
	}

	public Object getEndValue() {
		return endValue;
	}

	public void setEndValue(Object endValue) {
		this.endValue = endValue;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public Boolean getIsIn() {
		return isIn;
	}

	public void setIsIn(Boolean isIn) {
		this.isIn = isIn;
	}

	public Boolean getBetweenValue() {
		return betweenValue;
	}

	public void setBetweenValue(Boolean betweenValue) {
		this.betweenValue = betweenValue;
	}

	public Boolean getSingleValue() {
		return singleValue;
	}

	public void setSingleValue(Boolean singleValue) {
		this.singleValue = singleValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Criteria() {

	}
}
