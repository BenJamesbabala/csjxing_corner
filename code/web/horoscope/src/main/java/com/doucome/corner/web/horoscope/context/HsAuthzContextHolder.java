package com.doucome.corner.web.horoscope.context;

/**
 * ��AuthzContextHolder.java��ʵ��������TODO ��ʵ������
 * 
 * @author ib 2012-3-24 ����01:36:13
 */
public class HsAuthzContextHolder {

    private static ThreadLocal<HsAuthzContext> contextHolder = new ThreadLocal<HsAuthzContext>();

    public static HsAuthzContext getContext() {
        if (contextHolder.get() == null) {
            setContext(new HsAuthzContext());
        }
        return contextHolder.get();
    }

    public static void setContext(HsAuthzContext context) {
        contextHolder.set(context);
    }
}
