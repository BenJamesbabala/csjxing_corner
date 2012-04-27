package com.doucome.corner.web.bops.authz;

import com.doucome.corner.biz.dal.dataobject.BopsAdminDO;

/**
 * 类DdzSessionOpService.java的实现描述：Session操作
 * 
 * @author ib 2012-3-31 上午01:52:07
 */
public interface BopsSessionOperator {

    public boolean load(BopsAdminDO adminDO);

    public boolean unload();
}
