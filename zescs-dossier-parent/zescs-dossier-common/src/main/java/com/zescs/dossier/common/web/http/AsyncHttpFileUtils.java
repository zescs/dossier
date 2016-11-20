package com.zescs.dossier.common.web.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.zescs.dossier.common.web.json.Message;
import com.zescs.dossier.config.R;

/**
 * 
 * @ClassName: HttpFileUtils
 * @Description: TODO()
 * @author
 * @date 2015年6月30日 下午3:44:48
 *
 */
public final class AsyncHttpFileUtils extends Thread {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AsyncHttpFileUtils.class);
	private static HttpClientUtils client = HttpClientUtils.getInstance();
	private String server_api_url;
	private String source;
	private String target;
	private Boolean pdf;
	private Boolean swf;

	@Override
	public void run() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("source", source);
		map.put("target", target);
		map.put("pdf", pdf);
		map.put("swf", swf);
		String result = client.postMulti(server_api_url
				+ R.Url.FILE_API_SERVER_MOVE, map);
		if (!StringUtils.isEmpty(result)) {
			Message json = JSON.parseObject(result, Message.class);
			LOGGER.info("上传结果::" + json.getFlag());
		}
	}

	public AsyncHttpFileUtils() {
	}

	public AsyncHttpFileUtils(String server_api_url, String source,
			String target, Boolean pdf, Boolean swf) {
		this.server_api_url = server_api_url;
		this.source = source;
		this.target = target;
		this.pdf = pdf;
		this.swf = swf;
	}

}
