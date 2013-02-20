package com.doucome.corner.biz.core.taobao.dto;

import java.util.Date;

import com.doucome.corner.biz.common.utils.ReflectUtils;
import com.taobao.api.domain.User;

public class TaobaoUserDTO {
	
	public TaobaoUserDTO(User user){
		ReflectUtils.reflectTo(user, this) ;
	}

	/**
	 * �û�����ID
	 */
	private Long userId ;

	/**
	 * �û��ǳ�
	 */
	private String nick ;
	/**
	 * �Ա𡣿�ѡֵ:m(��),f(Ů)
	 */
	private String sex ;
	/**
	 * 2000-01-01 00:00:00
	 */
	private Date birthday ;
	/**
	 * �û����͡���ѡֵ:B(B�̼�),C(C�̼�)
	 */
	private String type ;
	/**
	 * ���ް󶨡���ѡֵ:bind(��),notbind(δ��)
	 */
	private String alipayBind ;
	/**
	 * hz0799@163.com 	֧�����˻�
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
