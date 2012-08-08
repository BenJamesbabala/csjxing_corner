package com.doucome.corner.web.dcome.authz.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.web.dcome.authz.DcSessionOperator;
import com.doucome.corner.web.dcome.context.AuthzContext;
import com.doucome.corner.web.dcome.context.AuthzContextHolder;

/**
 * @author ib 2012-3-31 ÉÏÎç01:53:54
 */
public class DefaultDcSessionOperator implements DcSessionOperator {

    @Autowired
    private DcUserService dcUserService;

    public boolean load(long userId, String nick, String password, String loginSource) {

        DcUserDO userDO = dcUserService.queryUser(userId);

        if (userDO == null) {
            return false;
        }

        AuthzContext authzContext = AuthzContextHolder.getContext();
        authzContext.clearModels();
        authzContext.setUserId(userId);
        authzContext.setExternalId(userDO.getExternalId());
        authzContext.setPfNick(nick);
        authzContext.setOpenKey(password);
        authzContext.setLoginSource(DcLoginSourceEnums.get(loginSource));
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
