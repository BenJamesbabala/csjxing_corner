package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 积分兑换类型
 * @author langben 2013-1-12
 *
 */
public enum DcExchangeTypeEnums {

	/**
	 * pgc兑换
	 */
	PGC_EXCHANGE("P") ,
	
	/**
	 * 默认兑换
	 */
	DEFAULT_EXCHANGE("D") ,
	
	UNKNOWN("") ,
	
	;
	
	private DcExchangeTypeEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	private static Map<String,DcExchangeTypeEnums> map = new HashMap<String,DcExchangeTypeEnums>() ;
	
	static {
		for(DcExchangeTypeEnums e : DcExchangeTypeEnums.values()){
			map.put(e.getValue(), e) ;
		}
	}
	
	public static DcExchangeTypeEnums get(String value) {
		
		DcExchangeTypeEnums iot = map.get(value) ;
		if(iot == null){
			return UNKNOWN ;
		}
		
		return iot ;
	}

	
}
