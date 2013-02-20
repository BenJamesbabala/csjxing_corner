package com.doucome.corner.biz.zhe.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class ManualSettleResult {

	private int effectCount = 0 ;
	
	private List<Long> settleIdList = new ArrayList<Long>();
	
	public boolean isSuccess(){
		return CollectionUtils.isNotEmpty(settleIdList) ;
	}

	public int getEffectCount() {
		return effectCount;
	}

	public void setEffectCount(int effectCount) {
		this.effectCount = effectCount;
	}

	public List<Long> getSettleIdList() {
		return settleIdList;
	}

	public void setSettleIdList(List<Long> settleIdList) {
		this.settleIdList = settleIdList;
	}

	public void addSettleId(Long settleId){
		settleIdList.add(settleId) ;
	}
	
}
