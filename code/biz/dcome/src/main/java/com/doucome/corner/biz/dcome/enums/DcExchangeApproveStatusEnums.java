package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author langben 2013-1-13
 *
 */
public enum DcExchangeApproveStatusEnums {

	/**
	 * 等待审核
	 */
	APPROVED_PENDING("P") ,
	
	/**
	 * 审核通过
	 */
	APPROVED("A") ,
	
	/**
	 * 审核未通过
	 */
	DISAPPROVED("D") ,
	
	/**
	 * 已经发放
	 */
	SENDED("S") ,
	
	/**
	 * 取消
	 */
	CANCEL("C") ,
	
	/**
	 * 失败
	 */
	FAILED("F") ,
	
	UNKNOWN("") ,
	;
	
	DcExchangeApproveStatusEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	private static Map<String,DcExchangeApproveStatusEnums> map = new HashMap<String,DcExchangeApproveStatusEnums>() ;
	
	static {
		for(DcExchangeApproveStatusEnums e : DcExchangeApproveStatusEnums.values()){
			map.put(e.getValue(), e) ;
		}
	}
	
	public static DcExchangeApproveStatusEnums get(String value) {
		
		DcExchangeApproveStatusEnums iot = map.get(value) ;
		if(iot == null){
			return UNKNOWN ;
		}
		
		return iot ;
	}

}
