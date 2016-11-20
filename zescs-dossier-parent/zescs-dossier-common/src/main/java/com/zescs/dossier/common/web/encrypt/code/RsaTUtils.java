package com.zescs.dossier.common.web.encrypt.code;

import java.security.InvalidParameterException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

import javax.crypto.Cipher;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zescs.dossier.common.web.encrypt.rsa.RSAUtils;
import com.zescs.dossier.common.web.encrypt.util.Coder;

public class RsaTUtils {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RSAUtils.class);
	/** 算法名称 */
	private static final String ALGORITHOM = "RSA";
	/** 密钥大小 */
	private static final int KEY_SIZE = 1024;
	/** 默认的安全服务提供者 */
	private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();
	private static KeyPairGenerator keyPairGen = null;
	private static KeyFactory keyFactory = null;
	/** 缓存的密钥对。 */
	private static KeyPair oneKeyPair = null;
	/**
	 * 签名
	 */
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	// 初始化
	static {
		try {
			keyPairGen = KeyPairGenerator.getInstance(ALGORITHOM,
					DEFAULT_PROVIDER);
			keyFactory = KeyFactory.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
		} catch (NoSuchAlgorithmException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	private static synchronized KeyPair generateKeyPair(String info) {
		try {
			if (info == null || info.trim().length() == 0) {
				info = DateFormatUtils.format(new Date(), "yyyyMMdd");
			}
			keyPairGen.initialize(KEY_SIZE, new SecureRandom(info.getBytes()));
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
	 * 返回RSA密钥对。
	 */
	public static KeyPair getKeyPair(String info) {
		return generateKeyPair(info);
	}

	public static KeyPair getKeyPair() {
		return generateKeyPair(null);
	}

	/** 返回已初始化的默认的公钥。 */
	public static RSAPublicKey getDefaultPublicKey() {
		KeyPair keyPair = getKeyPair();
		if (keyPair != null) {
			return (RSAPublicKey) keyPair.getPublic();
		}
		return null;
	}

	/** 返回已初始化的默认的私钥。 */
	public static RSAPrivateKey getDefaultPrivateKey() {
		KeyPair keyPair = getKeyPair();
		if (keyPair != null) {
			return (RSAPrivateKey) keyPair.getPrivate();
		}
		return null;
	}

	/**
	 * 获取字符串形式的公钥
	 * 
	 * @return
	 */
	public static String getPublicKeyStr() {
		Key key = getDefaultPublicKey();
		byte[] publicKey = key.getEncoded();
		try {
			return Coder.encryptBASE64(publicKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 生成私钥
	public static String getPrivateKeyStr() {
		Key key = getDefaultPrivateKey();
		byte[] privateKey = key.getEncoded();
		try {
			return Coder.encryptBASE64(privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 公钥加密
	// 私钥解密

	// 私钥签名
	public static String sign(byte[] data, String privateKey) throws Exception {
		// 解密私钥
		byte[] keyBytes = Coder.decryptBASE64(privateKey);
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		// 指定加密算法
		// 取私钥匙对象
		PrivateKey pk = keyFactory
				.generatePrivate(pkcs8EncodedKeySpec);
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(pk);
		signature.update(data);
		return Coder.encryptBASE64(signature.sign());
	}
   // 公钥验签
	/**
     * 用公钥加密
     * @param data  加密数据
     * @param key   密钥
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data,String key)throws Exception{
        //对公钥解密
        byte[] keyBytes = Coder.decryptBASE64(key);
        //取公钥
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }
    /**
     * 用私钥解密<span style="color:#000000;"></span> * @param data  加密数据
     * @param key   密钥
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data,String key)throws Exception{
        //对私钥解密
        byte[] keyBytes = Coder.decryptBASE64(key);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        //对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }
	/**
     * 校验数字签名
     * @param data  加密数据
     * @param publicKey 公钥
     * @param sign  数字签名
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data,String publicKey,String sign)throws Exception{
        //解密公钥
        byte[] keyBytes = Coder.decryptBASE64(publicKey);
        //构造X509EncodedKeySpec对象
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        //取公钥匙对象
        PublicKey pk = keyFactory.generatePublic(x509EncodedKeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pk);
        signature.update(data);
        //验证签名是否正常
        return signature.verify(Coder.decryptBASE64(sign));
    }
}
