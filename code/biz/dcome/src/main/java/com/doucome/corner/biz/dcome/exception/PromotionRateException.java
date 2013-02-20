package com.doucome.corner.biz.dcome.exception;

public class PromotionRateException extends RuntimeException {
	
	/**
	 * 活动不存在
	 */
	public static final String ERROR_PROMOTION_REQUIRED = "dcome.promotion.required" ;
	
	/**
	 * 没有参加活动
	 */
	public static final String ERROR_PROMOTION_USER_REQUIRED = "dcome.promotion.user.required" ;
	
	/**
	 * 活动商品必须
	 */
	public static final String ERROR_PROMOTION_ITEM_REQUIRED = "dcome.promotion.item.required" ;
	
	/**
	 * 活动商品必须
	 */
	public static final String ERROR_STEAL_ITEM_REQUIRED = "dcome.stealRate.item.required" ;
	
	/**
	 * 积分不组
	 */
	public static final String ERROR_INTEGRAL_NOT_ENOUGH = "dcome.user.integral.notenough" ;
	
	/**
	 * 抢票的商品是自己的
	 */
	public static final String ERROR_STEAL_ITEM_SAME = "dcome.stealRate.item.same" ;
	
	/**
	 * 增加投票失败
	 */
	public static final String ERROR_STEAL_ADD_RATE_ERROR = "dcome.stealRate.addRate.error" ;
	
	/**
	 * 被抢的商品没有票数
	 */
	public static final String ERROR_STEAL_ITEM_LOWRATE = "dcome.promotion.item.lowrate" ;
	
	/**
	 * 没有可以领取的愿望值
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
