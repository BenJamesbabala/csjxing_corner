package com.doucome.corner.biz.dcome.model.param;

/**
 * 积分兑换
 * @author ze2200
 *
 */
public class DcExchangeItemModel {
	private Long userId;
	private String userNick;
	/**
	 * 竞价物品id.
	 */
	private Long exItemId;
	
	private String exItemTitle;
	/**
	 * 出价.
	 */
	private Integer exIntegral;
	
	/**
	 * 
	 */
	private Long awardId  ;
	
	private String otherInfo;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public Long getExItemId() {
		return exItemId;
	}
	public void setExItemId(Long exItemId) {
		this.exItemId = exItemId;
	}
	public String getExItemTitle() {
		return exItemTitle;
	}
	public void setExItemTitle(String exItemTitle) {
		this.exItemTitle = exItemTitle;
	}
	public Integer getExIntegral() {
		return exIntegral;
	}
	public void setExIntegral(Integer exIntegral) {
		this.exIntegral = exIntegral;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public Long getAwardId() {
		return awardId;
	}
	public void setAwardId(Long awardId) {
		this.awardId = awardId;
	}
	
}
