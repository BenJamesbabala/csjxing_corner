package com.doucome.corner.biz.core.enums;

/**
 * �Ƽ�����
 * @author langben 2012-5-21
 *
 */
public enum RecommendTypeEnums {

	/**
	 * ��Ʒ�Ƽ�
	 */
	ITEM("A") ,
	
	/**
	 * ������
	 */
	BUYING("B") ,
	;
	private RecommendTypeEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	
}
