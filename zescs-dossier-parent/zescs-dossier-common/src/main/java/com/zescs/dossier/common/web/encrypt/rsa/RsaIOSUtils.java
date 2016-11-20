package com.zescs.dossier.common.web.encrypt.rsa;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
@SuppressWarnings("restriction")
public class RsaIOSUtils {
	private static RSAPublicKey publicKey;
	private static RSAPrivateKey privateKey;
	static{
		//加载公钥和私钥
		try {
			publicKey=RsaKeyUtils.getPublicKey();
			privateKey=RsaKeyUtils.getPrivateKey();
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	private RsaIOSUtils() {
	}
	public static RSAPublicKey getPublicKey() {
		return publicKey;
	}
	public static RSAPrivateKey getPrivateKey() {
		return privateKey;
	}
	/**
	 * 
	 * @param base64String
	 * @return
	 * @throws Exception
	 */
	public static String decryptWithBase64(String base64String) throws Exception {
		byte[] binaryData = decrypt(getPrivateKey(),
				new BASE64Decoder().decodeBuffer(base64String));
		String string = new String(binaryData);
		return string;
	}
	/**
	 * 解密过程
	 * 
	 * @param privateKey
	 *            私钥
	 * @param cipherData
	 *            密文数据
	 * @return 明文
	 * @throws Exception
	 *             解密过程中的异常信息
	 */
	public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData)
			throws Exception {
		if (privateKey == null) {
			throw new Exception("解密私钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");// , new BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] output = cipher.doFinal(cipherData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此解密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("解密私钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("密文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("密文数据已损坏");
		}
	}
	public static String encryptWithBase64(String string) throws Exception {
		byte[] binaryData = encrypt(getPublicKey(), string.getBytes());
		String base64String = new BASE64Encoder().encodeBuffer(binaryData);
		return base64String;
	}
	/**
	 * 加密过程
	 * 
	 * @param publicKey
	 *            公钥
	 * @param plainTextData
	 *            明文数据
	 * @return
	 * @throws Exception
	 *             加密过程中的异常信息
	 */
	public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData)
			throws Exception {
		if (publicKey == null) {
			throw new Exception("加密公钥为空, 请设置");
		}
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] output = cipher.doFinal(plainTextData);
			return output;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("无此加密算法");
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return null;
		} catch (InvalidKeyException e) {
			throw new Exception("加密公钥非法,请检查");
		} catch (IllegalBlockSizeException e) {
			throw new Exception("明文长度非法");
		} catch (BadPaddingException e) {
			throw new Exception("明文数据已损坏");
		}
	}
}
