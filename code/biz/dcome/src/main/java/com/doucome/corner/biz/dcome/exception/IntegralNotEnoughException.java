package com.doucome.corner.biz.dcome.exception;

public class IntegralNotEnoughException extends RuntimeException{

	private static final long serialVersionUID = -4868513722152260338L;
	
	public IntegralNotEnoughException(){
		super();
	}
	
	public IntegralNotEnoughException(String message){
		super(message);
	}

	public IntegralNotEnoughException(String message , Throwable e){
		super(message , e);
	}
}
