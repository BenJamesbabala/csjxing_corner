package com.doucome.corner.biz.dcome.model.param;

public class DcInviteModel {
	private Long userId;
	private String userNick;
	private Long invitedUserId;
	private String invitedUserNick;
	
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

	public Long getInvitedUserId() {
		return invitedUserId;
	}

	public void setInvitedUserId(Long invitedUserId) {
		this.invitedUserId = invitedUserId;
	}

	public String getInvitedUserNick() {
		return invitedUserNick;
	}

	public void setInvitedUserNick(String invitedUserNick) {
		this.invitedUserNick = invitedUserNick;
	}
}
