package com.doucome.corner.biz.dcome.enums;

/**
 * �ͶƱ����
 * @author langben 2012-8-27
 *
 */
public enum DcPromotionRateTypeEnums {
	
	
	RATE("R") ,
	/**
	 * ��Ʊ
	 */
	STEAL("S") , 
	/**
	 * ֧ȡ�Լ��Ļ���
	 */
	DRAW("D")
	;
	
	private DcPromotionRateTypeEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
}
