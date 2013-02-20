package com.doucome.corner.biz.dcome.exception;

public class PromotionRateException extends RuntimeException {
	
	/**
	 * �������
	 */
	public static final String ERROR_PROMOTION_REQUIRED = "dcome.promotion.required" ;
	
	/**
	 * û�вμӻ
	 */
	public static final String ERROR_PROMOTION_USER_REQUIRED = "dcome.promotion.user.required" ;
	
	/**
	 * ���Ʒ����
	 */
	public static final String ERROR_PROMOTION_ITEM_REQUIRED = "dcome.promotion.item.required" ;
	
	/**
	 * ���Ʒ����
	 */
	public static final String ERROR_STEAL_ITEM_REQUIRED = "dcome.stealRate.item.required" ;
	
	/**
	 * ���ֲ���
	 */
	public static final String ERROR_INTEGRAL_NOT_ENOUGH = "dcome.user.integral.notenough" ;
	
	/**
	 * ��Ʊ����Ʒ���Լ���
	 */
	public static final String ERROR_STEAL_ITEM_SAME = "dcome.stealRate.item.same" ;
	
	/**
	 * ����ͶƱʧ��
	 */
	public static final String ERROR_STEAL_ADD_RATE_ERROR = "dcome.stealRate.addRate.error" ;
	
	/**
	 * ��������Ʒû��Ʊ��
	 */
	public static final String ERROR_STEAL_ITEM_LOWRATE = "dcome.promotion.item.lowrate" ;
	
	/**
	 * û�п�����ȡ��Ը��ֵ
	 */
	public static final String ERROR_DRAW_RATE_LOW = "dcome.draw.rate.low" ;
	

	private String code ;
	
	public PromotionRateException(){
		super();
	}
	
	public PromotionRateException(String code){
		this.code = code ;
	}
	
	public PromotionRateException(String code , String message){
		super(message) ;
		this.code = code ;
	}

	public String getCode() {
		return code;
	}
	
}
