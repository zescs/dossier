package com.zescs.dossier.common.web.encrypt.util;

public class RsaKeyMap {
	private String publickKey;
	private String privateKey;

	public String getPublickKey() {
		return publickKey;
	}

	public void setPublickKey(String publickKey) {
		this.publickKey = publickKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public RsaKeyMap(String publickKey, String privateKey) {
		this.publickKey = publickKey;
		this.privateKey = privateKey;
	}

}
