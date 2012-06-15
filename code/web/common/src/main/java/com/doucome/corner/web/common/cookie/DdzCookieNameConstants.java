package com.doucome.corner.web.common.cookie;

/**
 * 类CookieNameConstants.java的实现描述：Ddz cookie名称常量定义
 * 
 * @author ib 2012-3-24 下午01:18:50
 */
public final class DdzCookieNameConstants {

    /**
     * DDZ帐号id(支付宝id)
     */
    public static final String ALIPAY_ID      = "__ddz_y_id";
    /**
     * DDZ是否登陆(true：已登录;false：未登录)！！仅仅页面的s使用！！
     */
    public static final String IS_LOGON       = "__ddz_is_logon";
    /**
     * DDZ最后登录的网站帐号
     */
    public static final String LAST_LOGIN_ID  = "__ddz_last_login_id";

    /**
     * DDZ登录信息加密数据
     */
    public static final String DDZ_TEMP       = "__ddz_t";

    /**
     * 标识是否为内部用户
     */
    public static final String DDZ_IS_PRIVATE = "__ddz_private";
}
