package com.doucome.corner.web.bops.model;

import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;

public class PromOutcodeModel {

	private DcItemDTO item ;
	
	private DcUserDTO user ;
	
	private int integralCount ;

	public DcItemDTO getItem() {
		return item;
	}

	public void setItem(DcItemDTO item) {
		this.item = item;
	}

	public DcUserDTO getUser() {
		return user;
	}

	public void setUser(DcUserDTO user) {
		this.user = user;
	}

	public int getIntegralCount() {
		return integralCount;
	}

	public void setIntegralCount(int integralCount) {
		this.integralCount = integralCount;
	}
	
}
