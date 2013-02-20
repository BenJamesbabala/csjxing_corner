package com.doucome.corner.biz.dcome.enums;

/**
 * µã»÷ÀàÐÍ
 * @author langben 2012-9-15
 *
 */
public enum DcUserClickStatisticsEnums {
	
	OFFER_CLICK("OF") ;
	

	private DcUserClickStatisticsEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
}
