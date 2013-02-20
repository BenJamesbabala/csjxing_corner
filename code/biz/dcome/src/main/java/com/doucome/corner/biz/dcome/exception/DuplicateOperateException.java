package com.doucome.corner.biz.dcome.exception;

/**
 * ÷ÿ∏¥ÃÌº”œ≤ª∂
 * @author langben 2012-7-22
 *
 */
public class DuplicateOperateException extends RuntimeException {

	private static final long serialVersionUID = 6736741912745278377L;

	public DuplicateOperateException(){
		super();
	}
	
	public DuplicateOperateException(String message){
		super(message);
	}
	
	public DuplicateOperateException(String message , Throwable e){
		super(message , e);
	}
}
