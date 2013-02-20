package com.doucome.corner.biz.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author langben 2012-10-22
 *
 */
public enum SumTaokeReportTypeEnums {

	SETTLE_FEE , 
	COMMISSION  ,
	UNKNOWN
	;
	
	private static final Map<String, SumTaokeReportTypeEnums> map = new HashMap<String, SumTaokeReportTypeEnums>();
	static {
		SumTaokeReportTypeEnums[] enums = SumTaokeReportTypeEnums.values();
		for (SumTaokeReportTypeEnums e : enums) {
			map.put(e.toString(), e);
		}
	}

	public static SumTaokeReportTypeEnums get(String key) {
		SumTaokeReportTypeEnums e =  map.get(key);
		
		if(e == null){
			return UNKNOWN ;
		}
		
		return e ;
	}
}
