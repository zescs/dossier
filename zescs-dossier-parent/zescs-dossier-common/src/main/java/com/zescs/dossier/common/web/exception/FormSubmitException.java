package com.zescs.dossier.common.web.exception;

public class FormSubmitException extends RuntimeException {
	private static final long serialVersionUID = 312616303296760430L;

	public FormSubmitException() {
		super();
	
	}

	public FormSubmitException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public FormSubmitException(String message) {
		super(message);
	
	}

	public FormSubmitException(Throwable cause) {
		super(cause);
	
	}
	

}
