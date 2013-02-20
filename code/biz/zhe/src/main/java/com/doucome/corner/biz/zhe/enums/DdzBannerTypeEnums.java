package com.doucome.corner.biz.zhe.enums;

/**
 * 点点折banner类型
 * @author langben 2012-9-27
 *
 */
public enum DdzBannerTypeEnums {

	TOP("TOP") , 
	MIDDLE("MID") , ;
	
	private DdzBannerTypeEnums(String value){
		this.value = value ;
	}
	
	private String value ;
	
	public String getValue(){
		return this.value ;
	}
}
