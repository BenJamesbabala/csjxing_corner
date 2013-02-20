package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

public enum DcUserIntegralDescKeyEnums {

	/**
	 * SOURCE
	 */
	DESC_SOURCE("DESC_SOURCE") ,
	/**
	 * �������ID
	 */
	SETTLE_ID("SETTLE_ID") ,
	/**
	 * �Ա���ƷID
	 */
	NUM_IID("NUM_IID") ,
	
	/**
	 * �ǳ�
	 */
	USER_NICK("USER_NICK") ,
	
	/**
	 * ��Ʒtitle
	 */
	ITEM_TITLE("ITEM_TITLE") ,
	
	/**
	 * ����ID
	 */
	TAOKE_REPORT_ID("TK_REPORT_ID") ,
	/**
	 * ���׺�
	 */
	TAOKE_TRADE_ID("TK_TRADE_ID") ,
	/**
	 * �һ���ƷID
	 */
	EX_ITEM_ID("EX_ITEM_ID") ,
	/**
	 * �һ���Ʒ����
	 */
	EX_ITEM_NUM("EX_ITEM_NUM") ,
	
	/**
	 * ��ޢ��ƷID
	 */
	DC_ITEM_ID("DC_ITEM_ID") ,
	
	/**
	 * �һ�����Ʒ����
	 */
	EX_ITEM_INTEGRAL("EX_ITEM_INTEGRAL") ,
	/**
	 * �������user_id
	 */
	INVITED_USER_ID("INVT_USER_ID") ,
	/**
	 * �������user_NICK
	 */
	INVITED_USER_NICK("INVT_USER_NICK") ,
	/**
	 * AWARD_ID
	 */
	AWARD_ID("AWARD_ID") ,
	/**
	 * �ռ�����
	 */
	QZONE_NAME("QZONE_NAME") ,
	/**
	 * ����ǩ������
	 */
	CONTINUE_DAYS("CONTINUE_DAYS") ,
	/**
	 * ��������
	 */
	SHARE_OBJECT_TYPE("SHARE_OBJ_TYPE") ,
	/**
	 * �������ID
	 */
	SHARE_OBJECT_ID("SHARE_OBJ_ID") ,
	
	OPEN_IDS("OPEN_IDS") ,
	
	UNKNOWN("") ,
	;
	
	private DcUserIntegralDescKeyEnums(String value) {
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	private static Map<String,DcUserIntegralDescKeyEnums> map = new HashMap<String,DcUserIntegralDescKeyEnums>() ;
	static {
		for(DcUserIntegralDescKeyEnums e : DcUserIntegralDescKeyEnums.values()) {
			map.put(e.getValue(), e) ;
		}
	}
	
	public static DcUserIntegralDescKeyEnums toEnum(String value) {
		DcUserIntegralDescKeyEnums e = map.get(value) ;
		if(e == null) {
			return UNKNOWN ;
		}
		return e ;
	}
}
	
