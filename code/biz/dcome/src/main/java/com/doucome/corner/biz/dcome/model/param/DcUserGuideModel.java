package com.doucome.corner.biz.dcome.model.param;

import com.doucome.corner.biz.dcome.enums.DcUserGuideEnum;

/**
 * 用户引导
 * @author ze2200
 *
 */
public class DcUserGuideModel {
	private Long userId;
	private String userNick;
	
	private Long guideResultId;
	
	private String guideResultName;
	
	private String otherInfo;
	
	private DcUserGuideEnum guideEnum;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserNick() {
		return userNick;
	}
	public Long getGuideResultId() {
		return guideResultId;
	}
	public void setGuideResultId(Long guideResultId) {
		this.guideResultId = guideResultId;
	}
	public String getGuideResultName() {
		return guideResultName;
	}
	public void setGuideResultName(String guideResultName) {
		this.guideResultName = guideResultName;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public DcUserGuideEnum getGuideEnum() {
		return guideEnum;
	}
	public void setGuideEnum(DcUserGuideEnum guideEnum) {
		this.guideEnum = guideEnum;
	}
}
