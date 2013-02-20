package com.doucome.corner.biz.core.taobao.constant;

/**
 * top 参数 
 * @author langben 2012-3-21
 *
 */
public class TopParameterConst {

	/**
	 * 当前时间戳,整数
	 */
	public static final String ts = "ts" ;
	
	/**
	 * 应用输出是否在IFRAME中,编码=串
	 */
	public static final String iframe = "iframe" ;
	
	/**
	 * 即uid，用户不登录则不传
	 */
	public static final String visitor_id = "visitor_id" ;
	
	/**
	 * 当前用户昵称
	 */
	public static final String visitor_nick = "visitor_nick" ;
	
	public static final String nick = "nick" ;
	
	/**
	 * 当前用户角色
	 */
	public static final String visitor_role = "visitor_role" ;
	
	/**
	 * Session_key 失效时间,整数
	 */
	public static final String expires_in = "expires_in" ;
	
	/**
	 * refresh token 失效时间,整数
	 */
	public static final String re_expires_in = "re_expires_in" ;
	
	/**
	 * 子帐号ID
	 */
	public static final String sub_visitor_id = "sub_visitor_id" ;
	/**
	 * 子帐号nick
	 */
	public static final String sub_visitor_nick = "sub_visitor_nick" ;
}
