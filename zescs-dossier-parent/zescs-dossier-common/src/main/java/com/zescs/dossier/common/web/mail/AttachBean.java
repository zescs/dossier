package com.zescs.dossier.common.web.mail;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: AttachBean
 * @Description: TODO(附件信息)
 * @author 郑建平
 * @date 2015年1月5日 下午5:08:19
 *
 */
public class AttachBean {
	private Map<String, File> map = new HashMap<String, File>();

	public AttachBean() {
	}

	public AttachBean(String fileName, File attach) {
		map.put(fileName, attach);
	}

	public Map<String, File> getMap() {
		return map;
	}

	public void setMap(Map<String, File> map) {
		this.map = map;
	}

	public void put(String fileName, File file) {
		map.put(fileName, file);
	}

	public void remove(String fileName) {
		this.getMap().remove(fileName);
	}
}
