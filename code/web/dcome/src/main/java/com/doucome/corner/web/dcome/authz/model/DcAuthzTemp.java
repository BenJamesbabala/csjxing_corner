package com.doucome.corner.web.dcome.authz.model;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.core.utils.MD5Util;

/**
 * 类DcAuthzTemp.java的实现描述：登录信息
 * 
 * @author ib 2012-7-29 上午12:42:04
 */
public class DcAuthzTemp {

    private long   userId;

    private String externalId;

    private String openKey;

    private long   loginTime;

    private String signature;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getOpenKey() {
        return openKey;
    }

    public void setOpenKey(String openKey) {
        this.openKey = openKey;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void generateSignature() {
        this.signature = createSignature();
    }

    private String createSignature() {
        StringBuilder builder = new StringBuilder();
        builder.append(userId);
        builder.append('|');
        builder.append(externalId);
        builder.append('|');
        builder.append(openKey);
        builder.append('|');
        builder.append(loginTime);
        return MD5Util.getMD5(builder.toString());
    }

    public boolean checkSignature() {
        return StringUtils.equals(signature, createSignature());
    }

    public String toString() {
        return JacksonHelper.toJSON(this);
    }
}
