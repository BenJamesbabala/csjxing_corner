package com.doucome.corner.biz.core.taobao.enums;

import java.util.HashMap;
import java.util.Map;

public enum TaobaoItemApproveStatusEnums {

	onsale ,
	instock ,
	unknown;
	
	private static final Map<String, TaobaoItemApproveStatusEnums> map = new HashMap<String, TaobaoItemApproveStatusEnums>();
	static {
		TaobaoItemApproveStatusEnums[] enums = TaobaoItemApproveStatusEnums.values();
		for (TaobaoItemApproveStatusEnums e : enums) {
			map.put(e.toString(), e);
		}
	}
	
	public static TaobaoItemApproveStatusEnums getInstance(String approveStatus) {
		TaobaoItemApproveStatusEnums e = map.get(approveStatus) ;
		if(e == null){
			return unknown ;
		}
		return e ;
	}
}
