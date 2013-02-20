package com.doucome.corner.biz.core.taobao.dto;

import java.math.BigDecimal;

import com.doucome.corner.biz.common.utils.ReflectUtils;
import com.doucome.corner.biz.core.constant.DecimalConstant;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.taobao.api.domain.TaobaokeShop;

/**
 * 淘宝客推广店铺DO
 * @author langben 2012-2-24
 *
 */
public class TaobaokeShopDTO extends AbstractModel {
	
	public TaobaokeShopDTO(TaobaokeShop shop){
		ReflectUtils.reflectTo(shop, this) ;
		if(this.commissionRate != null){
			this.commissionRate.divide(DecimalConstant.HUNDRED) ;
		}
	}
	

	/**
	 * 店铺用户id,1212 	
	 */
	private Long userId ;
	
	/**
	 * 店铺名称
	 */
	private String shopTitle ;
	
	/**
	 * 	店铺推广URL , http://blog.open.taobao.com 
	 */
	private String clickUrl ;
	
	/**
	 * 淘宝客店铺佣金比率 , 12% 	
	 */
	private BigDecimal commissionRate ;
	
	/**
	 * 店铺掌柜信用等级,14 	
	 */
	private String sellerCredit ;
	
	/**
	 * 店铺类型 , B=商城卖家 C=普通卖家
	 */
	private String shopType ;
	
	/**
	 * 200 	累计推广量
	 */
	private String totalAuction ;
	
	/**
	 * 店铺内商品总数吗,100 	
	 */
	private Long auctionCount ;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getShopTitle() {
		return shopTitle;
	}

	public void setShopTitle(String shopTitle) {
		this.shopTitle = shopTitle;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public String getSellerCredit() {
		return sellerCredit;
	}

	public void setSellerCredit(String sellerCredit) {
		this.sellerCredit = sellerCredit;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getTotalAuction() {
		return totalAuction;
	}

	public void setTotalAuction(String totalAuction) {
		this.totalAuction = totalAuction;
	}

	public Long getAuctionCount() {
		return auctionCount;
	}

	public void setAuctionCount(Long auctionCount) {
		this.auctionCount = auctionCount;
	}
	
}
