package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

public enum DcUserIntegralDescKeyEnums {

	/**
	 * SOURCE
	 */
	DESC_SOURCE("DESC_SOURCE") ,
	/**
	 * 报表结算ID
	 */
	SETTLE_ID("SETTLE_ID") ,
	/**
	 * 淘宝商品ID
	 */
	NUM_IID("NUM_IID") ,
	
	/**
	 * 昵称
	 */
	USER_NICK("USER_NICK") ,
	
	/**
	 * 商品title
	 */
	ITEM_TITLE("ITEM_TITLE") ,
	
	/**
	 * 报表ID
	 */
	TAOKE_REPORT_ID("TK_REPORT_ID") ,
	/**
	 * 交易号
	 */
	TAOKE_TRADE_ID("TK_TRADE_ID") ,
	/**
	 * 兑换商品ID
	 */
	EX_ITEM_ID("EX_ITEM_ID") ,
	/**
	 * 兑换商品数量
	 */
	EX_ITEM_NUM("EX_ITEM_NUM") ,
	
	/**
	 * 豆蔻商品ID
	 */
	DC_ITEM_ID("DC_ITEM_ID") ,
	
	/**
	 * 兑换单商品积分
	 */
	EX_ITEM_INTEGRAL("EX_ITEM_INTEGRAL") ,
	/**
	 * 被邀请的user_id
	 */
	INVITED_USER_ID("INVT_USER_ID") ,
	/**
	 * 被邀请的user_NICK
	 */
	INVITED_USER_NICK("INVT_USER_NICK") ,
	/**
	 * AWARD_ID
	 */
	AWARD_ID("AWARD_ID") ,
	/**
	 * 空间名字
	 */
	QZONE_NAME("QZONE_NAME") ,
	/**
	 * 连续签到天数
	 */
	CONTINUE_DAYS("CONTINUE_DAYS") ,
	/**
	 * 分享类型
	 */
	SHARE_OBJECT_TYPE("SHARE_OBJ_TYPE") ,
	/**
	 * 分享对象ID
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
	
