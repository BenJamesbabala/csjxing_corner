package com.doucome.corner.biz.core.enums;

/**
 * 推荐类型
 * @author langben 2012-5-21
 *
 */
public enum RecommendTypeEnums {

	/**
	 * 商品推荐
	 */
	ITEM("A") ,
	
	/**
	 * 正在买
	 */
	BUYING("B") ,
	;
	private RecommendTypeEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	
}
