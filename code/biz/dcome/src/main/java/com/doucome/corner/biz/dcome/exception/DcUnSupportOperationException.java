package com.doucome.corner.biz.dcome.exception;

/**
 * 
 * @author ze2200
 *
 */
public class DcUnSupportOperationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3920122823607677491L;

	public DcUnSupportOperationException(){
		super();
	}
	
	public DcUnSupportOperationException(String message){
		super(message);
	}

	public DcUnSupportOperationException(String message , Throwable e){
		super(message , e);
	}
}
