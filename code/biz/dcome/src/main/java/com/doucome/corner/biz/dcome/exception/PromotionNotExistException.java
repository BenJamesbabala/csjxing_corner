package com.doucome.corner.biz.dcome.exception;

/**
 * 
 * @author ze2200
 *
 */
public class PromotionNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8323760828651733490L;

	public PromotionNotExistException(){
		super();
	}
	
	public PromotionNotExistException(String message){
		super(message);
	}
}
