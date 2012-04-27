package com.doucome.corner.biz.core.exception;

public class EncryptException extends Exception {

	private static final long serialVersionUID = -717284979525566455L;

	public EncryptException(String message , Throwable e){
		super(message , e) ;
	}
	
	public EncryptException(Throwable e){
		super(e) ;
	}
}
