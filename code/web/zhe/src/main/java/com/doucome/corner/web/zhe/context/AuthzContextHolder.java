package com.doucome.corner.web.zhe.context;

/**
 * ��AuthzContextHolder.java��ʵ��������TODO ��ʵ������
 * 
 * @author ib 2012-3-24 ����01:36:13
 */
public class AuthzContextHolder {

    private static ThreadLocal<AuthzContext> contextHolder = new ThreadLocal<AuthzContext>();

    public static AuthzContext getContext() {
        if (contextHolder.get() == null) {
            setContext(new AuthzContext());
        }
        return contextHolder.get();
    }

    public static void setContext(AuthzContext context) {
        contextHolder.set(context);
    }
}
