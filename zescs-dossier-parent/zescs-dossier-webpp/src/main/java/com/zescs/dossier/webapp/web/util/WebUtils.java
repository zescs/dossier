package com.zescs.dossier.webapp.web.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.common.collect.Maps;
import com.zescs.dossier.common.web.action.ActionContext;
import com.zescs.dossier.common.web.action.ApplicationServletContext;
import com.zescs.dossier.config.R;

import sun.misc.BASE64Encoder;

/**
 * 
 * @ClassName: WebUtils
 * @Description: TODO()
 * @author 郑建平
 * @date 2014年7月1日 上午10:56:38
 * 
 */
@SuppressWarnings({ "restriction" })
public final class WebUtils {
	// 随机字符串
	private static final String SOURCE_CODE = "qwertyuioplkjhgfdsazxcvbnm0123456789";

	/**
	 * 
	 * @Title: getMap
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param keys
	 * @param values
	 * @return
	 */
	public static Map<String, Object> getMap(String[] keys, Object[] values) {
		if (keys != null && values != null && keys.length == values.length) {
			Map<String, Object> map = Maps.newHashMap();
			for (int i = 0, len = keys.length; i < len; i++) {
				map.put(keys[i], values[i]);
			}
			return map;
		}
		return null;
	}

	/**
	 * 
	 * @Title: createFormCode
	 * @Description: TODO()
	 * @return
	 */
	public static String createFormCode() {
		return createFormCode(null);
	}

	/**
	 * 
	 * @Title: createFormCode
	 * @Description: TODO()
	 * @param length
	 * @return
	 */
	public static String createFormCode(Integer length) {
		return WebUtils.getUUID() + getRandomCode(length);
	}

	// 获取随机的指定长度的字符串
	private static String getRandomCode(Integer length) {
		if (length == null) {
			length = SOURCE_CODE.length() / 2;
		}
		if (length > SOURCE_CODE.length()) {
			length = SOURCE_CODE.length() / 2;
		}
		if (length <= 0) {
			length = SOURCE_CODE.length() / 2;
		}
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int number = rand.nextInt(SOURCE_CODE.length());
			sb.append(SOURCE_CODE.charAt(number));
		}
		return sb.toString().toUpperCase();
	}

	private static String getRandomCode() {
		return getRandomCode(null);
	}

	/**
	 * 
	 * @Title: setFormCode
	 * @Description: TODO()
	 * @param request
	 * @return
	 */
	public static String setFormCode(HttpServletRequest request) {
		if (request == null) {
			request = ActionContext.get();
		}
		// 清除session
		request.getSession().removeAttribute(R.ProJectConfig.FORM_SESSION_CODE);
		String fromCode = WebUtils.createFormCode();
		// 写入request
		request.setAttribute(R.ProJectConfig.FORM_CODE, fromCode);
		// 重新写入
		request.getSession().setAttribute(R.ProJectConfig.FORM_SESSION_CODE, fromCode);
		return fromCode;
	}

	/**
	 * 
	 * @Title: setFormCode
	 * @Description: TODO()
	 * @return
	 */
	public static String setFormCode() {
		return setFormCode(null);
	}

	/**
	 * 
	 * @Title: checkFormCode
	 * @Description: TODO(检测session和request中的code值是否相同)
	 * @param request
	 * @return
	 */
	public static Boolean checkFormCode(HttpServletRequest request) {
		Boolean flag = false;
		// 获取request中的code
		String submitCode = request.getParameter(R.ProJectConfig.FORM_CODE);
		String sessionCode = (String) request.getSession().getAttribute(R.ProJectConfig.FORM_SESSION_CODE);
		// 检测是否相同
		if (submitCode != null && sessionCode != null) {
			if (submitCode.equals(sessionCode)) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 
	 * @Title: getContextPath
	 * @Description: TODO(获取文件的绝对路径)
	 * @param request
	 * @param dir
	 * @return
	 */
	public static String getContextPath(String dir) {
		try {
			return ApplicationServletContext.getServletContext().getRealPath(dir);
		} catch (Throwable t) {
			return Thread.currentThread().getContextClassLoader().getResource("/").getPath().replace("WEB-INF/classes/",
					dir);
		}
	}

	/**
	 * 
	 * @Title: getContextPath
	 * @Description: TODO(获取项目的路径)
	 * @param request
	 * @return
	 */
	public static String getContextPath() {
		return ApplicationServletContext.getServletContext().getRealPath("/");
	}

	/**
	 * 
	 * @Title: getIpAddr
	 * @Description: TODO(获取ip地址)
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip != null && ip.contains(",")) {
			ip = ip.substring(0, ip.indexOf(','));
		}
		if (ip != null && ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
			ip = "127.0.0.1";
		}
		return ip;
	}

	public static String getMACAddress(HttpServletRequest request) {
		String ip = getIpAddr(request);
		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 20; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC") > 1) {
						macAddress = str.substring(str.indexOf("=") + 1);
						if (macAddress != null) {
							macAddress = macAddress.trim();
						}
						break;
					}
				}
			}
		} catch (IOException e) {
		}
		return macAddress;
	}

	/**
	 * 
	 * @Title: getFile
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param par
	 * @return
	 */
	public static MultipartFile getMultipartFile(HttpServletRequest request, String par) {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile commFile = multipartRequest.getFile(par);
			return commFile;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * .
	 * 
	 * @Title: getCookie
	 * @Description: TODO(获取cookie)
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie != null && cookie.getName().equals(name.trim())) {
				return cookie;
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: getUUID
	 * @Description: TODO(获取UUID)
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	/**
	 * 
	 * @Title: getCurrentTimeMillis
	 * @Description: TODO(获取系统毫秒数)
	 * @return
	 */
	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 
	 * @Title: getFileName
	 * @Description: TODO(获取文件名)
	 * @param fileName
	 *            带扩展名的文件名
	 * @return
	 */
	public static String getFileName(String fileName) {
		return getUUID() + "_" + getRandomCode() + "." + FilenameUtils.getExtension(fileName);
	}

	/**
	 * 
	 * @Title: filenameEncoding
	 * @Description: TODO(处理下载文件乱码)
	 * @param filename
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String filenameEncoding(String filename, HttpServletRequest request) throws IOException {
		if (request == null) {
			request = ActionContext.get();
		}
		String agent = request.getHeader("User-Agent"); // 获取浏览器
		if (agent.contains("Firefox")) {
			BASE64Encoder base64Encoder = new BASE64Encoder();
			filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
		} else if (agent.contains("MSIE")) {
			filename = URLEncoder.encode(filename, "utf-8");
		} else {
			filename = URLEncoder.encode(filename, "utf-8");
		}
		return filename;
	}

	/**
	 * 
	 * @Title: filenameEncoding
	 * @Description: TODO(处理下载文件乱码)
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static String filenameEncoding(String filename) throws IOException {
		return filenameEncoding(filename, null);
	}

	public static WebApplicationContext getWebApplicationContext() {
		return WebApplicationContextUtils
				.getRequiredWebApplicationContext(ApplicationServletContext.getServletContext());
	}

	public static RequestContext getRequestContext() {
		return new RequestContext(ActionContext.get());
	}

	public static Locale getLocale() {
		return RequestContextUtils.getLocaleResolver(ActionContext.get()).resolveLocale(ActionContext.get());
	}

	/**
	 * 获取制定code的国际化资源消息
	 * 
	 * @param code
	 * @return
	 */
	public static String getMessage(String code) {
		return getRequestContext().getMessage(code);
	}

	public static boolean checkSession(HttpSession currSession, HttpSession targetSession, Boolean cluster) {
		String csId = currSession.getId();
		String tsId = targetSession.getId();
		if (csId.contains(".") && tsId.contains(".")) {
			String rcsId = csId.substring(0, csId.indexOf("."));
			String rtsId = tsId.substring(0, tsId.indexOf("."));
			return (rcsId.equals(rtsId));
		} else {
			return csId.equals(tsId);
		}
	}
}