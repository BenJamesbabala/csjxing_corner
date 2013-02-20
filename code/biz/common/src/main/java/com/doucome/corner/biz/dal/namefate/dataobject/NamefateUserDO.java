package com.doucome.corner.biz.dal.namefate.dataobject;

import java.util.Date;

public class NamefateUserDO {

	private Long userId;
	
	private String userNick;
	/**
     * 外部平台id
     */
    private String externalId;
    /**
     * 来源平台
     */
    private String externalPf;
    
    private String gender;
    
    private String password;
    
    private Integer integralCount ;
    
    /**
     * 新手引导.数据库字段为bigint(16)
     */
    private Long newGuide;
        
    private Date gmtLastLogin;
    
    private Date gmtFollowQzone;
    
    private Date gmtModified;
    
    private Date gmtCreate;

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

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getExternalPf() {
		return externalPf;
	}

	public void setExternalPf(String externalPf) {
		this.externalPf = externalPf;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getNewGuide() {
		return newGuide;
	}

	public void setNewGuide(Long newGuide) {
		this.newGuide = newGuide;
	}

	public Date getGmtLastLogin() {
		return gmtLastLogin;
	}

	public void setGmtLastLogin(Date gmtLastLogin) {
		this.gmtLastLogin = gmtLastLogin;
	}
	
	public Date getGmtFollowQzone() {
		return gmtFollowQzone;
	}

	public void setGmtFollowQzone(Date gmtFollowQzone) {
		this.gmtFollowQzone = gmtFollowQzone;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Integer getIntegralCount() {
		return integralCount;
	}

	public void setIntegralCount(Integer integralCount) {
		this.integralCount = integralCount;
	}

}
