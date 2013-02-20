package com.doucome.corner.biz.core.taobao.dto;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 店铺级优惠信息 
 * @author langben 2012-3-28
 *
 */
public class TaobaoPromotionInShopDTO extends AbstractModel {

	
	/**
	 * 优惠活动名称 ,满就送 	
	 */
	private String name ;
	
	/**
	 * idValue值 ,mjs2121_21332111-9842 	
	 */
	private String promotionId ;
	
	/**
	 * 	优惠详情描述。满100元送手套 
	 */
	private String promotionDetailDesc ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionDetailDesc() {
		return promotionDetailDesc;
	}

	public void setPromotionDetailDesc(String promotionDetailDesc) {
		this.promotionDetailDesc = promotionDetailDesc;
	}
	
	
}
