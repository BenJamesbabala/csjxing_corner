package com.doucome.corner.biz.zhe.exception;

/**
 * 手动提现异常
 * @author langben 2012-11-13
 *
 */
public class ManualSettleNotSufficientException extends RuntimeException {

	public ManualSettleNotSufficientException() {
		super();
	}
	
	public ManualSettleNotSufficientException(String message) {
		super(message);
	}
	
	public ManualSettleNotSufficientException(Throwable e) {
		super(e);
	}
	
	public ManualSettleNotSufficientException(String message, Throwable e) {
		super(message, e);
	}
	
}
