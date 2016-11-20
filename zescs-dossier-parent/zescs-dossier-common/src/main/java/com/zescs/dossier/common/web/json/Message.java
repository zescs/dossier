package com.zescs.dossier.common.web.json;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: Message
 * @Description: TODO()
 * @author zjp
 * @date 2014年11月19日 下午12:55:55
 * 
 */
public class Message implements Serializable {
	private static final long serialVersionUID = 3493047527403540717L;
	/**
	 * 操作标示
	 */
	private Boolean flag;
	/**
	 * 
	 */
	private Boolean success;
	/**
	 * 返回消息
	 */
	private String message;
	/**
	 * 通用标记0成功 1失败
	 */
	private int result;
	/**
	 * 表单验证码
	 */
	private String formCode;
	/**
	 * 数据
	 */
	private Object data;
	/**
	 * 文件上传是否成功
	 */
	private int error;
	/**
	 * 成功之后的图片地址
	 */
	private String url;
	/**
	 * map数据
	 */
	private Map<String, Object> map = new HashMap<String, Object>();

	// ////////////////////////////////////////////
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	private Message() {

	}

	private Message(Boolean flag, String message) {
		this.flag = flag;

		if (flag == null) {
			flag = false;
		}

		if (flag) {
			this.setResult(0);
			setSuccess(true);
			this.error = 0;
		} else {
			this.setResult(1);
			this.error = 1;
			setSuccess(false);
		}
		this.message = message;
	}

	private Message(int error, String url) {
		this.error = error;
		this.url = url;
	}

	private Message(Boolean flag, String message, int error, String url) {
		this(flag, message);
		this.error = error;
		this.url = url;
	}

	private Message(Boolean flag, String message, String formCode) {
		this(flag, message);
		this.formCode = formCode;
	}

	private Message(Boolean flag, String message, Map<String, Object> map) {
		this(flag, message);
		this.map = map;
	}

	private Message(Boolean flag, String message, String formCode, Map<String, Object> map) {
		this(flag, message, formCode);
		this.map = map;
	}

	public static synchronized Message newInstance(Boolean flag, String message) {
		return new Message(flag, message);
	}

	public static synchronized Message newInstance(int error, String url) {
		return new Message(error, url);
	}

	public static synchronized Message newInstance(Boolean flag, String message, int error, String url) {
		return new Message(flag, message, error, url);
	}

	public static synchronized Message newInstance(Boolean flag, String message, String formCode) {
		return new Message(flag, message, formCode);
	}

	public static synchronized Message newInstance(Boolean flag, String message, Map<String, Object> map) {
		return new Message(flag, message, map);
	}

	public static synchronized Message newInstance(Boolean flag, String message, String formCode,
			Map<String, Object> map) {
		return new Message(flag, message, formCode, map);
	}

}
