package com.doucome.corner.web.bops.context;

import java.util.HashMap;
import java.util.Map;

/**
 * 类AuthzContext.java的实现描述：BOPS认证信息上下文
 * 
 * @author ib 2012-3-24 下午01:35:03
 */
public class AuthzContext {

    private Map<AuthzContextModelEnum, Object> modelMap = new HashMap<AuthzContextModelEnum, Object>();
    /**
     * 是否需要重写cookie
     */
    private boolean                            touch;

    private boolean                            authentication;

    private UserAuthzContext                   user     = new UserAuthzContext();

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

    public String getAdminId() {
        return user.getAdminId();
    }

    public void setAdminId(String adminId) {
        user.setAdminId(adminId);
    }

    private class UserAuthzContext {

        private String adminId;

        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }
    }

}
