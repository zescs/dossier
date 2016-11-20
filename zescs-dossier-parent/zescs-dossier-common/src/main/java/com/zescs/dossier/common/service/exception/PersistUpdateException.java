package com.zescs.dossier.common.service.exception;

/**
 * 
 * @ClassName: PersistException
 * @Description: TODO()
 * @author Lambert
 * @date 2015年4月14日 下午1:08:29
 *
 */
public class PersistUpdateException extends RuntimeException {
	private static final long serialVersionUID = 7227395509007850734L;

	public PersistUpdateException() {
		super();
	}

	public PersistUpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistUpdateException(String message) {
		super(message);
	}

	public PersistUpdateException(Throwable cause) {
		super(cause);
	}
}
