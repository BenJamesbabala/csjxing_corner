package com.doucome.corner.web.dcome.context;

import java.util.HashMap;
import java.util.Map;

import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;

/**
 * ��AuthzContext.java��ʵ��������dc��֤��Ϣ������
 * 
 * @author ib 2012-7-29 ����12:10:57
 */
public class AuthzContext {

    private Map<AuthzContextModelEnum, Object> modelMap  = new HashMap<AuthzContextModelEnum, Object>();

    private Map<String, String>                promotype = new HashMap<String, String>();

    /**
     * �Ƿ���Ҫ��дcookie
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
     * ��AuthzContext.java��ʵ��������������Ϣ
     * 
     * @author ib 2012-9-16 ����4:24:39
     */
    private class EnvContext {

        /**
         * �Ƿ��ڲ���Ա
         */
        private boolean isPrivateUser;
        /**
         * ��ʶ�����Ψһ�Ե�id
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
     * ��AuthzContext.java��ʵ����������¼״̬�����ģ���д��cookie
     * 
     * @author ib 2012-7-29 ����12:26:00
     */
    private class UserAuthzContext {

        /**
         * �ڲ��û�id
         */
        private long               userId;
        /**
         * �ⲿ�û�id����Ӧqq��openid��
         */
        private String             externalId;
        /**
         * �û���ǰ��¼�ľ���ƽ̨��pengyou��qzone�ȣ�
         */
        private DcLoginSourceEnums loginSource;
        /**
         * �û���¼��ǰƽ̨��key���൱����������ã���Ӧqq��openkey��
         */
        private String             openKey;
        /**
         * �û��ڵ�ǰ��¼ƽ̨���ǳ�
         */
        private String             pfNick;
        /**
         * ���ܴ�(userId��externalId��openKey)
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
