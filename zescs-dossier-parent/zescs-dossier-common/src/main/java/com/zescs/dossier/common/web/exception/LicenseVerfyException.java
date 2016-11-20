package com.zescs.dossier.common.web.exception;

public class LicenseVerfyException extends Exception{
	private static final long serialVersionUID = 7696711958840621104L;

	public LicenseVerfyException() {
		super();
	}

	public LicenseVerfyException(String message, Throwable cause) {
		super(message, cause);
	}

	public LicenseVerfyException(String message) {
		super(message);
	}

	public LicenseVerfyException(Throwable cause) {
		super(cause);
	}
}
