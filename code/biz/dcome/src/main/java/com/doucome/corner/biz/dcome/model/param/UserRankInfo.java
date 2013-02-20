package com.doucome.corner.biz.dcome.model.param;

/**
 * 
 * @author ze2200
 *
 */
public class UserRankInfo {
	/**
	 * 
	 */
	private Long userId;
	/**
	 * 
	 */
	private String userNick;
	/**
	 * еецШ.
	 */
	private int rank;
	
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
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
}
