package com.doucome.corner.web.zhe.authz.impl;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.biz.zhe.service.DdzUserService;
import com.doucome.corner.web.zhe.authz.DdzSessionOperator;
import com.doucome.corner.web.zhe.context.AuthzContext;
import com.doucome.corner.web.zhe.context.AuthzContextHolder;

/**
 * 类DefaultDdzSessionOperator.java的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-3-31 上午01:53:54
 */
public class DefaultDdzSessionOperator implements DdzSessionOperator {

    private DdzUserService    ddzUserService;

    private DdzAccountService ddzAccountService;

    public boolean load(String uid) {
        if (StringUtils.isEmpty(uid)) {
            return false;
        }
        DdzUserDO userDO = ddzUserService.getByUid(uid);

        if (userDO == null) {
            return false;
        }

        AuthzContext authzContext = AuthzContextHolder.getContext();
        authzContext.clearModels();
        authzContext.setUid(uid);

        DdzAccountDO accountDO = ddzAccountService.queryAccountDOByUid(uid);
        if (accountDO != null && StringUtils.isBlank(authzContext.getAlipayId())) {
            authzContext.setAlipayId(accountDO.getAlipayId());
        }
        authzContext.setAuthentication(true, true);
        return true;
    }

    @Override
    public void setAlipayId(String alipayId) {
        AuthzContext authzContext = AuthzContextHolder.getContext();
        authzContext.setAlipayId(alipayId);
        authzContext.setTouch(true);
    }

    public void setDdzUserService(DdzUserService ddzUserService) {
        this.ddzUserService = ddzUserService;
    }

    public void setDdzAccountService(DdzAccountService ddzAccountService) {
        this.ddzAccountService = ddzAccountService;
    }

    @Override
    public boolean unload() {
        AuthzContext authzContext = AuthzContextHolder.getContext();
        authzContext.setAuthentication(false, true);
        return true;
    }

}
