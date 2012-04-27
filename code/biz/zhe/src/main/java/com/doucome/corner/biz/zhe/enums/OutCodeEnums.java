package com.doucome.corner.biz.zhe.enums;

public enum OutCodeEnums {

	/**
	 * 表示outCode传入的是DDZ_ACCOUNT_ID
	 */
	DDZ_ACCOUNT_ID("A") ,
	
	UNKNOWN("UNKNOWN") ,
	;
	
	private OutCodeEnums(String name) {
		this.name = name ;
	}
	
	private String name ;

	public String getName() {
		return name;
	}
	
	public static OutCodeEnums toOutCodeEnums(String name){
		for(OutCodeEnums e : OutCodeEnums.values()){
			if(e.getName().equals(name)){
				return e ;
			}
		}
		return UNKNOWN ;
	}
	
}
