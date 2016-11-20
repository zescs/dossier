package com.zescs.dossier.common.web.encrypt.rsa;

import java.security.Provider;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
/**
 * 
 * @ClassName: RsaSystemUtils
 * @Description: TODO()
 * @author Lambert
 * @date 2015年2月6日 下午7:29:04
 *
 */
public class RsaSystemUtils {
	/** 算法名称 */
	private static final String ALGORITHOM = "RSA";
	/** 默认的安全服务提供者 */
	private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();
	/**
	 * 系统默认公钥
	 */
	private static RSAPublicKey publicKey;
	/**
	 * 系统默认私钥
	 */
	private static RSAPrivateKey privateKey;
	/**
	 * 加载公钥和私钥
	 */
	static {
		publicKey = RsaKeyUtils.getPublicKey();
		privateKey = RsaKeyUtils.getPrivateKey();
	}

	private RsaSystemUtils() {
	}

	public static RSAPublicKey getPublicKey() {
		return publicKey;
	}

	public static RSAPrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * 
	 * @Title: encrypt
	 * @Description: TODO(使用系统的公钥对数据进行加密)
	 * @param plaintext
	 * @return
	 */
	public static String encrypt(String plaintext) {
		if (plaintext == null) {
			return null;
		}
		byte[] data = plaintext.getBytes();
		try {
			Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
			ci.init(Cipher.ENCRYPT_MODE, getPublicKey());
			byte[] en_data = ci.doFinal(data);
			return new String(Hex.encodeHex(en_data));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 
	 * @Title: encryptHex
	 * @Description: TODO(加密)
	 * @param plaintext
	 * @return
	 */
	public static String encryptHex(String plaintext) {
		return new String(Hex.encodeHex(encrypt(plaintext).getBytes()));
	}

	/**
	 * 
	 * @Title: decrypt
	 * @Description: TODO(使用系统的私钥解密数据)
	 * @param encrypttext
	 * @return
	 */
	public static String decrypt(String encrypttext) {
		if (StringUtils.isBlank(encrypttext)) {
			return null;
		}
		try {
			byte[] en_data = Hex.decodeHex(encrypttext.toCharArray());
			Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
			ci.init(Cipher.DECRYPT_MODE, getPrivateKey());
			byte[] data = ci.doFinal(en_data);
			return new String(data);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 
	 * @Title: decryptString
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param encrypttext
	 * @return
	 */
	public static String decryptHex(String encrypttext) {
		try {
			String source = new String(Hex.decodeHex(encrypttext.toCharArray()));
			return decrypt(source);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @Title: getPublicKeyMap
	 * @Description: TODO(所有数据都是转换为16进制的)
	 * @return
	 */
	public static PublicKeyMap getPublicKeyMap() {
		PublicKeyMap publicKeyMap = new PublicKeyMap();
		RSAPublicKey rsaPublicKey = getPublicKey();
		publicKeyMap.setModulus(new String(Hex.encodeHex(rsaPublicKey
				.getModulus().toByteArray())));
		publicKeyMap.setExponent(new String(Hex.encodeHex(rsaPublicKey
				.getPublicExponent().toByteArray())));
		return publicKeyMap;
	}
}
