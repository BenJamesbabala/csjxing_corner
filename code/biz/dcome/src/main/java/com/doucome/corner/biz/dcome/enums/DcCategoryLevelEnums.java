package com.doucome.corner.biz.dcome.enums;

/**
 * ��ޢ��Ŀ����
 * @author langben 2012-7-24
 *
 */
public enum DcCategoryLevelEnums {

	/**
	 * һ����Ŀ
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
