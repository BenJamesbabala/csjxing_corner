package com.doucome.corner.biz.core.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 结算状态ENUM
 * @author  2012-4-17
 *
 */
public enum SettleStatusEnums {
	/**
	 * 未结算
	 */
	UNSETTLE("U"),
	/**
	 * 结算中
	 */
	SETTLE_PENDING("P"),
	/**
	 * 结算失败
	 */
	SETTLE_FAIL("F"), 
	
	/**
	 * 结算成功
	 */
	SETTLE_SUCCESS("S") ,
	
	UNKNOWN("") ,
	;
	
	private SettleStatusEnums(String value) {
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}

	public static SettleStatusEnums fromValue(String value){
		for(SettleStatusEnums e : SettleStatusEnums.values()){
			if(StringUtils.equals(e.getValue(), value)){
				return e ;
			}
		}
		return UNKNOWN ;
	}
	
	
	
}
