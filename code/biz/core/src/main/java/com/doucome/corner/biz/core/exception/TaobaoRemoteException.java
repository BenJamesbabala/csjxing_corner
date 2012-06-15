package com.doucome.corner.biz.core.exception;

public class TaobaoRemoteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4661710911354694759L;

	private String errCode ;
	
	public TaobaoRemoteException(){
		super();
	}
	
	public TaobaoRemoteException(String message){
		super(message);
	}

	public TaobaoRemoteException(String message , String errCode){
		super(message);
		this.errCode = errCode ;
	}
	
	public TaobaoRemoteException(String message, Throwable cause){
		super(message, cause);
	}
	
	public TaobaoRemoteException(String message , Throwable cause , String errCode){
		super(message, cause);
		this.errCode = errCode ;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	
	
}

