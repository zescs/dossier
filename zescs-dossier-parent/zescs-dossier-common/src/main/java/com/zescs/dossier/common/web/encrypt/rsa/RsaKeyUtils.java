package com.zescs.dossier.common.web.encrypt.rsa;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zescs.dossier.common.web.commons.ConfigurationUtils;
import com.zescs.dossier.common.web.encrypt.util.Coder;
import com.zescs.dossier.common.web.encrypt.util.RsaKeyMap;
import com.zescs.dossier.config.R;

import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class RsaKeyUtils {
	private static RSAPublicKey publicKey;
	private static RSAPrivateKey privateKey;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RSAUtils.class);
	/** 密钥大小 */
	private static final int KEY_SIZE = 1024;
	private static KeyPairGenerator keyPairGen = null;
	/** 缓存的密钥对。 */
	private static KeyPair oneKeyPair = null;
	static {
		// 加载公钥和私钥
		try {
			Configuration config = ConfigurationUtils.getInstance().getConfig(
					R.Config_Path.RSAKEY);
			publicKey = RsaKeyUtils.loadPublicKey(config
					.getString(R.SystemConfig.PUBLICK_KEY));
			privateKey = RsaKeyUtils.loadPrivateKey(config
					.getString(R.SystemConfig.PRIVATE_KEY));
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	private RsaKeyUtils() {
	}

	public static RSAPublicKey getPublicKey() {
		return publicKey;
	}

	public static RSAPrivateKey getPrivateKey() {
		return privateKey;
	}

	/**
	 * 
	 * @Title: loadPublicKey
	 * @Description: TODO(加载公钥)
	 * @param publicKeyStr
	 * @return
	 * @throws Exception
	 */
	public static RSAPublicKey loadPublicKey(String publicKeyStr)
			throws Exception {
		try {
			BASE64Decoder base64Decoder = new BASE64Decoder();
			byte[] buffer = base64Decoder.decodeBuffer(publicKeyStr);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			throw new Exception("公钥非法");
		} catch (IOException e) {
			throw new Exception("公钥数据内容读取错误");
		} catch (NullPointerException e) {
			throw new Exception("公钥数据为空");
		}
	}

	/**
	 * 加载私钥
	 * 
	 * @param privateKeyStr
	 * @throws Exception
	 */
	public static RSAPrivateKey loadPrivateKey(String privateKeyStr)
			throws Exception {
		try {
			BASE64Decoder base64Decoder = new BASE64Decoder();
			byte[] buffer = base64Decoder.decodeBuffer(privateKeyStr);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此算法");
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			throw new Exception("私钥非法");
		} catch (IOException e) {
			throw new Exception("私钥数据内容读取错误");
		} catch (NullPointerException e) {
			throw new Exception("私钥数据为空");
		}
	}

	// 根据用户的信息生成公钥和私钥
	private static synchronized KeyPair generateKeyPair(String info) {
		try {
			keyPairGen.initialize(KEY_SIZE, new SecureRandom(DateFormatUtils
					.format(new Date(), "yyyyMMdd").getBytes()));
			oneKeyPair = keyPairGen.generateKeyPair();
			return oneKeyPair;
		} catch (InvalidParameterException ex) {
			LOGGER.error("KeyPairGenerator does not support a key length of "
					+ KEY_SIZE + ".", ex);
		} catch (NullPointerException ex) {
			LOGGER.error(
					"RSAUtils#KEY_PAIR_GEN is null, can not generate KeyPairGenerator instance.",
					ex);
		}
		return null;
	}

	/**
	 * 根据用户的信息生成公钥和私钥字符串
	 * 
	 * @param info
	 * @return
	 */
	public RsaKeyMap createKey(String info) {
		try {
			KeyPair kp = generateKeyPair(info);
			RSAPublicKey publickKey = (RSAPublicKey) kp.getPublic();
			byte[] publicKeyBT = publickKey.getEncoded();
			String rePubKey = Coder.encryptBASE64(publicKeyBT);

			RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
			byte[] privateKeyBT = privateKey.getEncoded();
			String rePrivKey = Coder.encryptBASE64(privateKeyBT);
			return new RsaKeyMap(rePubKey, rePrivKey);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}

}
