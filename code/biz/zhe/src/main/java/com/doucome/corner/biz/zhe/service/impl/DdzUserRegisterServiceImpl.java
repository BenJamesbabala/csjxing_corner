package com.doucome.corner.biz.zhe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.utils.MD5Util;
import com.doucome.corner.biz.core.utils.RandomStringUtil;
import com.doucome.corner.biz.core.utils.UidCreateUtil;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.model.AlipayLoginDTO;
import com.doucome.corner.biz.zhe.service.DdzUserRegisterService;
import com.doucome.corner.biz.zhe.service.DdzUserService;

/**
 * 类DdzTaobaoLoginServiceImpl.java的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-4-1 上午12:55:24
 */
public class DdzUserRegisterServiceImpl implements DdzUserRegisterService {

    @Autowired
    private DdzUserService ddzUserService;

    @Override
    public DdzUserDO registerByAlipay(AlipayLoginDTO loginDTO) {
        if (loginDTO == null) {
            return null;
        }
        DdzUserDO userDO = new DdzUserDO();
        userDO.setUid(UidCreateUtil.createUid(loginDTO.getNativeAlipayId(), 12));
        userDO.setLoginId(loginDTO.getNativeAlipayId());
        userDO.setNativeAlipayId(loginDTO.getNativeAlipayId()) ;
        String randomPassword = RandomStringUtil.randomString(3, 3);
        userDO.setMd5Password(MD5Util.getMD5(randomPassword));
        
        ddzUserService.createUser(userDO);
        return ddzUserService.getByUid(userDO.getUid());
    }
}
