package com.zescs.dossier.common.web.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zescs.dossier.common.web.commons.CommonsUtils;
import com.zescs.dossier.common.web.commons.ConfigurationUtils;
import com.zescs.dossier.common.web.guava.GuavaUtils;
import com.zescs.dossier.config.R;

/**
 * 
 * @author admin
 *
 */
public final class FtpUtils {
	private static final String DEAFULT_REMOTE_CHARSET = "gb2312";
	private static final String DEAFULT_LOCAL_CHARSET = "UTF-8";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(FtpUtils.class);
	private static FtpUtils instance = null;
	// 当前客户端
	private static FTPClient client = null;
	private static Lock lock = new ReentrantLock();
	private static Configuration config = null;

	private FtpUtils() {
	}

	public static FtpUtils getInstance() {
		if (instance == null) {
			try {
				lock.lock();
				if (instance == null) {
					instance = new FtpUtils();
					init();
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	private static void init() {
		client = new FTPClient();
		FTPClientConfig ftpClientConfig = new FTPClientConfig();
		ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());
		client.configure(ftpClientConfig);
		config = ConfigurationUtils.getInstance().getConfig(R.Config_Path.FTP);
		// 设置超时时间
		client.setDataTimeout(config.getInt(R.Config.Ftp.DATATIMEOUT));
		// 设置默认编码
		client.setControlEncoding(config.getString(R.Config.Ftp.ENCODING));
		// 设置默认端口
		client.setDefaultPort(config.getInt(R.Config.Ftp.PORT));
		// 设置是否显示隐藏文件
		client.setListHiddenFiles(config.getBoolean(R.Config.Ftp.ISHIDDENFILES));
		// 缓冲区大小
		client.setBufferSize(1024 * 3);
	}

	private Boolean login() {
		try {
			// 链接
			conn();
			int reply = client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				try {
					client.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
				throw new RuntimeException("ftp连接异常");
			}
			// 登录
			client.login(config.getString(R.Config.Ftp.USER),
					config.getString(R.Config.Ftp.PASSWORD));
			// 设置传输协议
			client.enterLocalPassiveMode();
			// 设置传输类型
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			return true;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static Boolean conn() {
		try {
			client.connect(config.getString(R.Config.Ftp.IP),
					config.getInt(R.Config.Ftp.PORT));
			return true;
		} catch (Exception e) {
			throw new RuntimeException(("FTP:"+config.getString(R.Config.Ftp.IP))+"::连接ftp服务器失败");
		}
	}

	/**
	 * 关闭服务器的连接
	 * 
	 * @return
	 */
	public static boolean ftpLogOut() {
		if (null != client && client.isConnected()) {
			try {
				return client.logout();// 退出FTP服务器
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					client.disconnect();// 关闭FTP服务器的连接
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param in
	 *            当前输入流
	 * @param remoteFilePath
	 *            远程服务器上的相对路径
	 * @param fileName
	 *            上传之后的文件名
	 * @return
	 */
	public UploadStatus upload(InputStream in, String dir, String remote) {
		try {
			//登录
			login();
			// 设置PassiveMode传输
			client.enterLocalPassiveMode();
			// 设置以二进制流的方式传输
			client.setFileType(FTP.BINARY_FILE_TYPE);
			if (createDirecroty(dir) == UploadStatus.CREATE_DIRECTORY_FAIL) {
				return UploadStatus.CREATE_DIRECTORY_FAIL;
			}
			// 检查该文件是否存在
			FTPFile[] files = client.listFiles(new String((dir + remote)
					.getBytes(DEAFULT_REMOTE_CHARSET), DEAFULT_LOCAL_CHARSET));
			if (files.length != 0) {
				return UploadStatus.FILE_EXISTS;
			}
			return client.storeFile(remote, in) ? UploadStatus.UPLOAD_SUCCESS
					: UploadStatus.UPLOAD_FAIL;
		} catch (Exception e) {
			return UploadStatus.UPLOAD_ERROR;
		} finally {
			CommonsUtils.close(in);
			ftpLogOut();
		}
	}

	public UploadStatus createDirecroty(String dir) throws IOException {
		UploadStatus status = UploadStatus.CREATE_DIRECTORY_SUCCESS;
		String directory = dir.substring(0, dir.lastIndexOf("/") + 1);
		if (!directory.equalsIgnoreCase("/")
				&& !client.changeWorkingDirectory(new String(directory
						.getBytes(DEAFULT_REMOTE_CHARSET),
						DEAFULT_REMOTE_CHARSET))) {
			// 上传设置
			if (dir.indexOf("/") == 0) {
				client.changeWorkingDirectory("/");
			}
			String subdir = new String();
			StringTokenizer st = new StringTokenizer(dir, "/");
			while (st.hasMoreTokens()) {
				subdir = st.nextToken();
				if (!(client.changeWorkingDirectory(subdir))) {
					if (!(client.makeDirectory(subdir))) {
						int rc = client.getReplyCode();
						if (((rc != 550) && (rc != 553) && (rc != 521))) {
							LOGGER.error(client.getReplyString());
							return UploadStatus.CREATE_DIRECTORY_FAIL;
						}
					} else {
						client.changeWorkingDirectory(subdir);
					}
				}
			}
			// 将路劲切换到最后一级别
			List<String> list = GuavaUtils.Splitter(dir, "/");
			client.changeWorkingDirectory(list.get(list.size() - 1));
		}
		return status;
	}

	/**
	 * 
	 * @param relativePath
	 * @param fileName
	 * @param out
	 */
	public void download(String relativePath, String fileName, OutputStream out) {
		try {
			client.changeWorkingDirectory(relativePath);
			client.retrieveFile((relativePath + fileName), out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
