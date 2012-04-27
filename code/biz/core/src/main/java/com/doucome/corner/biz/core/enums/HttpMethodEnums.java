package com.doucome.corner.biz.core.enums;

public enum HttpMethodEnums {

	GET("GET") , 
	POST("POST") , 
	PUT("PUT") , 
	DELETE("DELETE") , 
	;
	
	private HttpMethodEnums(String value){
		this.value = value ;
	}
	
	private String value ;
	
	public String getValue(){
		return this.value ;
	}
}
