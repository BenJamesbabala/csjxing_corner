package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * qq�ռ���ҳ ����״̬
 * @author langben 2012-7-25
 *
 */
public enum DcQIndexPubStatusEnums {

	/**
	 * ����
	 */
	RELEASE("R") ,
	
	/**
	 * Ԥ����
	 */
	PRE_RELEASE("P") ,
	
	UNKNOWN("") ,
	;
	
	private DcQIndexPubStatusEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	private static final Map<String,DcQIndexPubStatusEnums> map = new HashMap<String,DcQIndexPubStatusEnums>() ;
	static {
		for(DcQIndexPubStatusEnums e : DcQIndexPubStatusEnums.values()) {
			map.put(e.getValue(), e) ;
		}
	}
	
	public static DcQIndexPubStatusEnums toEnum(String value){
		DcQIndexPubStatusEnums e = map.get(value) ;
		if(e == null){
			return UNKNOWN ;
		}
		return e ;
	}
}
