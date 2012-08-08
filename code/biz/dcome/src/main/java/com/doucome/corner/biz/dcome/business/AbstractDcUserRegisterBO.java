package com.doucome.corner.biz.dcome.business;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.AbstractUserInfoModel;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dcome.service.DcUserService;

/**
 * 类DcUserRegisterBO.java的实现描述：专门用于注册的BO
 * 
 * @author ib 2012-7-29 上午01:30:14
 */
public abstract class AbstractDcUserRegisterBO<T extends AbstractUserInfoModel> {

    private static final Log  log = LogFactory.getLog(AbstractUserInfoModel.class);

    @Autowired
    protected DcUserService   dcUserService;
    @Autowired
    protected DcImageUploadBO dcImageUploadBO;

    public abstract DcUserDO registerUser(T userInfoModel);

    protected Long registerUser(DcUserDO userDO, String nick) {
        Calendar calendar = Calendar.getInstance();
        String[] nickS = new String[10];
        nickS[0] = nick;
        nickS[1] = nick + calendar.get(Calendar.YEAR);
        nickS[2] = nickS[1] + (calendar.get(Calendar.MONTH) + 1);
        nickS[3] = nickS[2] + calendar.get(Calendar.DAY_OF_MONTH);
        for (int i = 4; i < nickS.length; i++) {
            nickS[i] = nick + Math.round(Math.random() * 8999 + 1000);
        }

        for (String nickname : nickS) {
            try {
                if (StringUtils.isNotBlank(nickname)) {
                    userDO.setNick(nickname);
                    Long userId = dcUserService.insertUser(userDO);
                    return userId;
                }
            } catch (Exception e) {
                log.error("regist error: " + nickname, e);
            }
        }
        return null;
    }

    protected boolean uploadUserAvatar(long userId, String imgUrl) {
        if (imgUrl == null || userId <= 0) {
            return false;
        }
        try {
            dcImageUploadBO.uploadUserAvatarFromUrl(userId, imgUrl);
        } catch (Exception e) {
            log.error("update user avatar error, userId:" + userId + " ;url[" + imgUrl + "]", e);
            return false;
        }
        return true;
    }
}
