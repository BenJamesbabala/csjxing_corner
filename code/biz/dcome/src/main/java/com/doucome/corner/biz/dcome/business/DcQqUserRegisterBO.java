package com.doucome.corner.biz.dcome.business;

import com.doucome.corner.biz.core.qq.enums.QqGenderEnums;
import com.doucome.corner.biz.core.qq.model.QqUserInfoModel;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dcome.enums.DcGenderEnum;
import com.doucome.corner.biz.dcome.enums.DcUserExternalPfEnum;

/**
 * 类DcQqUserRegisterBO.java的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-7-29 下午02:52:17
 */
public class DcQqUserRegisterBO extends AbstractDcUserRegisterBO<QqUserInfoModel> {

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
            DcUserDO result = dcUserService.queryUserByExternalId(dcUserDO.getExternalId(), dcUserDO.getExternalPf());
            if (result != null) {
                uploadUserAvatar(result.getUserId(), userInfoModel.getFigureUrl());
            }
            return result;
        }
        return null;
    }

}
