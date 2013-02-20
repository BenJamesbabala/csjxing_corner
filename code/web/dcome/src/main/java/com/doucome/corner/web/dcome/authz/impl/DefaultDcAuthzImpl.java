package com.doucome.corner.web.dcome.authz.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.facade.DcUserModel;
import com.doucome.corner.biz.dcome.model.facade.PfModel;
import com.doucome.corner.biz.dcome.model.facade.QQPfModel;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.web.dcome.authz.DcAuthz;
import com.doucome.corner.web.dcome.context.AuthzContext;
import com.doucome.corner.web.dcome.context.AuthzContextHolder;
import com.doucome.corner.web.dcome.context.AuthzContextModelEnum;

/**
 * 类DefaultDcAuthzImpl.java的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-7-28 下午11:31:39
 */
public class DefaultDcAuthzImpl implements DcAuthz {

    @Autowired
    private DcUserService dcUserService;

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
        QQPfModel pf = new QQPfModel();
        AuthzContext ctx = AuthzContextHolder.getContext();
        pf.setOpenId(ctx.getExternalId());
        pf.setOpenKey(ctx.getOpenKey());
        pf.setPfNick(ctx.getPfNick());
        pf.setPf(ctx.getLoginSource());
        return pf;
    }

    @Override
    public DcUserModel getModel() {
        DcUserModel model = new DcUserModel();
        model.setNick(getPfNick());
        model.setPfModel(getPfModel());
        model.setUserId(getUserId());
        return model;
    }

    @Override
    public DcUserDTO getUser() {
        AuthzContext ctx = AuthzContextHolder.getContext();
        Object object = ctx.getModel(AuthzContextModelEnum.DC_USER_DTO);
        if (object != null && object instanceof DcUserDTO) {
            DcUserDTO user = (DcUserDTO) object;
            return user;
        }
        DcUserDTO user = dcUserService.getUser(getUserId());
        if (user != null) {
            ctx.setModel(AuthzContextModelEnum.DC_USER_DTO, user);
        }
        return user;
    }

    @Override
    public String getPromotype(String key) {
        AuthzContext ctx = AuthzContextHolder.getContext();
        Map<String, String> map = ctx.getPromotype();
        return map.get(key);
    }

    @Override
    public void addPromotype(String key, String value) {
        AuthzContext ctx = AuthzContextHolder.getContext();
        Map<String, String> map = ctx.getPromotype();
        map.put(key, value);
        ctx.setTouch(true);
    }

    @Override
    public String removePromotype(String key) {
        AuthzContext ctx = AuthzContextHolder.getContext();
        Map<String, String> map = ctx.getPromotype();
        String result = map.get(key);
        map.remove(key);
        ctx.setTouch(true);
        return result;
    }

    @Override
    public String getUbid() {
        AuthzContext ctx = AuthzContextHolder.getContext();
        return ctx.getUbid();
    }
}
