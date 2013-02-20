package com.doucome.corner.web.namefate.action.context;

/**
 * ��AuthzContextHolder.java��ʵ��������TODO ��ʵ������
 * 
 * @author ib 2012-3-24 ����01:36:13
 */
public class NamefateAuthzContextHolder {

    private static ThreadLocal<NamefateAuthzContext> contextHolder = new ThreadLocal<NamefateAuthzContext>();

    public static NamefateAuthzContext getContext() {
        if (contextHolder.get() == null) {
            setContext(new NamefateAuthzContext());
        }
        return contextHolder.get();
    }

    public static void setContext(NamefateAuthzContext context) {
        contextHolder.set(context);
    }
}
