package com.zescs.dossier.common.service.exception;

public class PersistImportException extends RuntimeException {
	private static final long serialVersionUID = 7227395509007850734L;

	public PersistImportException() {
		super();
	}

	public PersistImportException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistImportException(String message) {
		super(message);
	}

	public PersistImportException(Throwable cause) {
		super(cause);
	}
}
