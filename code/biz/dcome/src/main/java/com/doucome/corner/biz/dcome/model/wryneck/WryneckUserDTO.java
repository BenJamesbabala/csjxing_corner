package com.doucome.corner.biz.dcome.model.wryneck;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dal.wryneck.dataobject.WryneckTestDO;
import com.doucome.corner.biz.dal.wryneck.dataobject.WryneckUserDO;

public class WryneckUserDTO extends AbstractModel {
	
	private static final Log log = LogFactory.getLog(WryneckUserDTO.class) ;

	private WryneckUserDO user ;

	private WryneckTestDTO wryneckTest ;
	
	public WryneckUserDTO(WryneckUserDO user){
		if(user == null){
			user = new WryneckUserDO() ;
		}
		
		this.user = user ;
		
		if(StringUtils.isNotBlank(user.getTestResult())){
			try {
				WryneckTestDO wryneckTestDO = JacksonHelper.fromJSON(user.getTestResult(), WryneckTestDO.class) ;
				wryneckTest = new WryneckTestDTO(wryneckTestDO) ;
			} catch (Exception e){
				log.error(e.getMessage() , e) ;
			}
 		}
	}
	
	public WryneckUserDO getUser(){
		return this.user ;
	}
	
	/**
	 * -------------------------------------
	 * @return
	 */
	
	public String getTestResult(){
		return user.getTestResult() ;
	}

	public Long getUserId() {
		return user.getUserId();
	}

	public WryneckTestDTO getWryneckTestModel() {
		return wryneckTest;
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
