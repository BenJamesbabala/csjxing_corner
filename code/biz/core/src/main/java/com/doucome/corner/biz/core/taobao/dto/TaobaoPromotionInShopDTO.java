package com.doucome.corner.biz.core.taobao.dto;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * ���̼��Ż���Ϣ 
 * @author langben 2012-3-28
 *
 */
public class TaobaoPromotionInShopDTO extends AbstractModel {

	
	/**
	 * �Żݻ���� ,������ 	
	 */
	private String name ;
	
	/**
	 * idValueֵ ,mjs2121_21332111-9842 	
	 */
	private String promotionId ;
	
	/**
	 * 	�Ż�������������100Ԫ������ 
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
