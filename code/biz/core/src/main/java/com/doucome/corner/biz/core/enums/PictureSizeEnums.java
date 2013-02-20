package com.doucome.corner.biz.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Õº∆¨¥Û–°
 * @author langben 2012-7-24
 *
 */
public enum PictureSizeEnums {
	
	_30x30("30x30" , 30) ,
	_40x40("40x40" , 40) ,
	_50x50("50x50" , 50) ,
	_60x60("60x60" , 60) ,
	_80x80("80x80" , 80) ,
	_100x100("100x100" , 100) ,
	_120x120("120x120" , 120) ,
	_160x160("160x160" , 160) ,
	_160x000("160x000" , 160) ,
	_180x180("180x180" , 180) ,
	_220x220("220x220" , 220),
	_220x000("220x000" , 220),
	_278x278("278x278" , 278),
	_280x280("280x280" , 280),
	_300x300("300x300" , 300),
	_154x154("154x154" , 154),
	NONE("" , 0) ,
	;

	private PictureSizeEnums(String name , int width) {
		this.name = name;
		this.width = width ;
	}

	private String name;
	
	private int width ;

	public String getName() {
		return name;
	}
	
	public int getWidth() {
		return width;
	}

	private static final Map<String, PictureSizeEnums> map = new HashMap<String, PictureSizeEnums>();
	static {
		PictureSizeEnums[] enums = PictureSizeEnums.values();
        for (PictureSizeEnums e : enums) {
            map.put(e.getName(), e);
        }
    }
	public static PictureSizeEnums toEnum(String name){
		PictureSizeEnums e = map.get(name);
		if(e != null){
			return e ;
		}
		return _50x50 ;
	}
}
