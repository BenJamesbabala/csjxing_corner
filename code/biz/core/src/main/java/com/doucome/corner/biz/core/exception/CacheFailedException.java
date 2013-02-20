package com.doucome.corner.biz.core.exception;

/**
 * ª∫¥Ê ß∞‹“Ï≥£.
 * @author ze2200
 *
 */
public class CacheFailedException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CacheFailedException(Exception e) {
		super(e);
	}
	
	public CacheFailedException(String message, Exception e) {
		super(message, e);
	}
}
