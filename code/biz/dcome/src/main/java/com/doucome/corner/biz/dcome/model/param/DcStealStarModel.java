package com.doucome.corner.biz.dcome.model.param;

/**
 * 
 * @author ze2200
 *
 */
public class DcStealStarModel {
	
	private Long toUserId;
	
	private String toUserNick;
	
	private Long fromPromItemId;
	
	private Long fromUserId;
	
	private String fromUserNick;
	
	private int num;

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}
	
	public String getToUserNick() {
		return toUserNick;
	}
	
	public void setToUserNick(String toUserNick) {
		this.toUserNick = toUserNick;
	}

	public void setFromPromItemId(Long fromPromItemId) {
		this.fromPromItemId = fromPromItemId;
	}
	
	public Long getFromPromItemId() {
		return this.fromPromItemId;
	}
	
	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUserNick() {
		return fromUserNick;
	}

	public void setFromUserNick(String fromUserNick) {
		this.fromUserNick = fromUserNick;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
