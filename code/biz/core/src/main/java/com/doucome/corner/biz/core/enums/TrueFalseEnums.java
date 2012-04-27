package com.doucome.corner.biz.core.enums;

public enum TrueFalseEnums {

	TRUE("T") , 
	FALSE("F") ,
	;
	
	private TrueFalseEnums(String value){
		this.value = value ;
	}
	
	private String value ;
	
	public String getValue(){
		return this.value ;
	}
}
