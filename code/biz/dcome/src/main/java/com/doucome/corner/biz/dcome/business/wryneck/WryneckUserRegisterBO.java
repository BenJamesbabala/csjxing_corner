package com.doucome.corner.biz.dcome.business.wryneck;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.qq.enums.QqGenderEnums;
import com.doucome.corner.biz.core.qq.model.QqUserInfoModel;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dal.wryneck.dataobject.WryneckUserDO;
import com.doucome.corner.biz.dcome.business.AbstractDcUserRegisterBO;
import com.doucome.corner.biz.dcome.enums.DcGenderEnum;
import com.doucome.corner.biz.dcome.model.wryneck.WryneckUserDTO;
import com.doucome.corner.biz.dcome.service.wryneck.WryneckUserService;

/**
 * зЂВс
 * @author langben 2013-1-1
 *
 */
public class WryneckUserRegisterBO extends AbstractDcUserRegisterBO<QqUserInfoModel> {
	
	private static final Log log = LogFactory.getLog(WryneckUserRegisterBO.class) ;

	@Autowired
	private WryneckUserService wryneckUserService ;
	
	
	public WryneckUserDTO registerWryneckUser(QqUserInfoModel userModel) {
		if (userModel == null) {
    		return null;
    	}
    	WryneckUserDO user = new WryneckUserDO();
    	user.setExternalId(userModel.getOpenId());
    	user.setExternalPf(userModel.getPlatform());
    	QqGenderEnums qqGender = userModel.getGender();
        if (qqGender != null && qqGender.equals(QqGenderEnums.Male)) {
        	user.setGender(DcGenderEnum.Male.getValue());
        } else if (qqGender != null && qqGender.equals(QqGenderEnums.Female)) {
        	user.setGender(DcGenderEnum.Female.getValue());
        } else {
        	user.setGender(DcGenderEnum.UnKnow.getValue());
        }
        String userNick = null;
        for (int i = 0; i < 6; i++) {
	        try {
	        	userNick = convertUserNick(userModel.getNickName(), i);
	        	user.setUserNick(userNick);
	        	Long userId = wryneckUserService.insertUser(user);
	        	user.setUserId(userId);
	        	return new WryneckUserDTO(user);
	        } catch (Exception e) {
	        	log.error("regist error: " + userNick, e);
	        }
        }
        return null;
	}

	@Override
	public DcUserDO registerUser(QqUserInfoModel userInfoModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
