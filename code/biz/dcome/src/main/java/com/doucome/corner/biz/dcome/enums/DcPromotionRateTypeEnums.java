package com.doucome.corner.biz.dcome.enums;

/**
 * 活动投票类型
 * @author langben 2012-8-27
 *
 */
public enum DcPromotionRateTypeEnums {
	
	
	RATE("R") ,
	/**
	 * 抢票
	 */
	STEAL("S") , 
	/**
	 * 支取自己的积分
	 */
	DRAW("D")
	;
	
	private DcPromotionRateTypeEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
}
