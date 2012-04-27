package com.doucome.corner.biz.core.enums;

public enum TaobaoPicEnums {

	SUM("sum"), 
	_100x100("100x100") , 
	_120x120("120x120") ,
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

}
