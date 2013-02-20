package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

/**
 * 活动投票
 * @author langben 2012-8-13
 *
 */
public class DcPromotionRateDetailDO {

	private Long id ;
	
	/**
	 * 活动ID
	 */
	private Long promotionId ;
	
	/**
	 * 活动商品ID
	 */
	private Long promotionItemId ;
	
	/**
	 * 抢投票的活动商品ID
	 */
	private Long stealPromotionItemId ;
	
	/**
	 * 投票数
	 */
	private Integer amount ;
	
	/**
	 * 投票UserId
	 */
	private Long rateUserId ;
	
	/**
	 * 投票UserNick
	 */
	private String rateUserNick ;
	
	/**
	 * 状态
	 */
	private String status ;
	
	/**
	 * 投票类型
	 */
	private String rateType ;
	
	private Date gmtModified ;
	
	private Date gmtCreate ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Long getPromotionItemId() {
		return promotionItemId;
	}

	public void setPromotionItemId(Long promotionItemId) {
		this.promotionItemId = promotionItemId;
	}

	public Long getRateUserId() {
		return rateUserId;
	}

	public void setRateUserId(Long rateUserId) {
		this.rateUserId = rateUserId;
	}

	public String getRateUserNick() {
		return rateUserNick;
	}

	public void setRateUserNick(String rateUserNick) {
		this.rateUserNick = rateUserNick;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Long getStealPromotionItemId() {
		return stealPromotionItemId;
	}

	public void setStealPromotionItemId(Long stealPromotionItemId) {
		this.stealPromotionItemId = stealPromotionItemId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	
}
