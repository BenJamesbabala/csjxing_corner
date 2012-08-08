package com.doucome.corner.biz.core.enums;

public enum OutCodeEnums {

	/**
	 * ��ʾoutCode�������DDZ_ACCOUNT_ID
	 */
	DDZ_ACCOUNT_ID("A") ,
	
	/**
	 * ��ޢ QQ
	 */
	DOUCOME("D"),
	
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
