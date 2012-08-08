package com.doucome.corner.biz.dcome.enums;

/**
 * 豆蔻商品来源
 * @author shenjia.caosj 2012-7-22
 *
 */
public enum DcItemSourceEnums {

	TAOBAO("TB","淘宝网") ,
	TMALL("TM","天猫") ,
	UNKNOWN("","未知")
	;
	
	private DcItemSourceEnums(String value , String desc){
		this.value = value ;
		this.desc = desc ;
	}
	
	private String value ;
	
	private String desc ;

	public String getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
	
	
}
