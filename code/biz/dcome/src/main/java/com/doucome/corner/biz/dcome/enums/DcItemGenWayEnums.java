package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * ��Ʒ���ݲ�����ʽEnum
 * @author langben 2012-8-8
 *
 */
public enum DcItemGenWayEnums {

	/**
	 * PGC
	 */
	PROFESSIONAL("P") , 
	/**
	 * UGC
	 */
	USER("U"),
	/**
	 * ugc״̬��һ�֣�������������û�.
	 */
	SECRET("S"),
	UNKNOWN("") ,
	;
	
	private DcItemGenWayEnums(String value) {
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}

	private static final Map<String,DcItemGenWayEnums> map = new HashMap<String,DcItemGenWayEnums>() ;
	static {
		for(DcItemGenWayEnums e : DcItemGenWayEnums.values()) {
			map.put(e.getValue(), e) ;
		}
	}
	
	public static DcItemGenWayEnums toEnum(String value){
		DcItemGenWayEnums e = map.get(value) ;
		if(e == null){
			return UNKNOWN ;
		}
		return e ;
	}
}
