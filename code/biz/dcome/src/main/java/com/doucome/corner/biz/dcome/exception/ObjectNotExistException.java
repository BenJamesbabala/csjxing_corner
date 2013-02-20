package com.doucome.corner.biz.dcome.exception;

/**
 * 
 * @author ze2200
 *
 */
public class ObjectNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8323760828651733490L;

	public ObjectNotExistException(){
		super();
	}
	
	public ObjectNotExistException(String message){
		super(message);
	}
}
