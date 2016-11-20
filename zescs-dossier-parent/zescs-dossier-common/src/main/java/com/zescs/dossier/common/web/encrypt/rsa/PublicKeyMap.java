package com.zescs.dossier.common.web.encrypt.rsa;

/**
 * 
 * @ClassName: PublicKeyMap
 * @Description: TODO(rsa参数)
 * @author 郑建平
 * @date 2015年1月7日 下午12:02:10
 * 
 */
public class PublicKeyMap {
	/**
	 * 公钥加密系数
	 */
	private String modulus;
	/**
	 * 公钥专用指数
	 */
	private String exponent;

	public String getModulus() {
		return modulus;
	}

	public void setModulus(String modulus) {
		this.modulus = modulus;
	}

	public String getExponent() {
		return exponent;
	}

	public void setExponent(String exponent) {
		this.exponent = exponent;
	}
}