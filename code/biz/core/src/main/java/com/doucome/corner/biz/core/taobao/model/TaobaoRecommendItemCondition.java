package com.doucome.corner.biz.core.taobao.model;

import com.doucome.corner.biz.core.taobao.enums.TaobaoRecommendTypeEnums;

public class TaobaoRecommendItemCondition {
	
	/**
	 * type
	 */
	private TaobaoRecommendTypeEnums recommendType = TaobaoRecommendTypeEnums.SAME_STYLE ;
	
	/**
	 * ���ؽ������
	 */
	private Long count = 20L;
	
	/**
	 * ����Ĳ�����Ϣ 
	 */
	private String ext ;

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
