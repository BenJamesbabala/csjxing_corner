package com.doucome.corner.biz.core.taobao.enums;


public enum TaokeItemSearchSortEnums {

	price_desc("price_desc") ,
	price_asc("price_asc") ,
	credit_desc("credit_desc") ,
	commissionRate_desc("commissionRate_desc") ,
	commissionRate_asc("commissionRate_asc") ,
	commissionNum_desc("commissionNum_desc") ,
	commissionNum_asc("commissionNum_asc") ,
	commissionVolume_desc("commissionVolume_desc") ,
	commissionVolume_asc("commissionVolume_asc") ,
	delistTime_desc("delistTime_desc") ,
	delistTime_asc("delistTime_asc") ;
	
	private TaokeItemSearchSortEnums(String value) {
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	
}
