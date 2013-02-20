package com.doucome.corner.biz.core.enums;

public enum OutCodeEnums {

	/**
	 * 表示outCode传入的是DDZ_ACCOUNT_ID
	 */
	DDZ_ACCOUNT_ID("A") ,
	
	/**
	 * 点点折报表，手动提款
	 */
	DDZ_ACCOUNT_ID_MANUAL("B") ,
	
	/**
	 * 点点折集分宝结算
	 */
	DDZ_ACCOUNT_ID_JFB("J") ,
	
	/**
	 * 豆蔻 
	 */
	DOUCOME("D"),
	
	/**
	 * 豆蔻PK活动
	 */
	DOUCOME_PROMOTION("P") ,
	
	/**
	 * 豆蔻user_id
	 */
	DOUCOME_USER_ID("U") ,
	
	/**
	 * 系统自动或者没有归类的默认类型
	 */
	SYSTEM("S") ,
	
	UNKNOWN("UN") ,
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
