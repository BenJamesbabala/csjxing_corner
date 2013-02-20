package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

/**
 * �ͶƱ
 * @author langben 2012-8-13
 *
 */
public class DcPromotionRateDetailDO {

	private Long id ;
	
	/**
	 * �ID
	 */
	private Long promotionId ;
	
	/**
	 * ���ƷID
	 */
	private Long promotionItemId ;
	
	/**
	 * ��ͶƱ�Ļ��ƷID
	 */
	private Long stealPromotionItemId ;
	
	/**
	 * ͶƱ��
	 */
	private Integer amount ;
	
	/**
	 * ͶƱUserId
	 */
	private Long rateUserId ;
	
	/**
	 * ͶƱUserNick
	 */
	private String rateUserNick ;
	
	/**
	 * ״̬
	 */
	private String status ;
	
	/**
	 * ͶƱ����
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
