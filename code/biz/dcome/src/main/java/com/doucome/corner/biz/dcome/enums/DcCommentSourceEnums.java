package com.doucome.corner.biz.dcome.enums;

/**
 * 评论来源
 * @author shenjia.caosj 2012-7-25
 *
 */
public enum DcCommentSourceEnums {

	/**
	 * qq空间
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
