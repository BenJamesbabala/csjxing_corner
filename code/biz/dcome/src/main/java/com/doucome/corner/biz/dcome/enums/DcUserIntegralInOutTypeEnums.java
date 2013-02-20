package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 收入、支出类型
 * @author langben 2013-1-8
 *
 */
public enum DcUserIntegralInOutTypeEnums {

	/**
	 * 收入
	 */
	INCOME("I") ,
	/**
	 * 支出
	 */
	OUTLAY("O") ,
	
	UNKNOWN("") ,
	
	;
	
	private DcUserIntegralInOutTypeEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	private static Map<String,DcUserIntegralInOutTypeEnums> map = new HashMap<String,DcUserIntegralInOutTypeEnums>() ;
	
	static {
		for(DcUserIntegralInOutTypeEnums e : DcUserIntegralInOutTypeEnums.values()){
			map.put(e.getValue(), e) ;
		}
	}
	
	public static DcUserIntegralInOutTypeEnums get(String value) {
		DcUserIntegralInOutTypeEnums iot = map.get(value) ;
		if(iot == null){
			return UNKNOWN ;
		}
		
		return iot ;
	}
}
