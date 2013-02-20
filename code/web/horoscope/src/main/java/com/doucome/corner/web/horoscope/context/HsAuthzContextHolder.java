package com.doucome.corner.web.horoscope.context;

/**
 * 类AuthzContextHolder.java的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-3-24 下午01:36:13
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
