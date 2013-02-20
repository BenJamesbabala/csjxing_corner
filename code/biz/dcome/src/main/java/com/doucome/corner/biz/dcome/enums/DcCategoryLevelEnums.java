package com.doucome.corner.biz.dcome.enums;

/**
 * 豆蔻类目级别
 * @author langben 2012-7-24
 *
 */
public enum DcCategoryLevelEnums {

	/**
	 * 一级类目
	 */
	CATGORY("C") ,
	
	;
	
	private DcCategoryLevelEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	
}
