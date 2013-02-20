package com.doucome.corner.biz.core.taobao.dto;

import com.doucome.corner.biz.common.utils.ReflectUtils;
import com.taobao.api.domain.TaobaokeItemDetail;

/**
 * 淘宝客商品详情 
 * @author langben 2012-4-9
 *
 */
public class TaobaokeItemDetailDTO {
	
	public TaobaokeItemDetailDTO(TaobaokeItemDetail detail){
		
		this.clickUrl = detail.getClickUrl() ;
		this.sellerCreditScore = detail.getSellerCreditScore() ;
		this.shopClickUrl = detail.getShopClickUrl() ;
		
		ReflectUtils.reflectTo(detail.getItem(), item) ;
	}

	private TaobaoItemDTO item ;
	
	private String clickUrl  ;
	
	private String shopClickUrl  ;
	
	private Long sellerCreditScore  ;

	public TaobaoItemDTO getItem() {
		return item;
	}

	public void setItem(TaobaoItemDTO item) {
		this.item = item;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public String getShopClickUrl() {
		return shopClickUrl;
	}

	public void setShopClickUrl(String shopClickUrl) {
		this.shopClickUrl = shopClickUrl;
	}

	public Long getSellerCreditScore() {
		return sellerCreditScore;
	}

	public void setSellerCreditScore(Long sellerCreditScore) {
		this.sellerCreditScore = sellerCreditScore;
	}
	
	
	
}
