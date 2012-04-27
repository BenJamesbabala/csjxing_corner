package com.doucome.corner.biz.core.enums;

import org.apache.commons.lang.StringUtils;

/**
 * ����״̬ENUM
 * @author  2012-4-17
 *
 */
public enum SettleStatusEnums {
	/**
	 * δ����
	 */
	UNSETTLE("U"),
	/**
	 * ������
	 */
	SETTLE_PENDING("P"),
	/**
	 * ����ʧ��
	 */
	SETTLE_FAIL("F"), 
	
	/**
	 * ����ɹ�
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
