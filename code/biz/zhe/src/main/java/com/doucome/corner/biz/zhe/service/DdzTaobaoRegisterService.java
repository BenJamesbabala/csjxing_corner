package com.doucome.corner.biz.zhe.service;

import com.doucome.corner.biz.core.taobao.dto.TaobaoUserDTO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;

/**
 * ��DdzTaobaoLoginService.java��ʵ��������TODO ��ʵ������
 * 
 * @author ib 2012-4-1 ����12:46:16
 */
public interface DdzTaobaoRegisterService {

    public DdzUserDO register(TaobaoUserDTO tbUserDTO);
}
