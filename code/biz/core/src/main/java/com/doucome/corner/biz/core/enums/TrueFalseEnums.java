package com.doucome.corner.biz.core.enums;

import java.util.HashMap;
import java.util.Map;

public enum TrueFalseEnums {

	TRUE("T") , 
	FALSE("F") ,
	
	UNKNOWN("") ;
	;
	
	private TrueFalseEnums(String value){
		this.value = value ;
	}
	
	private String value ;
	
	public String getValue(){
		return this.value ;
	}
	
	private static final Map<String, TrueFalseEnums> map = new HashMap<String, TrueFalseEnums>();
	static {
		TrueFalseEnums[] enums = TrueFalseEnums.values();
        for (TrueFalseEnums e : enums) {
            map.put(e.getValue(), e);
        }
    }
	public static TrueFalseEnums toEnum(String value){
		TrueFalseEnums e = map.get(value);
		if(e != null){
			return e ;
		}
		return UNKNOWN ;
	}
}
