package com.doucome.corner.biz.core.enums;

import org.apache.commons.lang.StringUtils;

public enum TaobaoPicEnums {

	SUM("sum"), 
	_100x100("100x100") , 
	_120x120("120x120") ,
	_160x160("160x160") ,
	_310x310("310x310") ,
	_210x210("210x210") , 
	
	;

	private TaobaoPicEnums(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public static TaobaoPicEnums toTaobaoPicEnums(String name){
		for(TaobaoPicEnums e : TaobaoPicEnums.values()){
			if(StringUtils.equals(e.getName(),name)){
				return e ;
			}
		}
		return SUM ;
	}
}
