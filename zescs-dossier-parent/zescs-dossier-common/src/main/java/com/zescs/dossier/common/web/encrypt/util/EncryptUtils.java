package com.zescs.dossier.common.web.encrypt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * 
 * @ClassName: EncrypUtils
 * @Description: TODO(加密工具类)
 * @author Lambert
 * @date 2015年2月10日 上午10:52:02
 * 
 */
public final class EncryptUtils {
	/**
	 * 
	 * @Title: encryptMD5
	 * @Description: TODO(MD5加密)
	 * @param source
	 * @return
	 */
	public static byte[] encryptMD5(byte[] source) {
		byte[] result = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			result = md5.digest(source);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @Title: encryptMD5
	 * @Description: TODO(MD5加密)
	 * @param source
	 * @return
	 */
	public static String encryptMD5Hex(byte[] source) {
		return Hex.encodeHexString(encryptMD5(source));
	}

	/**
	 * 
	 * @Title: encryptSHA
	 * @Description: TODO(SHA加密)
	 * @param source
	 * @return
	 */
	public static byte[] encryptSHA(byte[] source) {
		byte[] result = null;
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA");
			result = sha.digest(source);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @Title: encryptSHA
	 * @Description: TODO(SHA加密)
	 * @param source
	 * @return
	 */
	public static String encryptSHAHex(byte[] source) {
		return Hex.encodeHexString(encryptSHA(source));
	}

	/**
	 * 
	 * @Title: encryptPassword
	 * @Description: TODO(对要入库的数据的不可逆加密)
	 * @param source
	 * @return
	 */
	public static String encryptData(byte[] source) {
		return Hex.encodeHexString(encryptSHA(encryptMD5(source)));
	}
}
