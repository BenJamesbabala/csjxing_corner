package com.doucome.corner.biz.apps.business;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.apps.model.AbstractUserDTO;
import com.doucome.corner.biz.core.model.AbstractUserInfoModel;

/**
 * 类DcUserRegisterBO.java的实现描述：专门用于注册的BO
 * 
 * @author ib 2012-7-29 上午01:30:14
 */
public abstract class AbstractAppUserRegisterBO<T extends AbstractUserInfoModel> {

    private static final Log  log = LogFactory.getLog(AbstractUserInfoModel.class);
    
    @Autowired
    protected AppImageUploadBO appImageUploadBO;
    
    private String appName ;

    public String getAppName() {
    	return appName ;
    }
    
    public void setAppName(String appName) {
		this.appName = appName;
	}

	public abstract AbstractUserDTO registerUser(T userInfoModel);
    
    protected String convertUserNick(String userNick, int index) {
    	if (index > 6 || index < 1) {
    		return userNick;
    	}
    	StringBuilder tempNick = new StringBuilder(userNick);
    	for(int i = 0; i < index; i++) {
    		tempNick.append(i);
    	}
    	return tempNick.toString();
    }
    
    protected boolean uploadUserAvatar(long userId, String imgUrl) {
        if (imgUrl == null || userId <= 0) {
            return false;
        }
        
        String appName = getAppName() ;
        
        try {
        	appImageUploadBO.uploadUserAvatarFromUrl(appName , userId, imgUrl);
        } catch (Exception e) {
            log.error("update user avatar error, userId:" + userId + " ;url[" + imgUrl + "]", e);
            return false;
        }
        return true;
    }
}
