package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * ÆÀÂÛ×´Ì¬
 * @author langben 2012-7-25
 *
 */
public enum DcCommentStatusEnums {

	/**
	 * Õý³£
	 */
	NORMAL("N") ,
	
	/**
	 * ½ûÖ¹
	 */
	DISABLED("D") ,
	
	UNKNOWN("") ,
	;
	
	private DcCommentStatusEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	private static final Map<String,DcCommentStatusEnums> map = new HashMap<String,DcCommentStatusEnums>() ;
	static {
		for(DcCommentStatusEnums e : DcCommentStatusEnums.values()) {
			map.put(e.getValue(), e) ;
		}
	}
	
	public static DcCommentStatusEnums toEnum(String value){
		DcCommentStatusEnums e = map.get(value) ;
		if(e == null){
			return UNKNOWN ;
		}
		return e ;
	}
}
