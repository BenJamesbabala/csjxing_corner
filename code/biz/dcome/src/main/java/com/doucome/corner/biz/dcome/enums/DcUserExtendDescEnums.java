package com.doucome.corner.biz.dcome.enums;

/**
 * 用户扩展信息
 * @author langben 2013-1-16
 *
 */
public enum DcUserExtendDescEnums {

	/**
	 * 兑换的月份
	 */
	EXCHANGE_MONTH("EX_MON") ,
	/**
	 * 兑换次数
	 */
	EXCHANGE_COUNT("EX_COUNT") ,
	;

	
	private DcUserExtendDescEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	
}
