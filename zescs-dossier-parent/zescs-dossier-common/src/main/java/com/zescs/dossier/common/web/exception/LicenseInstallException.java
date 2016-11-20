package com.zescs.dossier.common.web.exception;

public class LicenseInstallException extends Exception{
	private static final long serialVersionUID = 7696711958840621104L;

	public LicenseInstallException() {
		super();
	}

	public LicenseInstallException(String message, Throwable cause) {
		super(message, cause);
	}

	public LicenseInstallException(String message) {
		super(message);
	}

	public LicenseInstallException(Throwable cause) {
		super(cause);
	}
}
