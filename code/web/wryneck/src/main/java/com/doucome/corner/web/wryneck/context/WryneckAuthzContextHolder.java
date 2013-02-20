package com.doucome.corner.web.wryneck.context;

/**
 * ��AuthzContextHolder.java��ʵ��������TODO ��ʵ������
 * 
 * @author ib 2012-3-24 ����01:36:13
 */
public class WryneckAuthzContextHolder {

    private static ThreadLocal<WryneckAuthzContext> contextHolder = new ThreadLocal<WryneckAuthzContext>();

    public static WryneckAuthzContext getContext() {
        if (contextHolder.get() == null) {
            setContext(new WryneckAuthzContext());
        }
        return contextHolder.get();
    }

    public static void setContext(WryneckAuthzContext context) {
        contextHolder.set(context);
    }
}
