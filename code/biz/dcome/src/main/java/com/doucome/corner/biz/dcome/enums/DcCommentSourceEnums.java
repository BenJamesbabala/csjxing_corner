package com.doucome.corner.biz.dcome.enums;

/**
 * ������Դ
 * @author shenjia.caosj 2012-7-25
 *
 */
public enum DcCommentSourceEnums {

	/**
	 * qq�ռ�
	 */
	QZONE("QZ") , 
	;
	
	private DcCommentSourceEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	
}
