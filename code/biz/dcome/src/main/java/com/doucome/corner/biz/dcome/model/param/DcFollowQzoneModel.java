package com.doucome.corner.biz.dcome.model.param;

/**
 * ¹Ø×¢¿Õ¼ä
 * @author ze2200
 *
 */
public class DcFollowQzoneModel {
	private Long userId;
	private String userNick;
	private String qzoneName;
	
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

	public String getQzoneName() {
		return qzoneName;
	}

	public void setQzoneName(String qzoneName) {
		this.qzoneName = qzoneName;
	}
}
