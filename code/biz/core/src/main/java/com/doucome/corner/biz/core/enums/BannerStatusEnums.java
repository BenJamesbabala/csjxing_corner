package com.doucome.corner.biz.core.enums;

import java.util.HashMap;
import java.util.Map;


public enum BannerStatusEnums {

	NORMAL("N") , 
	DISABLED("D") ,
	UNKNOWN("");
	
	private BannerStatusEnums(String value) {
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	private static final Map<String, BannerStatusEnums> map = new HashMap<String, BannerStatusEnums>();
	static {
		BannerStatusEnums[] enums = BannerStatusEnums.values();
		for (BannerStatusEnums e : enums) {
			map.put(e.getValue(), e);
		}
	}
	
	/**
	 * 获取美剧实例
	 * @param status
	 * @return
	 */
	public static BannerStatusEnums getInstance(String status) {
		BannerStatusEnums e =  map.get(status);
		if(e != null){
			return e ;
		}
		return UNKNOWN;
	}
}
