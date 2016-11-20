package com.zescs.dossier.common.web.encrypt.rsa;

import java.security.Provider;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.zescs.dossier.common.web.commons.ConfigurationUtils;
import com.zescs.dossier.common.web.encrypt.util.Coder;
import com.zescs.dossier.config.R;

/**
 * 
 * @ClassName: RsaUserUtils
 * @Description: TODO(网站所有用户的rsa加密)
 * @author Lambert
 * @date 2015年2月6日 下午6:31:07
 * 
 */
public class RsaDataUtils {
	/** 算法名称 */
	private static final String ALGORITHOM = "RSA";
	/** 默认的安全服务提供者 */
	private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();
	/**
	 * 签名
	 */
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
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
		try {
			Configuration config = ConfigurationUtils.getInstance().getConfig(R.Config_Path.RSA_DATA_KEY);
			publicKey = RsaKeyUtils.loadPublicKey(config.getString(R.SystemConfig.PUBLICK_KEY));
			privateKey = RsaKeyUtils.loadPrivateKey(config.getString(R.SystemConfig.PRIVATE_KEY));
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	private RsaDataUtils() {
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
	 * @Description: TODO(该方法加密的用户数据直接存入数据库)
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
	 * @Description: TODO()
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
	
	public static String sign(byte[] data){
		try {
			// 用私钥对信息生成数字签名
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(getPrivateKey());
			signature.update(data);
			return Coder.encryptBASE64(signature.sign());
		} catch (Exception e) {
			return null;
		}
	}
	/**
     * 校验数字签名
     * @param data  加密数据
     * @param publicKey 公钥
     * @param sign  数字签名
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data,String sign)throws Exception{
        Signature signature;
		try {
			signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(getPublicKey());
			signature.update(data);
			//验证签名是否正常
			return signature.verify(Coder.decryptBASE64(sign));
		} catch (Exception e) {
			return false;
		}
    }
}
