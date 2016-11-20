package com.zescs.dossier.common.web.exception;

public class ArchivesPageException extends RuntimeException {
	private static final long serialVersionUID = 6649998342950866287L;

	public ArchivesPageException() {
		super();
	}

	public ArchivesPageException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ArchivesPageException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ArchivesPageException(String arg0) {
		super(arg0);
	}

	public ArchivesPageException(Throwable arg0) {
		super(arg0);
	}
}
