package com.doucome.corner.biz.dcome.service.impl;

import java.util.List;

import com.doucome.corner.biz.dcome.service.DcUserFilterService;

public class DcUserFilterServiceImpl implements DcUserFilterService {
	
	private List<Long> whiteListUserIds;
	
	@Override
	public boolean isWhiteListUser(Long userId) {
		return whiteListUserIds.contains(userId);
	}
	
	@Override
	public boolean isMessageWhiteListUser(Long userId) {
		return whiteListUserIds.contains(userId);
	}
	
	@Override
	public List<Long> getWhiteListUser() {
		return whiteListUserIds ;
	}
	
	public void setWhiteListUserIds(List<Long> whiteListUserIds) {
		this.whiteListUserIds = whiteListUserIds;
	}
	
	
}
