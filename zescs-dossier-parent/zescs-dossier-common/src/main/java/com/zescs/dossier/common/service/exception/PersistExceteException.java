package com.zescs.dossier.common.service.exception;
/**
 * 
 * @ClassName: PersistException
 * @Description: TODO()
 * @author Lambert
 * @date 2015年4月14日 下午1:08:29
 *
 */
public class PersistExceteException extends RuntimeException {
	private static final long serialVersionUID = 7227395509007850734L;

	public PersistExceteException() {
		super();

	}

	public PersistExceteException(String message, Throwable cause) {
		super(message, cause);

	}

	public PersistExceteException(String message) {
		super(message);

	}

	public PersistExceteException(Throwable cause) {
		super(cause);

	}
}
