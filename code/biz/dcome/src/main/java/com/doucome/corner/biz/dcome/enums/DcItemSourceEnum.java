package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.web.common.utils.TaobaoUrlUtils;

/**
 * 豆蔻商品来源
 * @author langben 2012-7-22
 *
 */
public enum DcItemSourceEnum {

	TAOBAO("TB") {
		@Override
		public String getItemId(String itemUrl) {
			return TaobaoUrlUtils.parseItemId(itemUrl);
		}
	} ,
	TMALL("TM") {
		@Override
		public String getItemId(String itemUrl) {
			return TaobaoUrlUtils.parseItemId(itemUrl);
		}
	} ,
	UNKNOWN("") {
		@Override
		public String getItemId(String itemUrl) {
			return null;
		}
		
		@Override
		public boolean isLegalItemId(String itemId) {
			return false;
		}
	};
	
	private DcItemSourceEnum(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	/**
	 * 获取商品id.
	 * @param itemUrl
	 * @return
	 */
	public abstract String getItemId(String itemUrl);
	
	/**
	 * 判断是否是合法商品id.
	 * @param itemId
	 * @return
	 */
	public boolean isLegalItemId(String itemId) {
		if (StringUtils.isEmpty(itemId)) {
			return false;
		}
		return Pattern.matches("\\d+", itemId);
	}
	
	/**
	 * 获取商品来源.
	 * @param itemUrl
	 * @return
	 */
	public static DcItemSourceEnum getItemSource(String itemUrl) {
		if (StringUtils.isEmpty(itemUrl)) {
			return UNKNOWN;
		}
		if (itemUrl.indexOf("taobao") != -1) {
			return TAOBAO;
		} else if (itemUrl.indexOf("tmall") != -1) {
			return TMALL;
		} else {
			return UNKNOWN;
		}
	}
	
	private static Map<String, DcItemSourceEnum> map = new HashMap<String, DcItemSourceEnum>();

	static {
		DcItemSourceEnum[] enums = values();
		for (DcItemSourceEnum e : enums) {
			map.put(e.getValue(), e);
		}
	}
	
	public static DcItemSourceEnum get(String key) {
		if (key == null) {
			return UNKNOWN;
		}

		DcItemSourceEnum result = map.get(key);
		if (result != null) {
			return result;
		}
		return UNKNOWN;
	}
}
