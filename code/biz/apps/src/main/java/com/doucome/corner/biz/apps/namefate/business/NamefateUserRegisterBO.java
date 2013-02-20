package com.doucome.corner.biz.apps.namefate.business;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.apps.business.AbstractAppUserRegisterBO;
import com.doucome.corner.biz.apps.enums.GenderEnum;
import com.doucome.corner.biz.apps.model.AbstractUserDTO;
import com.doucome.corner.biz.apps.namefate.model.NamefateUserDTO;
import com.doucome.corner.biz.apps.namefate.service.NamefateUserService;
import com.doucome.corner.biz.core.qq.enums.QqGenderEnums;
import com.doucome.corner.biz.core.qq.model.QqUserInfoModel;
import com.doucome.corner.biz.dal.namefate.dataobject.NamefateUserDO;

/**
 * зЂВс
 * @author langben 2013-1-1
 *
 */
public class NamefateUserRegisterBO extends AbstractAppUserRegisterBO<QqUserInfoModel> {
	
	private static final Log log = LogFactory.getLog(NamefateUserRegisterBO.class) ;

	@Autowired
	private NamefateUserService namefateUserService ;
	
	@Override
	public AbstractUserDTO registerUser(QqUserInfoModel userModel) {
		if (userModel == null) {
    		return null;
    	}
		NamefateUserDO user = new NamefateUserDO();
    	user.setExternalId(userModel.getOpenId());
    	user.setExternalPf(userModel.getPlatform());
    	QqGenderEnums qqGender = userModel.getGender();
        if (qqGender != null && qqGender.equals(QqGenderEnums.Male)) {
        	user.setGender(GenderEnum.Male.getValue());
        } else if (qqGender != null && qqGender.equals(QqGenderEnums.Female)) {
        	user.setGender(GenderEnum.Female.getValue());
        } else {
        	user.setGender(GenderEnum.UnKnow.getValue());
        }
        String userNick = null;
        for (int i = 0; i < 6; i++) {
	        try {
	        	userNick = convertUserNick(userModel.getNickName(), i);
	        	user.setUserNick(userNick);
	        	Long userId = namefateUserService.insertUser(user);
	        	user.setUserId(userId);
	        	return new NamefateUserDTO(user);
	        } catch (Exception e) {
	        	log.error("regist error: " + userNick, e);
	        }
        }
        return null;
	}

}
