package com.doucome.corner.biz.core.qq.constant;

/**
 * 类Constant.java的实现描述：调用QQ API相关的常量
 * 
 * @author ib 2012-7-28 下午08:11:10
 */
public interface QqApiConstant {

    public final static String SCRIPT_GET_INFO = "/v3/user/get_info";
    
    public final static String SCRIPT_WORD_FILTER = "/v3/csec/word_filter";
    
    public final static String SCRIPT_QZONE_FANS = "/v3/page/is_fans";
    
    public final static String SCRIPT_IS_WEIBO_FANS_OR_IDOL = "/v3/relation/is_fans_or_idol" ;
    
    public final static String PROTOCOL_HTTP   = "http";
    public final static String METHOD_POST     = "POST";
}
