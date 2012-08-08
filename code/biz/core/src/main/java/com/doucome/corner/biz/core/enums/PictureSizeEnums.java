package com.doucome.corner.biz.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Õº∆¨¥Û–°
 * @author shenjia.caosj 2012-7-24
 *
 */
public enum PictureSizeEnums {
	
	_30x30("30x30") ,
	_40x40("40x40") ,
	_50x50("50x50") ,
	_60x60("60x60") ,
	_80x80("80x80") ,
	_100x100("100x100") ,
	_120x120("120x120") ,
	_160x160("160x160") ,
	_180x180("180x180") ,
	_278x278("278x278"),
	_280x280("280x280"),
	_154x154("154x154"),
	;

	private PictureSizeEnums(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
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
