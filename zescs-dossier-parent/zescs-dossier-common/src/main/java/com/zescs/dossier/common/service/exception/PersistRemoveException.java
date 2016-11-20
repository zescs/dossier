package com.zescs.dossier.common.service.exception;
/**
 * 
 * @ClassName: PersistException
 * @Description: TODO()
 * @author Lambert
 * @date 2015年4月14日 下午1:08:29
 *
 */
public class PersistRemoveException extends RuntimeException {
	private static final long serialVersionUID = 7227395509007850734L;

	public PersistRemoveException() {
		super();

	}

	public PersistRemoveException(String message, Throwable cause) {
		super(message, cause);

	}

	public PersistRemoveException(String message) {
		super(message);

	}

	public PersistRemoveException(Throwable cause) {
		super(cause);

	}
}
