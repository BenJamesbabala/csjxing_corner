package com.doucome.corner.biz.dcome.exception;

/**
 * ��ޢΥ��Ψһ��Լ���쳣��.
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
