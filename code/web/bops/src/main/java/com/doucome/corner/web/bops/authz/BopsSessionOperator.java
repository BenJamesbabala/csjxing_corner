package com.doucome.corner.web.bops.authz;

import com.doucome.corner.biz.dal.dataobject.BopsAdminDO;

/**
 * ��DdzSessionOpService.java��ʵ��������Session����
 * 
 * @author ib 2012-3-31 ����01:52:07
 */
public interface BopsSessionOperator {

    public boolean load(BopsAdminDO adminDO);

    public boolean unload();
}
