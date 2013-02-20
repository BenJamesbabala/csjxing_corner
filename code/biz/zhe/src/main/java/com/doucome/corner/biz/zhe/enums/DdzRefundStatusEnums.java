package com.doucome.corner.biz.zhe.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * άȨ״̬
 * @author langben 2012-11-14
 *
 */
public enum DdzRefundStatusEnums {

	NONE("N") ,
	/**
	 * άȨ
	 */
	REFUND_TRUE("T") ,
	/**
	 * άȨ�Ѿ�����
	 */
	REFUND_PAYBACK("B") ,
	;
	
	private DdzRefundStatusEnums(String value) {
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	private static final Map<String, DdzRefundStatusEnums> map = new HashMap<String, DdzRefundStatusEnums>();
	
	static {
		DdzRefundStatusEnums[] enums = DdzRefundStatusEnums.values();
		for (DdzRefundStatusEnums e : enums) {
			map.put(e.getValue(), e);
		}
	}
	
	/**
	 * ��ȡʵ��
	 * @param status
	 * @return
	 */
	public static DdzRefundStatusEnums getInstance(String status) {
		DdzRefundStatusEnums e =  map.get(status);
		if(e != null){
			return e ;
		}
		return NONE ;
	}
}
