package com.doucome.corner.web.dcome.context;

import java.util.HashMap;
import java.util.Map;

import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;

/**
 * 类AuthzContext.java的实现描述：dc认证信息上下文
 * 
 * @author ib 2012-7-29 上午12:10:57
 */
public class AuthzContext {

    private Map<AuthzContextModelEnum, Object> modelMap  = new HashMap<AuthzContextModelEnum, Object>();

    private Map<String, String>                promotype = new HashMap<String, String>();

    /**
     * 是否需要重写cookie
     */
    private boolean                            touch;

    private boolean                            authentication;

    private UserAuthzContext                   user      = new UserAuthzContext();

    private EnvContext                         env       = new EnvContext();

    public Object getModel(AuthzContextModelEnum key) {
        return modelMap.get(key);
    }

    public void setModel(AuthzContextModelEnum key, Object value) {
        modelMap.put(key, value);
    }

    public void clearModels() {
        modelMap.clear();
    }

    public boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication, boolean touch) {
        this.authentication = authentication;
        if (touch) {
            this.touch = touch;
        }
    }

    public boolean isTouch() {
        return touch;
    }

    public void setTouch(boolean touch) {
        this.touch = touch;
    }

    public boolean isPrivateUser() {
        return env.isPrivateUser();
    }

    public void setPrivateUser(boolean isPrivateUser) {
        env.setPrivateUser(isPrivateUser);
    }

    public long getUserId() {
        return user.getUserId();
    }

    public void setUserId(long userId) {
        user.setUserId(userId);
    }

    public String getPfNick() {
        return user.getPfNick();
    }

    public void setPfNick(String pfNick) {
        user.setPfNick(pfNick);
    }

    public String getDcTemp() {
        return user.getDcTemp();
    }

    public void setDcTemp(String dcTemp) {
        user.setDcTemp(dcTemp);
    }

    public DcLoginSourceEnums getLoginSource() {
        return user.getLoginSource();
    }

    public void setLoginSource(DcLoginSourceEnums loginSource) {
        user.setLoginSource(loginSource);
    }

    public String getExternalId() {
        return user.getExternalId();
    }

    public void setExternalId(String externalId) {
        user.setExternalId(externalId);
    }

    public String getOpenKey() {
        return user.getOpenKey();
    }

    public void setOpenKey(String openKey) {
        user.setOpenKey(openKey);
    }

    public String getUbid() {
        return env.getUbid();
    }

    public void setUbid(String ubid) {
        env.setUbid(ubid);
    }

    public Map<String, String> getPromotype() {
        return promotype;
    }

    public void setPromotype(Map<String, String> promotype) {
        this.promotype = promotype;
    }

    /**
     * 类AuthzContext.java的实现描述：环境信息
     * 
     * @author ib 2012-9-16 下午4:24:39
     */
    private class EnvContext {

        /**
         * 是否内部人员
         */
        private boolean isPrivateUser;
        /**
         * 标识浏览器唯一性的id
         */
        private String  ubid;

        public boolean isPrivateUser() {
            return isPrivateUser;
        }

        public void setPrivateUser(boolean isPrivateUser) {
            this.isPrivateUser = isPrivateUser;
        }

        public String getUbid() {
            return ubid;
        }

        public void setUbid(String ubid) {
            this.ubid = ubid;
        }

    }

    /**
     * 类AuthzContext.java的实现描述：登录状态上下文，会写入cookie
     * 
     * @author ib 2012-7-29 上午12:26:00
     */
    private class UserAuthzContext {

        /**
         * 内部用户id
         */
        private long               userId;
        /**
         * 外部用户id（对应qq的openid）
         */
        private String             externalId;
        /**
         * 用户当前登录的具体平台（pengyou、qzone等）
         */
        private DcLoginSourceEnums loginSource;
        /**
         * 用户登录当前平台的key，相当于密码的作用（对应qq的openkey）
         */
        private String             openKey;
        /**
         * 用户在当前登录平台的昵称
         */
        private String             pfNick;
        /**
         * 加密串(userId、externalId、openKey)
         */
        private String             dcTemp;

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getPfNick() {
            return pfNick;
        }

        public void setPfNick(String pfNick) {
            this.pfNick = pfNick;
        }

        public String getDcTemp() {
            return dcTemp;
        }

        public void setDcTemp(String dcTemp) {
            this.dcTemp = dcTemp;
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

        public DcLoginSourceEnums getLoginSource() {
            return loginSource;
        }

        public void setLoginSource(DcLoginSourceEnums loginSource) {
            this.loginSource = loginSource;
        }

    }

}
