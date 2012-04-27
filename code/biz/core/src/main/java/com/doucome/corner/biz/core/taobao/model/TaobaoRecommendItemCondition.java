package com.doucome.corner.biz.core.taobao.model;

import com.doucome.corner.biz.core.taobao.enums.TaobaoRecommendTypeEnums;

public class TaobaoRecommendItemCondition {

	/**
	 * itemId
	 */
	private Long itemId ;
	
	/**
	 * type
	 */
	private TaobaoRecommendTypeEnums recommendType = TaobaoRecommendTypeEnums.SAME_STYLE ;
	
	/**
	 * 返回结果数量
	 */
	private Long count ;
	
	/**
	 * 额外的参数信息 
	 */
	private String ext ;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public TaobaoRecommendTypeEnums getRecommendType() {
		return recommendType;
	}

	public void setRecommendType(TaobaoRecommendTypeEnums recommendType) {
		this.recommendType = recommendType;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	
	
}
