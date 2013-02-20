package com.doucome.corner.biz.dcome.model.param;
/**
 * 
 * @author ze2200
 *
 */
public class DcAppInvitationModel {
	
	private Long userId;
	private String userNick;
	private String inviteeOpenIds;
	
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

	public String getInviteeOpenIds() {
		return inviteeOpenIds;
	}

	public void setInviteeOpenIds(String inviteeOpenIds) {
		this.inviteeOpenIds = inviteeOpenIds;
	}
}
