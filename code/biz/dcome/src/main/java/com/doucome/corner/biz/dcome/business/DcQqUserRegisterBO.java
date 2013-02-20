package com.doucome.corner.biz.dcome.business;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.qq.enums.QqGenderEnums;
import com.doucome.corner.biz.core.qq.model.QqUserInfoModel;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dcome.enums.DcGenderEnum;
import com.doucome.corner.biz.dcome.enums.DcUserExternalPfEnum;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.facade.AppCustomParamModel;
import com.doucome.corner.biz.dcome.model.star.HsUserDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsUserService;

/**
 * 类DcQqUserRegisterBO.java的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-7-29 下午02:52:17
 */
public class DcQqUserRegisterBO extends AbstractDcUserRegisterBO<QqUserInfoModel> {
	@Autowired
	private DcUserIntegralOperateBO dcUserIntegralOperateBO ;
    @Autowired
    private HsUserService stUserService;
    
    private ExecutorService executor = Executors.newFixedThreadPool(20);
    
    private static final Log logger = LogFactory.getLog(DcQqUserRegisterBO.class);
	
    @Override
    public DcUserDO registerUser(QqUserInfoModel userInfoModel) {
        if (userInfoModel == null) {
            return null;
        }
        DcUserDO dcUserDO = new DcUserDO();
        dcUserDO.setExternalId(userInfoModel.getOpenId());
        dcUserDO.setExternalPf(DcUserExternalPfEnum.QQ.getValue());
        dcUserDO.setSource(DcUserExternalPfEnum.QQ.getValue() + "_" + userInfoModel.getPlatform());

        QqGenderEnums qqGender = userInfoModel.getGender();
        if (qqGender != null && qqGender.equals(QqGenderEnums.Male)) {
            dcUserDO.setGender(DcGenderEnum.Male.getValue());
        } else if (qqGender != null && qqGender.equals(QqGenderEnums.Female)) {
            dcUserDO.setGender(DcGenderEnum.Female.getValue());
        } else {
            dcUserDO.setGender(DcGenderEnum.UnKnow.getValue());
        }
        Long userId = registerUser(dcUserDO, userInfoModel.getNickName());
        if (userId != null) {
        	
            DcUserDTO result = dcUserService.queryUserByExternalId(dcUserDO.getExternalId(), dcUserDO.getExternalPf());
            if (result != null) {
                uploadUserAvatar(result.getUserId(), userInfoModel.getFigureUrl());
                //新用户注册积分奖励
                dcUserIntegralOperateBO.doUserRegister(result) ;
            }
            return result.getUser();
        }
        return null;
    }
    
    /**
     * 注册星座贝贝用户.
     * @param userModel
     * @return
     */
    public HsUserDTO regiterStUser(QqUserInfoModel userModel) {
    	if (userModel == null) {
    		return null;
    	}
    	HsUserDTO userDTO = new HsUserDTO();
    	userDTO.setExternalId(userModel.getOpenId());
    	userDTO.setExternalPf(userModel.getPlatform());
    	QqGenderEnums qqGender = userModel.getGender();
        if (qqGender != null && qqGender.equals(QqGenderEnums.Male)) {
        	userDTO.setGender(DcGenderEnum.Male.getValue());
        } else if (qqGender != null && qqGender.equals(QqGenderEnums.Female)) {
        	userDTO.setGender(DcGenderEnum.Female.getValue());
        } else {
        	userDTO.setGender(DcGenderEnum.UnKnow.getValue());
        }
        String userNick = null;
        for (int i = 0; i < 6; i++) {
	        try {
	        	userNick = convertUserNick(userModel.getNickName(), i);
	        	userDTO.setUserNick(userNick);
	        	Long userId = stUserService.insertUser(userDTO);
	        	userDTO.setUserId(userId);
	        	return userDTO;
	        } catch (Exception e) {
	        	logger.error("regist error: " + userNick, e);
	        }
        }
        return null;
    }
    
    /**
     * 登陆链接中自定义参数对应的逻辑.
     * @param userDO 
     * @param isNewUser 是否是新用户.
     */
    public void doAppCustomParamActAsyn(String customParam, DcUserDO userDO, boolean isNewUser) {
    	if (StringUtils.isEmpty(customParam)) {
    		return;
    	}
    	try {
    		AppCustomParamModel paramModel = JacksonHelper.fromJSON(customParam, AppCustomParamModel.class);
    		if (customParam == null || !IDUtils.isCorrect(paramModel.getShareUserId())) {
    			return;
    		}
    		//邀请新用户.
    		if(isNewUser) {
    			executor.execute(doInviteFriendAct(paramModel.getShareUserId(), userDO));
    		}
    	} catch(Exception e) {
    		logger.error(e);
    	}
    }
    
    /**
     * 邀请新用户.
     * @param inviteUserId
     * @return
     */
    private FutureTask<Boolean> doInviteFriendAct(final Long userId, final DcUserDO userInvited) {
    	Callable<Boolean> invite = new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				DcUserDTO user = dcUserService.getUser(userId);
				String userNick = user.getNick() ;
				//送积分
				dcUserIntegralOperateBO.doInviteNewUser(user, userInvited) ;
				return true;
			}
		};
		return new FutureTask<Boolean>(invite);
    }
}
