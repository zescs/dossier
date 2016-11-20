package com.zescs.dossier.webapp.web.multipart;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

import com.zescs.dossier.config.R;

/**
 * 
 * @ClassName: FileUploadProgressListener
 * @Description: TODO(文件上传监听器)
 * @author john
 * @date 2014年8月7日 上午11:16:08
 * 
 */
public class FileUploadProgressListener implements ProgressListener {
	/**
	 * 数据格式化
	 */
	private NumberFormat format = new DecimalFormat("0.00%");
	/**
	 * 临时大小
	 */
	private long tempSize = 0;
	/**
	 * 临时进度
	 */
	private String tempProgress = "";
	/**
	 * 当前session
	 */
	private HttpSession session;

	public HttpSession getSession() {
		return session;
	}

	protected void setSession(HttpSession session) {
		this.session = session;
	}

	public FileUploadProgressListener(HttpSession session) {
		setSession(session);
	}

	@Override
	public void update(long currentSize, long totalSize, int fileCount) {
		Double result = (double) currentSize / totalSize;
		String currentProgress = format.format(result);
		// 不让进度值重复
		if ((tempSize != currentSize)
				&& (!currentProgress.equals(tempProgress))) {
			// 防止session过期
			if (getSession() != null) {
				getSession().setAttribute(R.App.FILE_PROGRESS,
						format.format(result));
			}
			tempSize = currentSize;
			tempProgress = currentProgress;
		}
	}

}
