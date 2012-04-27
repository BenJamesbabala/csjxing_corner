package com.doucome.corner.biz.zhe.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.GenderEnums;
import com.doucome.corner.biz.core.taobao.dto.TaobaoUserDTO;
import com.doucome.corner.biz.core.utils.MD5Util;
import com.doucome.corner.biz.core.utils.RandomStringUtil;
import com.doucome.corner.biz.core.utils.UidCreateUtil;
import com.doucome.corner.biz.core.utils.ValidateUtil;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.service.DdzTaobaoRegisterService;
import com.doucome.corner.biz.zhe.service.DdzUserService;

/**
 * 类DdzTaobaoLoginServiceImpl.java的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-4-1 上午12:55:24
 */
public class DdzTaobaoRegisterServiceImpl implements DdzTaobaoRegisterService {

    @Autowired
    private DdzUserService ddzUserService;

    @Override
    public DdzUserDO register(TaobaoUserDTO tbUserDTO) {
        if (tbUserDTO == null) {
            return null;
        }
        DdzUserDO userDO = new DdzUserDO();
        userDO.setUid(UidCreateUtil.createUid(tbUserDTO.getNick(), 12));
        userDO.setLoginId(tbUserDTO.getNick());
        userDO.setTaobaoId(tbUserDTO.getNick());
        String randomPassword = RandomStringUtil.randomString(3, 3);
        userDO.setMd5Password(MD5Util.getMD5(randomPassword));
        String alipayId = tbUserDTO.getAlipayAccount();
        if (StringUtils.isNotBlank(alipayId)) {
            if (ValidateUtil.checkEmailFormat(alipayId)) {
                userDO.setEmail(alipayId);
            } else if (ValidateUtil.checkIsMobile(alipayId)) {
                userDO.setMobile(alipayId);
            }
        }
        if (StringUtils.equalsIgnoreCase(tbUserDTO.getSex(), "m")) {
            userDO.setGender(GenderEnums.MALE.getValue());
        } else if (StringUtils.equalsIgnoreCase(tbUserDTO.getSex(), "m")) {
            userDO.setGender(GenderEnums.FEMALE.getValue());
        } else {
            userDO.setGender(GenderEnums.UNKNOW.getValue());
        }
        ddzUserService.createUser(userDO);
        return ddzUserService.getByUid(userDO.getUid());
    }
}
