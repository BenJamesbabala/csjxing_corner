package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * ����ָ��
 * @author langben 2012-9-6
 *
 */
public enum DcUserNewGuideEnums {

	/**
	 * ��Ҫ��Ը
	 */
	PROM_IWISH(0,"prom_iwish") ,
	/**
	 * ȥ��Ը����
	 */
	PROM_STEAL_RATE(1,"prom_steal_rate") ,
	
	/**
	 * ÿСʱ��ȡԸ����
	 */
	PROM_DRAW_RATE(2,"prom_draw_rate") ,
	
	UNKNOWN(62 , "unknown") ,
	;
	
	private DcUserNewGuideEnums(int index , String name){
		this.index = index  ;
		this.name = name ;
	}
	
	/**
	 * λ����
	 */
	private int index ;
	
	/**
	 * ������
	 */
	private String name ;

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}
	
	private static Map<String, DcUserNewGuideEnums> nameMap = new HashMap<String, DcUserNewGuideEnums>();
	private static Map<Integer, DcUserNewGuideEnums> indexMap = new HashMap<Integer, DcUserNewGuideEnums>();
	static {
		DcUserNewGuideEnums[] enums = values();
		for (DcUserNewGuideEnums e : enums) {
			nameMap.put(e.getName(), e);
			indexMap.put(e.getIndex(), e) ;
		}
	}
	
	public static DcUserNewGuideEnums getInstanceByIndex(int index){
		DcUserNewGuideEnums e = indexMap.get(index) ;
		if(e == null){
			return UNKNOWN ;
		}
		return e ;
	}
	
	public static DcUserNewGuideEnums getInstanceByName(String name){
		DcUserNewGuideEnums e = nameMap.get(name) ;
		if(e == null){
			return UNKNOWN ;
		}
		return e ;
	}
}
