package com.doucome.corner.biz.dcome.enums;

/**
 * �û���չ��Ϣ
 * @author langben 2013-1-16
 *
 */
public enum DcUserExtendDescEnums {

	/**
	 * �һ����·�
	 */
	EXCHANGE_MONTH("EX_MON") ,
	/**
	 * �һ�����
	 */
	EXCHANGE_COUNT("EX_COUNT") ,
	;

	
	private DcUserExtendDescEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	
}
