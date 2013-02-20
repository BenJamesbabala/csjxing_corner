package com.doucome.corner.biz.dcome.model.param;

import com.doucome.corner.biz.dcome.enums.DcShareObjectEnum;

/**
 * 
 * @author ze2200
 *
 */
public class DcShareModel {
	
	private Long userId;
	private String userNick;
	/**
	 * ������.
	 */
	private DcShareObjectEnum shareObject;
	private Long shareObjectId;
	
	private String itemTitle ;
	/**
	 * ������Ϣ.
	 */
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

	/**
	 * �������.������.
	 * @return
	 */
	public DcShareObjectEnum getShareObject() {
		return shareObject;
	}

	/**
	 * �������.������.
	 * @param shareObject
	 */
	public void setShareObject(DcShareObjectEnum shareObject) {
		this.shareObject = shareObject;
	}

	public Long getShareObjectId() {
		return shareObjectId;
	}

	public void setShareObjectId(Long shareObjectId) {
		this.shareObjectId = shareObjectId;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
}
