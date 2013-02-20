package com.doucome.corner.biz.zhe.exception;

@SuppressWarnings("serial")
public class DdzDuplicateKeyException extends RuntimeException {

	public DdzDuplicateKeyException() {
		super();
	}
	
	public DdzDuplicateKeyException(String message) {
		super(message);
	}
	
	public DdzDuplicateKeyException(Throwable e) {
		super(e);
	}
	
	public DdzDuplicateKeyException(String message, Throwable e) {
		super(message, e);
	}
	
}
