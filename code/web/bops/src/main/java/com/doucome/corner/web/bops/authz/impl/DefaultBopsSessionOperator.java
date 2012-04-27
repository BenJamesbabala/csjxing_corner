package com.doucome.corner.web.bops.authz.impl;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.dal.dataobject.BopsAdminDO;
import com.doucome.corner.web.bops.authz.BopsSessionOperator;
import com.doucome.corner.web.bops.context.AuthzContext;
import com.doucome.corner.web.bops.context.AuthzContextHolder;
import com.doucome.corner.web.bops.context.AuthzContextModelEnum;

/**
 * 类DefaultDdzSessionOperator.java的实现描述：认证信息写入操作工具
 * 
 * @author ib 2012-3-31 上午01:53:54
 */
public class DefaultBopsSessionOperator implements BopsSessionOperator {

    @Override
    public boolean load(BopsAdminDO adminDO) {
        if (adminDO == null || StringUtils.isEmpty(adminDO.getAdminId())) {
            return false;
        }
        AuthzContext authzContext = AuthzContextHolder.getContext();
        authzContext.clearModels();
        authzContext.setAdminId(adminDO.getAdminId());
        authzContext.setModel(AuthzContextModelEnum.BOPS_ADMIN_MODEL, adminDO);
        authzContext.setAuthentication(true, true);
        return true;
    }

    @Override
    public boolean unload() {
        AuthzContext authzContext = AuthzContextHolder.getContext();
        authzContext.setAuthentication(false, true);
        return true;
    }

}
