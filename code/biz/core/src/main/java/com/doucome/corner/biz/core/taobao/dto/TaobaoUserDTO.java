package com.doucome.corner.biz.core.taobao.dto;

import java.util.Date;

import com.doucome.corner.biz.common.utils.ReflectUtils;
import com.taobao.api.domain.User;

public class TaobaoUserDTO {
	
	public TaobaoUserDTO(User user){
		ReflectUtils.reflectTo(user, this) ;
	}

	/**
	 * 用户数字ID
	 */
	private Long userId ;

	/**
	 * 用户昵称
	 */
	private String nick ;
	/**
	 * 性别。可选值:m(男),f(女)
	 */
	private String sex ;
	/**
	 * 2000-01-01 00:00:00
	 */
	private Date birthday ;
	/**
	 * 用户类型。可选值:B(B商家),C(C商家)
	 */
	private String type ;
	/**
	 * 有无绑定。可选值:bind(绑定),notbind(未绑定)
	 */
	private String alipayBind ;
	/**
	 * hz0799@163.com 	支付宝账户
	 */
	private String alipayAccount ;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAlipayBind() {
		return alipayBind;
	}
	public void setAlipayBind(String alipayBind) {
		this.alipayBind = alipayBind;
	}
	public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	
	
	
	
}
