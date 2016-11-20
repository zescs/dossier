package com.zescs.dossier.common.service.exception;
/**
 * 
 * @ClassName: PersistException
 * @Description: TODO()
 * @author Lambert
 * @date 2015年4月14日 下午1:08:29
 *
 */
public class PersistAddException extends RuntimeException {
	private static final long serialVersionUID = 7227395509007850734L;

	public PersistAddException() {
		super();

	}

	public PersistAddException(String message, Throwable cause) {
		super(message, cause);

	}

	public PersistAddException(String message) {
		super(message);

	}

	public PersistAddException(Throwable cause) {
		super(cause);

	}
}
