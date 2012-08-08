package com.doucome.corner.biz.core.exception;

public class UpyunException extends RuntimeException {

	private static final long serialVersionUID = 8998271179909596718L;

	public UpyunException(){
		super();
	}
	
	public UpyunException(String message){
		super(message);
	}
	
	public UpyunException(String message, Throwable cause){
		super(message, cause);
	}
}
