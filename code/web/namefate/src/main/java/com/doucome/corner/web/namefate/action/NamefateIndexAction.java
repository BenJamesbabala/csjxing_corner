package com.doucome.corner.web.namefate.action;

import com.doucome.corner.biz.apps.namefate.enums.NamefateUserGuideEnums;
import com.doucome.corner.biz.apps.namefate.model.NamefateUserDTO;

@SuppressWarnings("serial")
public class NamefateIndexAction extends NamefateBasicAction {

	/**
	 * 共有多少人参加了测试，随便一个数字
	 */
	private int trickUserCount ;
	
	private String extId ;
	
	@Override
	public String execute() throws Exception {
		
		NamefateUserDTO user = getUser() ;
		
		if(user != null && user.isGuideDone(NamefateUserGuideEnums.NAMEFATE_TEST.getGuideStr())) {
			return "trickList" ;
		}
		
		return SUCCESS ;
	}

	public int getTrickUserCount() {
		return trickUserCount;
	}

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}
	
	
}
