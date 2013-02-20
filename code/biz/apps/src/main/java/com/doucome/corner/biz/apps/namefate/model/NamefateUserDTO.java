package com.doucome.corner.biz.apps.namefate.model;

import java.util.Date;

import com.doucome.corner.biz.apps.model.AbstractUserDTO;
import com.doucome.corner.biz.apps.namefate.enums.NamefateUserGuideEnums;
import com.doucome.corner.biz.dal.namefate.dataobject.NamefateUserDO;

public class NamefateUserDTO extends AbstractUserDTO {

	private NamefateUserDO user ;
	
	public NamefateUserDTO(NamefateUserDO user){
		if(user == null){
			user = new NamefateUserDO() ;
		}
		
		this.user = user ;
		
	}
	
	public NamefateUserDO getUser(){
		return this.user ;
	}
	
	/**
	 * 是否已经引导过
	 * @param guideName
	 * @return
	 */
	public boolean isGuideDone(String guideName){
		
		NamefateUserGuideEnums guideEnums = NamefateUserGuideEnums.toEnum(guideName) ;
		if(guideEnums == NamefateUserGuideEnums.UNKNOWN){
			return false ;
		}
		
		return guideEnums.isGuideDone(this.getNewGuide()) ;
		
	}
	
	/**
	 * -------------------------------------
	 * @return
	 */

	public Long getUserId() {
		return user.getUserId();
	}

	public String getUserNick() {
		return user.getUserNick();
	}

	public String getExternalId() {
		return user.getExternalId();
	}

	public String getExternalPf() {
		return user.getExternalPf();
	}

	public String getGender() {
		return user.getGender();
	}

	public String getPassword() {
		return user.getPassword();
	}

	public Long getNewGuide() {
		return user.getNewGuide();
	}

	public Date getGmtLastLogin() {
		return user.getGmtLastLogin();
	}

	public Date getGmtFollowQzone() {
		return user.getGmtFollowQzone();
	}

	public Date getGmtModified() {
		return user.getGmtModified();
	}

	public Date getGmtCreate() {
		return user.getGmtCreate();
	}

	public Integer getIntegralCount() {
		return user.getIntegralCount();
	}
	
}
