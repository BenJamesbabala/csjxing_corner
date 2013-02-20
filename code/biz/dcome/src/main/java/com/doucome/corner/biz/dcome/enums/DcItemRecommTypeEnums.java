package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * �Ƽ�����
 * 
 * @author langben 2012-10-15
 * 
 */
public enum DcItemRecommTypeEnums {
	
	/**
	 * ��ʱ����
	 */
	LIMIT_BUY("LB"),
	/**
	 * �ޱ���
	 */
	HUODONG("HD"),
	NONE(""), ;

	private DcItemRecommTypeEnums(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}
	
	private static final Map<String,DcItemRecommTypeEnums> map = new HashMap<String,DcItemRecommTypeEnums>() ;
	static {
		for(DcItemRecommTypeEnums e : DcItemRecommTypeEnums.values()) {
			map.put(e.getValue(), e) ;
		}
	}
	
	public static DcItemRecommTypeEnums toEnum(String value){
		DcItemRecommTypeEnums e = map.get(value) ;
		if(e == null){
			return NONE ;
		}
		return e ;
	}
}
