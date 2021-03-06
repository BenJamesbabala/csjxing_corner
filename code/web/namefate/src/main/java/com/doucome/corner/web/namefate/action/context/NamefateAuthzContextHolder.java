package com.doucome.corner.web.namefate.action.context;

/**
 * 类AuthzContextHolder.java的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-3-24 下午01:36:13
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
