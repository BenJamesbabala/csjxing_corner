package com.doucome.corner.biz.dcome.model.star;

import java.util.Date;

import com.doucome.corner.biz.core.utils.DateTool;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsUserDO;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dcome.enums.hs.HoroscopeEnum;

/**
 * HsUserDTO
 * @author 
 *
 */
public class HsUserDTO extends AbstractModel {
	private static final long serialVersionUID = 1L;
	
	private HsUserDO hsUser ;
	
	public HsUserDTO() {
		this(null);
	}
	
	public HsUserDTO(HsUserDO hsUser){
		if(hsUser == null){
			hsUser = new HsUserDO();
		}
		this.hsUser = hsUser ;
	}
	
	public Long getUserId() {
		return hsUser.getUserId();
	}

	public void setUserId(Long userId) {
		hsUser.setUserId(userId);
	}

	public String getUserNick() {
		return hsUser.getUserNick();
	}

	public void setUserNick(String userNick) {
		hsUser.setUserNick(userNick);
	}

	public String getExternalId() {
		return hsUser.getExternalId();
	}

	public void setExternalId(String externalId) {
		hsUser.setExternalId(externalId);
	}

	public String getExternalPf() {
		return hsUser.getExternalPf();
	}

	public void setExternalPf(String externalPf) {
		hsUser.setExternalPf(externalPf);
	}

	public String getGender() {
		return hsUser.getGender();
	}

	public void setGender(String gender) {
		hsUser.setGender(gender);
	}

	public String getPassword() {
		return hsUser.getPassword();
	}

	public void setPassword(String password) {
		hsUser.setPassword(password);
	}

	public Integer getIntegral() {
		return hsUser.getIntegral();
	}

	public void setIntegral(Integer integral) {
		hsUser.setIntegral(integral);
	}

	public Long getNewGuide() {
		return hsUser.getNewGuide();
	}

	public void setNewGuide(Long newGuide) {
		hsUser.setNewGuide(newGuide);
	}

	public Date getGmtLastLogin() {
		return hsUser.getGmtLastLogin();
	}
	
	public String getGmtLastLoginFmt() {
		return DateTool.format(hsUser.getGmtLastLogin(), "yyyy-MM-dd");
	}
	
	public Date getGmtFollowQzone() {
		return hsUser.getGmtFollowQzone();
	}
	
	public String getGmtFollowQzoneFmt() {
		return DateTool.format(hsUser.getGmtFollowQzone(), "yyyy-MM-dd");
	}

	public void setGmtFollowQzone(Date gmtFollowQzone) {
		hsUser.setGmtFollowQzone(gmtFollowQzone);
	}

	public Date getGmtModified() {
		return hsUser.getGmtModified();
	}
	
	public String getGmtModifiedFmt() {
		return DateTool.format(hsUser.getGmtModified(), "yyyy-MM-dd");
	}

	public Date getGmtCreate() {
		return hsUser.getGmtCreate();
	}
	
	public Integer getHsId() {
		return hsUser.getHsId();
	}
	
	public HoroscopeEnum getHsEnum() {
		return HoroscopeEnum.toEnum(getHsId());
	}
	
	public void setHsId(Integer hsId) {
		hsUser.setHsId(hsId);
	}
	
	public HsUserDO toDO() {
		return hsUser;
	}
}
