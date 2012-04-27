package com.doucome.corner.biz.core.enums;

public enum GenderEnums {

	FEMALE("F") , 
	MALE("M") ,
 UNKNOW("")
	;
	
	private GenderEnums(String value){
		this.value = value ;
	}
	
	private String value ;
	
	public String getValue(){
		return this.value ;
	}
}
