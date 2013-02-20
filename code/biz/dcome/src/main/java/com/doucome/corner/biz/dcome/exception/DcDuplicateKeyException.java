package com.doucome.corner.biz.dcome.exception;

/**
 * 豆蔻违反唯一性约束异常类.
 * @author
 *
 */
@SuppressWarnings("serial")
public class DcDuplicateKeyException extends Exception {
	
	public DcDuplicateKeyException() {
		super();
	}
	
	public DcDuplicateKeyException(String message) {
		super(message);
	}
	
	public DcDuplicateKeyException(Exception e) {
		super(e);
	}
	
	public DcDuplicateKeyException(String message, Exception e) {
		super(message, e);
	}
}
