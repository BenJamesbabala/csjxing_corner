package com.doucome.corner.biz.dcome.enums;

/**
 * 活动类型
 * @author langben 2012-8-13
 *
 */
public enum DcPromTypeEnums {

	PK("PK") ,
	;
	
	private DcPromTypeEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	
}
