package com.doucome.corner.biz.zhe.enums;

/**
 * ≤È—Ø∑Ω Ω
 * @author shenjia.caosj 2012-3-7
 *
 */
public enum SearchWayEnums {

	ITEM("item") , 
	SHOP("shop") ,
	KEYWORD("keyword")
	;
	
	private SearchWayEnums(String value){
		this.value = value ;
	}
	
	private String value ;
	
	public String getValue(){
		return this.value ;
	}
}
