package com.zescs.dossier.common.web.http;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: HttpUtils
 * @Description: TODO(http工具类)
 * @author zjp
 * @date 2014年11月7日 下午4:20:24
 *
 */
public final class HttpClientUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);
	private static HttpClientUtils httpUtils = null;
	/**
	 * 请求客户端
	 */
	private static CloseableHttpClient client = null;
	/**
	 * 字符编码
	 */
	private static String character = null;
	private static Lock lock = new ReentrantLock();

	private HttpClientUtils() {
	}

	public static HttpClientUtils getInstance(String character) {
		if (httpUtils == null) {
			try {
				lock.lock();
				if (httpUtils == null) {
					httpUtils = new HttpClientUtils();
					if (character == null) {
						character = "UTF-8";
					}
					HttpClientUtils.character = character;
					// RequestConfig config = RequestConfig.custom()
					// .setConnectTimeout(60000).setSocketTimeout(15000)
					// .build();
					// client = HttpClientBuilder.create()
					// .setDefaultRequestConfig(config).build();
					client = HttpClientBuilder.create().build();
					// client =HttpClients.createDefault();
				}
			} finally {
				lock.unlock();
			}
		}
		return httpUtils;
	}

	public static HttpClientUtils getInstance() {
		return getInstance(null);
	}

	/**
	 * 
	 * @Title: post
	 * @Description: TODO(post)
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 */
	public String post(String url, Map<String, Object> params, Map<String, String> headers) {
		// 拼装url
		url = HttpClientParamUtils.buildURL(url);
		HttpPost post = new HttpPost(url);
		try {
			HttpClientParamUtils.buildHeader(post, headers);
			if(params!=null && !params.isEmpty()){
				// 设置参数
				post.setEntity(HttpClientParamUtils.buildUrlEncodedFormEntity(params, character));
			}
			// 执行
			CloseableHttpResponse response = client.execute(post);
			// 判断是否执行成功
			int code = response.getStatusLine().getStatusCode();
			String result = null;
			if (code != 200) {
				throw new RuntimeException("http请求异常::code::" + code);
			}
			// 返回值
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, character);
			}
			EntityUtils.consume(entity);
			response.close();
			return result;
		} catch (Exception e) {
			LOGGER.error("POST请求异常:::" + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @Title: post
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param url
	 * @param params
	 * @return
	 */
	public String post(String url, Map<String, Object> params) {
		return post(url, params, null);
	}

	/**
	 * 
	 * @Title: get
	 * @Description: TODO(执行get方法)
	 * @param url
	 *            请求路径
	 * @param params
	 *            参数列表
	 * @param headers
	 *            请求头信息
	 * @return 执行该路径返回的信息
	 */
	public String get(String url, Map<String, Object> params, Map<String, String> headers) {
		try {
			// 拼装url
			url = HttpClientParamUtils.buildURL(url, params, character);
			// 创建请求对象
			HttpGet get = new HttpGet(url);
			// 设置头信息
			HttpClientParamUtils.buildHeader(get, headers);
			// 执行该请求
			CloseableHttpResponse response = client.execute(get);
			// 获取请求的返回值
			int statuCode = response.getStatusLine().getStatusCode();
			String result = null;
			if (statuCode == 200) {
				// 请求成功继续执行
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, character);
				}
				EntityUtils.consume(entity);
				response.close();
			} else {
				get.isAborted();
			}
			return result;
		} catch (Exception e) {
			LOGGER.error("get请求异常:::" + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @Title: get
	 * @Description: TODO(执行get方法)
	 * @param url
	 *            请求路径
	 * @param params
	 *            参数列表
	 * @param headers
	 *            请求头信息
	 * @return 执行该路径返回的信息
	 */
	public String get(String url, Map<String, Object> params) {
		return get(url, params, null);
	}

	public String postMulti(String url, Map<String, Object> params, Map<String, String> headers) {
		// 拼装url
		url = HttpClientParamUtils.buildURL(url);
		HttpPost post = new HttpPost(url);
		try {
			HttpClientParamUtils.buildHeader(post, headers);
			// 设置参数
			post.setEntity(HttpClientParamUtils.buildUEntity(params));
			// 执行
			CloseableHttpResponse response = client.execute(post);
			// 判断是否执行成功
			int code = response.getStatusLine().getStatusCode();
			String result = null;
			if (code != 200) {
				throw new RuntimeException("http请求异常::code::" + code);
			}
			// 返回值
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, character);
			}
			EntityUtils.consume(entity);
			response.close();
			return result;
		} catch (Exception e) {
			LOGGER.error("POST请求异常:::" + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public String postMulti(String url, Map<String, Object> params) {
		return postMulti(url, params, null);
	}

	/**
	 * 获取文件流
	 * 
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 */
	public InputStream getInputStream(String url, Map<String, Object> params, Map<String, String> headers) {
		try {
			InputStream input = null;
			// 拼装url
			url = HttpClientParamUtils.buildURL(url, params, character);
			// 创建请求对象
			HttpGet get = new HttpGet(url);
			// 设置头信息
			HttpClientParamUtils.buildHeader(get, headers);
			// 执行该请求
			CloseableHttpResponse response = client.execute(get);
			// 获取请求的返回值O
			int statuCode = response.getStatusLine().getStatusCode();
			if (statuCode == 200) {
				// 请求成功继续执行
				HttpEntity entity = response.getEntity();
				input = entity.getContent();
				// EntityUtils.consume(entity);
				// response.close();
			} else {
				get.isAborted();
			}
			return input;
		} catch (Exception e) {
			LOGGER.error("get请求异常:::" + e.getMessage());
			return null;
		}
	}

	/**
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public InputStream getInputStream(String url, Map<String, Object> params) {
		return getInputStream(url, params, null);
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public InputStream getInputStream(String url) {
		return getInputStream(url, null, null);
	}
}
