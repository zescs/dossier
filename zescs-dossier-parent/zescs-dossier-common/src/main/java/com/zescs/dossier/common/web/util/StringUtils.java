package com.zescs.dossier.common.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import com.zescs.dossier.common.web.date.DateUtils;

/**
 * 
 * @ClassName: StringUtil
 * @Description: TODO()
 * @author
 * @date 2014年12月8日 下午8:23:34
 *
 */
public final class StringUtils {
	private static final class StringUtilsHolder {
		private static StringUtils instance = new StringUtils();
	}

	public static StringUtils getInstance() {
		return StringUtilsHolder.instance;
	}

	public String genStr(String username, Date date, String fileName) {
		return username + DateUtils.format(date, "yyyyMMddHHmmss") + fileName;
	}

	public String encode(String text, String encoding) {
		try {
			return URLEncoder.encode(text, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return text;
	}

	public String encode(String text) {
		return encode(text, "UTF-8");
	}

	public Boolean isNotNull(String data) {
		if (data != null && !data.equals("") && !data.equals("null") && data.trim().length() > 0) {
			return true;
		}
		return false;
	}

	public String getLikeQueryString(String value) {
		if (this.isNotNull(value)) {
			return "%" + value.trim() + "%";
		} else {
			return null;
		}
	}

	public static boolean isNotEmpty(String value) {
		return value != null && value.trim().length() > 0 && !value.toLowerCase().equals("null");
	}

	public static String join(String[] source, String separator) {
		StringBuffer sb = new StringBuffer();
		for (String temp : source) {
			sb.append("\"" + temp + "\"");
			sb.append(separator);
		}
		// 删除最后一个分隔符
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
