package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 新手指引
 * @author langben 2012-9-6
 *
 */
public enum DcPromotionItemNewGuideEnums {

	/**
	 * 每小时领取
	 */
	PROM_HOURLY_DRAW(0,"prom_hourly_draw") ,
	/**
	 *砸金蛋
	 */
	GOLDEN_EGG(1,"golden_egg") ,
	
	UNKNOWN(62 , "unknown") ,
	;
	
	private DcPromotionItemNewGuideEnums(int index , String name){
		this.index = index  ;
		this.name = name ;
	}
	
	/**
	 * 位索引
	 */
	private int index ;
	
	/**
	 * 向导名称
	 */
	private String name ;

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}
	
	private static Map<String, DcPromotionItemNewGuideEnums> nameMap = new HashMap<String, DcPromotionItemNewGuideEnums>();
	private static Map<Integer, DcPromotionItemNewGuideEnums> indexMap = new HashMap<Integer, DcPromotionItemNewGuideEnums>();
	static {
		DcPromotionItemNewGuideEnums[] enums = values();
		for (DcPromotionItemNewGuideEnums e : enums) {
			nameMap.put(e.getName(), e);
			indexMap.put(e.getIndex(), e) ;
		}
	}
	
	public static DcPromotionItemNewGuideEnums getInstanceByIndex(int index){
		DcPromotionItemNewGuideEnums e = indexMap.get(index) ;
		if(e == null){
			return UNKNOWN ;
		}
		return e ;
	}
	
	public static DcPromotionItemNewGuideEnums getInstanceByName(String name){
		DcPromotionItemNewGuideEnums e = nameMap.get(name) ;
		if(e == null){
			return UNKNOWN ;
		}
		return e ;
	}
}
