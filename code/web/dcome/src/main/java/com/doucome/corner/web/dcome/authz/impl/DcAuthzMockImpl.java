package com.doucome.corner.web.dcome.authz.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.facade.DcUserModel;
import com.doucome.corner.biz.dcome.model.facade.PfModel;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.web.dcome.authz.DcAuthz;
import com.doucome.corner.web.dcome.context.AuthzContext;
import com.doucome.corner.web.dcome.context.AuthzContextHolder;

public class DcAuthzMockImpl implements DcAuthz {

	@Autowired
    private DcUserService dcUserService;
	
    @Override
    public boolean isLogin() {
        return true;
    }

    @Override
    public long getUserId() {
        return 10002L;
    }

    @Override
    public String getPfNick() {
        return "¿À±º¿À¬•";
    }

    @Override
    public DcLoginSourceEnums getLoginSource() {
        return null;
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

	@Override
	public DcUserModel getModel() {
		DcUserModel model = new DcUserModel() ;
		model.setNick(getPfNick()) ;
		model.setPfModel(getPfModel()) ;
		model.setUserId(getUserId()) ;
		return model;
	}

    /* (non-Javadoc)
     * @see com.doucome.corner.web.dcome.authz.DcAuthz#getUser()
     */
    @Override
    public DcUserDTO getUser() {
    	Long userId = getUserId() ;
    	return dcUserService.getUser(userId) ;
    }

    @Override
    public String getPromotype(String key) {
        AuthzContext ctx = AuthzContextHolder.getContext();
        Map<String,String> map = ctx.getPromotype();
        return map.get(key);
    }

    @Override
    public void addPromotype(String key, String value) {
        AuthzContext ctx = AuthzContextHolder.getContext();
        Map<String,String> map = ctx.getPromotype();
        map.put(key, value);
        ctx.setTouch(true);
    }

    @Override
    public String removePromotype(String key) {
        AuthzContext ctx = AuthzContextHolder.getContext();
        Map<String,String> map = ctx.getPromotype();
        String result = map.get(key);
        map.remove(key);
        ctx.setTouch(true);
        return result;
    }

    /* (non-Javadoc)
     * @see com.doucome.corner.web.dcome.authz.DcAuthz#getUbid()
     */
    @Override
    public String getUbid() {
        return "abcd";
    }
    
    


}
