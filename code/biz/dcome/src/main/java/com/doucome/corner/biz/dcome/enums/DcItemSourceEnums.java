package com.doucome.corner.biz.dcome.enums;

/**
 * ��ޢ��Ʒ��Դ
 * @author shenjia.caosj 2012-7-22
 *
 */
public enum DcItemSourceEnums {

	TAOBAO("TB","�Ա���") ,
	TMALL("TM","��è") ,
	UNKNOWN("","δ֪")
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
