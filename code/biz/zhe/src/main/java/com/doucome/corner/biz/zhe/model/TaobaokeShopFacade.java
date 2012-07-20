package com.doucome.corner.biz.zhe.model;

import java.math.BigDecimal;

import com.doucome.corner.biz.core.model.AbstractModel;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeShopDTO;
import com.doucome.corner.biz.core.utils.ReflectUtils;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule.InternalCommission;

public class TaobaokeShopFacade extends AbstractModel{

	public TaobaokeShopFacade(TaobaokeShopDTO shop , DdzEatDiscountRule rule) {
		if(shop != null){
			ReflectUtils.reflectTo(shop, this) ;
			
			
			InternalCommission internalCommission = DdzEatDiscountRule.calcUserCommission(rule ,  commissionRate) ;
			
			this.userCommissionRate = internalCommission.getCommissionRate() ;
		}
	}
	
	/**
	 * shopId
	 */
	private String sid ;
	
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
	
	private BigDecimal userCommissionRate ;

	public BigDecimal getUserCommissionRate() {
		return userCommissionRate;
	}

	public void setUserCommissionRate(BigDecimal userCommissionRate) {
		this.userCommissionRate = userCommissionRate;
	}

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

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	
	
}
