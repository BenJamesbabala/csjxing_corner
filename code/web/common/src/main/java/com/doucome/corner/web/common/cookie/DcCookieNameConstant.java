package com.doucome.corner.web.common.cookie;

/**
 * 类DcCookieNameConstant.java的实现描述：豆蔻cookie名称定义
 * 
 * @author ib 2012-7-29 上午12:36:31
 */
public interface DcCookieNameConstant {

    /**
     * 豆蔻登录信息加密数据
     */
    public static final String DC_TEMP         = "__dc_t";
    /**
     * 用户当前登录的平台昵称
     */
    public static final String DC_PF_NICK      = "__dc_pf_nick";
    /**
     * 用户id
     */
    public static final String DC_USER_ID      = "__dc_user_id";
    /**
     * 本次登录的来源
     */
    public static final String DC_LOGIN_SOURCE = "__dc_login_source";
    /**
     * 非重要的浏览器临时属性，不加密，不与用户绑定
     */
    public static final String DC_PROMOTYPE    = "__dc_promotype";
    /**
     * 标识是否为内部用户
     */
    public static final String DC_IS_PRIVATE   = "__dc_private";
    /**
     * 标识浏览器的id
     */
    public static final String DC_UBID         = "__dc_u";
}
