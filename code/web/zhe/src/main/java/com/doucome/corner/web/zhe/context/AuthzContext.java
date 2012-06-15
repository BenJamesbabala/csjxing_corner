package com.doucome.corner.web.zhe.context;

import java.util.HashMap;
import java.util.Map;

/**
 * ��AuthzContext.java��ʵ��������ddz��֤��Ϣ������
 * 
 * @author ib 2012-3-24 ����01:35:03
 */
public class AuthzContext {

    private Map<AuthzContextModelEnum, Object> modelMap = new HashMap<AuthzContextModelEnum, Object>();
    /**
     * �Ƿ���Ҫ��дcookie
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

    public String getAlipayId() {
        return user.getAlipayId();
    }

    public void setAlipayId(String alipayId) {
        user.setAlipayId(alipayId);
    }

    public String getUid() {
        return user.getUid();
    }

    public void setUid(String uid) {
        user.setUid(uid);
    }

    public String getLoginId() {
        return user.getLoginId();
    }

    public void setLoginId(String loginId) {
        user.setLoginId(loginId);
    }

    public String getDdzTemp() {
        return user.getDdzTemp();
    }

    public void setDdzTemp(String ddzTemp) {
        user.setDdzTemp(ddzTemp);
    }

    public boolean isPrivateUser() {
        return user.isPrivateUser();
    }

    public void setPrivateUser(boolean isPrivateUser) {
        user.setPrivateUser(isPrivateUser);
    }

    private class UserAuthzContext {

        /**
         * notTruts ֧�����˺ţ��ǵ�½���ȡ���Ĺ���֧������
         */
        private String  alipayId;
        /**
         * uid
         */
        private String  uid;

        private String  loginId;

        private String  ddzTemp;

        /**
         * �Ƿ��ڲ���Ա
         */
        private boolean isPrivateUser;

        public String getAlipayId() {
            return alipayId;
        }

        public void setAlipayId(String alipayId) {
            this.alipayId = alipayId;
        }

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getDdzTemp() {
            return ddzTemp;
        }

        public void setDdzTemp(String ddzTemp) {
            this.ddzTemp = ddzTemp;
        }

        public boolean isPrivateUser() {
            return isPrivateUser;
        }

        public void setPrivateUser(boolean isPrivateUser) {
            this.isPrivateUser = isPrivateUser;
        }

    }

}
