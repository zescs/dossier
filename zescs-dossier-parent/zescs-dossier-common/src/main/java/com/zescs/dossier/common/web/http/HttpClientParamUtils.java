package com.zescs.dossier.common.web.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @ClassName: ParamUtils
 * @Description: TODO()
 * @author zjp
 * @date 2014年11月23日 下午4:21:41
 *
 */
public final class HttpClientParamUtils {

	public static String buildURL(String url) {
		if (!url.startsWith("http://")) {
			url = "http://" + url;
		}
		return url;
	}

	public static UrlEncodedFormEntity buildUrlEncodedFormEntity(
			Map<String, Object> params, String character)
			throws UnsupportedEncodingException {
		List<BasicNameValuePair> formParams = new ArrayList<BasicNameValuePair>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String value = String.valueOf(entry.getValue());
			if (value != null) {
				formParams.add(new BasicNameValuePair(entry.getKey(), value));
			}
		}
		return new UrlEncodedFormEntity(formParams, character);
	}

	public static HttpPost buildHeader(HttpPost post,
			Map<String, String> headers) {
		if (headers != null && headers.size() > 0) {
			for (String key : headers.keySet()) {
				post.addHeader(key, headers.get(key));
			}
		}
		return post;
	}

	public static String buildURL(String url, Map<String, Object> params,
			String character) throws ParseException,
			UnsupportedEncodingException, IOException {
		url = buildURL(url);
		if (params != null && params.size() > 0) {
			// 拼装参数
			String tag = null;
			if (!url.contains("?")) {
				tag = "?";
			} else {
				tag = "&";
			}
			url += tag
					+ EntityUtils.toString(buildUrlEncodedFormEntity(params,
							character));
		}
		return url;
	}

	public static HttpGet buildHeader(HttpGet get, Map<String, String> headers) {
		if (headers != null && headers.size() > 0) {
			for (String key : headers.keySet()) {
				get.addHeader(key, headers.get(key));
			}
		}
		return get;
	}

	public static HttpEntity buildUEntity(Map<String, Object> params)
			throws FileNotFoundException {
		if (params != null && params.size() > 0) {
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			for (String key : params.keySet()) {
				Object value = params.get(key);
				ContentBody body = null;
				if (value instanceof InputStream) {
					InputStream stream = (InputStream) value;
					body = new InputStreamBody(stream,"fileName");
				} else if (value instanceof File) {
					File file = (File) value;
					body = new InputStreamBody(new FileInputStream(file),
							file.getName());
				} else {
					if (value == null) {
						value = "";
					}
					body = new StringBody(value.toString(),
							ContentType.TEXT_PLAIN);
				}
				builder.addPart(key, body);
			}
			return builder.build();
		}
		return null;
	}
}
