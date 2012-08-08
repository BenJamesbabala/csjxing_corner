package com.doucome.corner.web.dcome.authz.impl;

import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.web.dcome.authz.DcAuthz;
import com.doucome.corner.web.dcome.authz.model.PfModel;

public class DcAuthzMockImpl implements DcAuthz {

    @Override
    public boolean isLogin() {
        return true;
    }

    @Override
    public long getUserId() {
        return 2L;
    }

    @Override
    public String getPfNick() {
        return "¿À±º¿À¬•";
    }

    @Override
    public DcLoginSourceEnums getLoginSource() {
        return DcLoginSourceEnums.Pengyou;
    }

    @Override
    public String getExternalId() {
        return "ABCD";
    }

	@Override
	public boolean isPrivateUser() {
		return false;
	}

	@Override
	public PfModel getPfModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
