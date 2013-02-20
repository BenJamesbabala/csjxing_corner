package com.doucome.corner.biz.apps.exception;

public class DuplicateKeyException extends RuntimeException {
	
	public DuplicateKeyException() {
		super();
	}
	
	public DuplicateKeyException(String message) {
		super(message);
	}
	
	public DuplicateKeyException(Exception e) {
		super(e);
	}
	
	public DuplicateKeyException(String message, Exception e) {
		super(message, e);
	}
}
