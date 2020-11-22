/**
 * @author vivek
 *
 * 
 */
package com.gini.file.storage.exception;

/**
 * @author vivek
 *
 */
public class GiniCommonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GiniCommonException(String message) {
		super(message);
	}

	public GiniCommonException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public GiniCommonException(Throwable cause) {
		super(cause);
	}

}
