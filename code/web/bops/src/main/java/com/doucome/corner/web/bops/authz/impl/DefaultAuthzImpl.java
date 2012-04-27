package com.doucome.corner.web.bops.authz.impl;

import com.doucome.corner.biz.dal.dataobject.BopsAdminDO;
import com.doucome.corner.web.bops.authz.BopsAuthz;
import com.doucome.corner.web.bops.context.AuthzContextHolder;
import com.doucome.corner.web.bops.context.AuthzContextModelEnum;

/**
 * ��DefaultAuthzImpl.java��ʵ��������Authz.java��Ĭ��ʵ��
 * 
 * @author ib 2012-3-24 ����01:27:48
 */
public class DefaultAuthzImpl implements BopsAuthz {

    @Override
    public BopsAdminDO getAdminDO() {
        return (BopsAdminDO) AuthzContextHolder.getContext().getModel(AuthzContextModelEnum.BOPS_ADMIN_MODEL);
    }

    @Override
    public boolean isLogin() {
        return AuthzContextHolder.getContext().isAuthentication();
    }

    @Override
    public String getAdminId() {
        return AuthzContextHolder.getContext().getAdminId();
    }

}
