package com.doucome.corner.biz.zhe.service;

import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.model.AlipayLoginDTO;

/**
 * 类DdzTaobaoLoginService.java的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-4-1 上午12:46:16
 */
public interface DdzUserRegisterService {

    public DdzUserDO registerByAlipay(AlipayLoginDTO loginDTO);
}
