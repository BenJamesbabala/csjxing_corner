package com.doucome.corner.biz.zhe.service;

import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.model.AlipayLoginDTO;

/**
 * ��DdzTaobaoLoginService.java��ʵ��������TODO ��ʵ������
 * 
 * @author ib 2012-4-1 ����12:46:16
 */
public interface DdzUserRegisterService {

    public DdzUserDO registerByAlipay(AlipayLoginDTO loginDTO);
}
