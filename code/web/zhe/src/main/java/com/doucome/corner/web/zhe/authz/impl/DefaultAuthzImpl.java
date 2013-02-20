package com.doucome.corner.web.zhe.authz.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.biz.zhe.service.DdzUserService;
import com.doucome.corner.web.zhe.authz.DdzAuthz;
import com.doucome.corner.web.zhe.context.AuthzContext;
import com.doucome.corner.web.zhe.context.AuthzContextHolder;
import com.doucome.corner.web.zhe.context.AuthzContextModelEnum;

/**
 * 类DefaultAuthzImpl.java的实现描述：Authz.java的默认实现
 * 
 * @author ib 2012-3-24 下午01:27:48
 */
public class DefaultAuthzImpl implements DdzAuthz {

    @Autowired
    private DdzAccountService ddzAccountService;

    @Autowired
    private DdzUserService    ddzUserService;

    @Override
    public DdzAccountDO getAccount() {
        AuthzContext authzContext = AuthzContextHolder.getContext();
        DdzAccountDO accountDO = (DdzAccountDO) authzContext.getModel(AuthzContextModelEnum.DDZ_ACCOUNT_MODEL);
        if (accountDO == null) {
            String uid = authzContext.getUid();
            if (StringUtils.isNotEmpty(uid)) {
            	DdzUserDO user = ddzUserService.getByUid(uid) ;
            	if(user != null){
            		String alipayId = user.getAlipayId() ;
            		if(StringUtils.isNotBlank(alipayId)){
            			accountDO = ddzAccountService.queryAccountDOByAlipayId(alipayId);
            		}
            	}
            } else {
                String alipayId = authzContext.getAlipayId();
                accountDO = ddzAccountService.queryAccountDOByAlipayId(alipayId);
            }
            if (accountDO != null) {
                authzContext.setModel(AuthzContextModelEnum.DDZ_ACCOUNT_MODEL, accountDO);
            }
        }
        return accountDO;
    }

    @Override
    public DdzUserDO getUser() {
        AuthzContext authzContext = AuthzContextHolder.getContext();
        DdzUserDO userDO = (DdzUserDO) authzContext.getModel(AuthzContextModelEnum.DDZ_USER_MODEL);
        if (userDO == null) {
            String uid = authzContext.getUid();
            if (StringUtils.isNotBlank(uid)) {
                userDO = ddzUserService.getByUid(uid);
                authzContext.setModel(AuthzContextModelEnum.DDZ_USER_MODEL, userDO);
            }
        }
        return userDO;
    }

    @Override
    public String getAlipayId() {
        AuthzContext authzContext = AuthzContextHolder.getContext();
        return authzContext.getAlipayId();
    }

    @Override
    public String getLoginId() {
        AuthzContext authzContext = AuthzContextHolder.getContext();
        return authzContext.getLoginId();
    }

    @Override
    public boolean isLogin() {
        return AuthzContextHolder.getContext().isAuthentication();
    }

    public String getUid() {
        return AuthzContextHolder.getContext().getUid();
    }

    @Override
    public boolean isPrivateUser() {
        return AuthzContextHolder.getContext().isPrivateUser();
    }

    /**
     * ------------------------------------------------
     */

}
