package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcPromotionItemDO extends AbstractModel {
	
	private Long id;
	/**
	 * 活动ID
	 */
	private Long promotionId;
	
	/**
	 * itemId
	 */
	private Long itemId;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 用户昵称.
	 */
	private String userNick;
	
	/**
	 * 投票数
	 */
	private Integer rateCount ;
	/**
	 * 分享状态. Y/N
	 */
	private String shareStatus = "N";
	
	/**
	 * 分别对应每个时段小时可用的愿望值数，被抢或者被领取都会减少数值
	 */
	private Integer hour8 = 2;
	private Integer hour9 = 2 ;
	private Integer hour10 = 2 ;
	private Integer hour11 = 2 ;
	private Integer hour12 = 2 ;
	private Integer hour13 = 2 ;
	private Integer hour14 = 2 ;
	private Integer hour15 = 2 ;
	private Integer hour16 = 2;
	private Integer hour17 = 2 ;
	private Integer hour18 = 2 ;
	private Integer hour19 = 2 ;
	private Integer hour20 = 2 ;
	private Integer hour21 = 2 ;
	private Integer hour22 = 2 ;
	
	private Long newGuide ;
	
	private Date gmtModified ;
	
	private Date gmtCreate;
	
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
	
	public Long getItemId() {
		return itemId;
	}
	
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserNick() {
		return this.userNick;
	}
	
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}
	
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Integer getRateCount() {
		return rateCount == null? 0: rateCount;
	}

	public void setRateCount(Integer rateCount) {
		this.rateCount = rateCount;
	}
	
	public String getShareStatus() {
		return this.shareStatus;
	}
	
	public void setShareStatus(String status) {
		this.shareStatus = status;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Integer getHour8() {
		return hour8;
	}

	public void setHour8(Integer hour8) {
		this.hour8 = hour8;
	}

	public Integer getHour9() {
		return hour9;
	}

	public void setHour9(Integer hour9) {
		this.hour9 = hour9;
	}

	public Integer getHour10() {
		return hour10;
	}

	public void setHour10(Integer hour10) {
		this.hour10 = hour10;
	}

	public Integer getHour11() {
		return hour11;
	}

	public void setHour11(Integer hour11) {
		this.hour11 = hour11;
	}

	public Integer getHour12() {
		return hour12;
	}

	public void setHour12(Integer hour12) {
		this.hour12 = hour12;
	}

	public Integer getHour13() {
		return hour13;
	}

	public void setHour13(Integer hour13) {
		this.hour13 = hour13;
	}

	public Integer getHour14() {
		return hour14;
	}

	public void setHour14(Integer hour14) {
		this.hour14 = hour14;
	}

	public Integer getHour15() {
		return hour15;
	}

	public void setHour15(Integer hour15) {
		this.hour15 = hour15;
	}

	public Integer getHour16() {
		return hour16;
	}

	public void setHour16(Integer hour16) {
		this.hour16 = hour16;
	}

	public Integer getHour17() {
		return hour17;
	}

	public void setHour17(Integer hour17) {
		this.hour17 = hour17;
	}

	public Integer getHour18() {
		return hour18;
	}

	public void setHour18(Integer hour18) {
		this.hour18 = hour18;
	}

	public Integer getHour19() {
		return hour19;
	}

	public void setHour19(Integer hour19) {
		this.hour19 = hour19;
	}

	public Integer getHour20() {
		return hour20;
	}

	public void setHour20(Integer hour20) {
		this.hour20 = hour20;
	}

	public Integer getHour21() {
		return hour21;
	}

	public void setHour21(Integer hour21) {
		this.hour21 = hour21;
	}

	public Integer getHour22() {
		return hour22;
	}

	public void setHour22(Integer hour22) {
		this.hour22 = hour22;
	}

	public Long getNewGuide() {
		return newGuide;
	}

	public void setNewGuide(Long newGuide) {
		this.newGuide = newGuide;
	}
	
}
