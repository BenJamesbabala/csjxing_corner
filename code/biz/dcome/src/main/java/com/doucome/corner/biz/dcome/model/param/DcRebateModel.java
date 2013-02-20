package com.doucome.corner.biz.dcome.model.param;

import com.doucome.corner.biz.dcome.utils.DcItemUtils;


public class DcRebateModel {
	
	private String userNick;
	
	private Long userId;
	
	private Long itemId;
	
	private String itemTitle;
	
	private int integralCount;
	
	private Long itemOwnerUserId;
	
	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public int getIntegralCount() {
		return integralCount;
	}

	public void setIntegralCount(int integralCount) {
		this.integralCount = integralCount;
	}

	public Long getItemOwnerUserId() {
		return itemOwnerUserId;
	}

	public void setItemOwnerUserId(Long itemOwnerUserId) {
		this.itemOwnerUserId = itemOwnerUserId;
	}
	
	public boolean isUserSelf() {
		return userId.equals(itemOwnerUserId);
	}
	
	public boolean isPgcOwner() {
		return DcItemUtils.PGC_ITEM_CREATOR.equals(itemOwnerUserId);
	}
}
