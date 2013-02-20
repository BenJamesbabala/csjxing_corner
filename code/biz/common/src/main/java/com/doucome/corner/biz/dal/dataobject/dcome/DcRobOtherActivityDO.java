package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

@SuppressWarnings("serial")
public class DcRobOtherActivityDO extends AbstractModel {
	/**
	 * 活动id
	 */
	private Long promotionId;
	
	private Long toPromItemId;
	
	private Long fromPromItemId;
	
	private Long toUserId;
	
	private Long fromUserId;
	
	private Long itemId;
	
	private String toUserNick;
	
	private String fromUserNick;
	
	private Integer amount;
	/**
	 * 行为类型,S: STEAL
	 */
	private String rateType;
	
	private String status;
	
	private Integer rateCount;
	/**
	 * 0:被打劫,1:打劫
	 */
	private Integer robResult;
	
	private Date gmtCreate;

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Long getToPromItemId() {
		return toPromItemId;
	}

	public void setToPromItemId(Long toPromItemId) {
		this.toPromItemId = toPromItemId;
	}

	public Long getFromPromItemId() {
		return fromPromItemId;
	}

	public void setFromPromItemId(Long fromPromItemId) {
		this.fromPromItemId = fromPromItemId;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getToUserNick() {
		return toUserNick;
	}

	public void setToUserNick(String toUserNick) {
		this.toUserNick = toUserNick;
	}

	public String getFromUserNick() {
		return fromUserNick;
	}

	public void setFromUserNick(String fromUserNick) {
		this.fromUserNick = fromUserNick;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getRateCount() {
		return rateCount;
	}

	public void setRateCount(Integer rateCount) {
		this.rateCount = rateCount;
	}

	public Integer getRobResult() {
		return robResult;
	}

	public void setRobResult(Integer robResult) {
		this.robResult = robResult;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
}
