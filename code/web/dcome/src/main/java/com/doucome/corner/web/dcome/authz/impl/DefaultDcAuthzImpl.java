package com.doucome.corner.web.dcome.authz.impl;

import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.web.dcome.authz.DcAuthz;
import com.doucome.corner.web.dcome.authz.model.PfModel;
import com.doucome.corner.web.dcome.authz.model.QQPfModel;
import com.doucome.corner.web.dcome.context.AuthzContext;
import com.doucome.corner.web.dcome.context.AuthzContextHolder;

/**
 * 类DefaultDcAuthzImpl.java的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-7-28 下午11:31:39
 */
public class DefaultDcAuthzImpl implements DcAuthz {

    @Override
    public String getPfNick() {
        AuthzContext authzContext = AuthzContextHolder.getContext();
        return authzContext.getPfNick();
    }

    @Override
    public long getUserId() {
        AuthzContext authzContext = AuthzContextHolder.getContext();
        return authzContext.getUserId();
    }

    @Override
    public boolean isLogin() {
        return AuthzContextHolder.getContext().isAuthentication();
    }

    @Override
    public DcLoginSourceEnums getLoginSource() {
        AuthzContext authzContext = AuthzContextHolder.getContext();
        return authzContext.getLoginSource();
    }

    @Override
    public String getExternalId() {
        return AuthzContextHolder.getContext().getExternalId();
    }

	@Override
	public boolean isPrivateUser() {
		return AuthzContextHolder.getContext().isPrivateUser();
	}

	@Override
	public PfModel getPfModel() {
		QQPfModel pf = new QQPfModel() ;
		AuthzContext ctx = AuthzContextHolder.getContext() ;
		pf.setOpenId(ctx.getExternalId()) ;
		pf.setOpenKey(ctx.getOpenKey()) ;
		pf.setPfNick(ctx.getPfNick()) ;
		pf.setPf(ctx.getLoginSource()) ;
		return pf ;
	}

}
