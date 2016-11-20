package com.zescs.dossier.common.web.license;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.prefs.Preferences;

import org.springframework.stereotype.Component;

import com.zescs.dossier.config.R;

import de.schlichtherle.license.CipherParam;
import de.schlichtherle.license.DefaultCipherParam;
import de.schlichtherle.license.DefaultKeyStoreParam;
import de.schlichtherle.license.DefaultLicenseParam;
import de.schlichtherle.license.KeyStoreParam;
import de.schlichtherle.license.LicenseContent;
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;

/**
 * VerifyLicense
 * 
 * @author melina
 */
@Component("verifyLicense")
public class VerifyLicense {
	// common param
	private static String PUBLICALIAS = "";
	private static String STOREPWD = "";
	private static String SUBJECT = "";
	private static String licPath = "";
	private static String pubPath = "";
	private LicenseContent content;
	private LicenseManager licenseManager;

	public LicenseManager getLicenseManager() {
		return licenseManager;
	}

	public LicenseContent getContent() {
		return content;
	}

	public void setParam(String propertiesPath) {
		// 获取参数
		Properties prop = new Properties();
		InputStream in = VerifyLicense.class
				.getResourceAsStream(propertiesPath);
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PUBLICALIAS = prop.getProperty("PUBLICALIAS");
		STOREPWD = prop.getProperty("STOREPWD");
		SUBJECT = prop.getProperty("SUBJECT");
		licPath = prop.getProperty("licPath");
		pubPath = prop.getProperty("pubPath");
	}

	/**
	 * 安装证书
	 * 
	 * @return
	 */
	public Boolean install() {
		/************** 证书使用者端执行 ******************/
		// 安装证书
		try {
			content = licenseManager.install(new File(licPath));
			return true;
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * 验证证书
	 * 
	 * @return
	 */
	public boolean verify() {
		// 验证证书
		try {
			content = licenseManager.verify();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 返回验证证书需要的参数
	private static LicenseParam initLicenseParams() {
		Preferences preference = Preferences
				.userNodeForPackage(VerifyLicense.class);
		CipherParam cipherParam = new DefaultCipherParam(STOREPWD);
		KeyStoreParam privateStoreParam = new DefaultKeyStoreParam(
				VerifyLicense.class, pubPath, PUBLICALIAS, STOREPWD, null);
		LicenseParam licenseParams = new DefaultLicenseParam(SUBJECT,
				preference, privateStoreParam, cipherParam);
		return licenseParams;
	}

	/**
	 * 初始化
	 */
	public VerifyLicense() {
		setParam(R.Config_Path.LICENSE_VERIFY_PATH);
		licenseManager = LicenseManagerHolder
				.getLicenseManager(initLicenseParams());
	}
}