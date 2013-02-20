package com.doucome.corner.biz.dcome.model.param;

/**
 * 发布商品
 * @author ze2200
 *
 */
public class DcPubPromItemModel {
	private Long userId;
	
	private String userNick;
	
	private Long promItemId;
	
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
	
	public Long getPromItemId() {
		return promItemId;
	}
	
	public void setPromItemId(Long promItemId) {
		this.promItemId = promItemId;
	}
}
