package com.doucome.corner.biz.dcome.model.param;

/**
 * ���־���
 * @author ze2200
 *
 */
public class DcAuctionBidModel {
	private Long userId;
	private String userNick;
	/**
	 * ������Ʒid.
	 */
	private Long auctionItemId;
	
	private String auction;
	/**
	 * ����.
	 */
	private Integer bidIntegral;
	
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

	public Integer getBidIntegral() {
		return bidIntegral;
	}

	public void setBidIntegral(Integer bidIntegral) {
		this.bidIntegral = bidIntegral;
	}

	public Long getAuctionItemId() {
		return auctionItemId;
	}

	public void setAuctionItemId(Long auctionItemId) {
		this.auctionItemId = auctionItemId;
	}

	public String getAuction() {
		return auction;
	}

	public void setAuction(String auction) {
		this.auction = auction;
	}
}
