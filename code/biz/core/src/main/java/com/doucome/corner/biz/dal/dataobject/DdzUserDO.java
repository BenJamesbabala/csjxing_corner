package com.doucome.corner.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.biz.core.model.AbstractModel;

public class DdzUserDO extends AbstractModel {

	/**
	 * PK
	 */
	private int id;

	/**
	 * UserId
	 */
	private String uid;

	/**
	 * loginId µãµãÕÛµÇÂ½ÕËºÅ£¬ºÍÌÔ±¦ÕËºÅÒ»Öª
	 */
	private String loginId;

	/**
	 * mobile
	 */
	private String mobile;

	/**
	 * email
	 */
	private String email;

	/**
	 * taobaoµÇÂ½ÕËºÅ
	 */
	private String taobaoId;

	/**
	 * ÐÔ±ð
	 */
	private String gender;

	/**
	 * MD5 password
	 */
	private String md5Password;

	/**
	 * 
	 */
	private String userActive;

	private Date gmtCreate;

	private Date gmtModified;

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTaobaoId() {
		return taobaoId;
	}

	public void setTaobaoId(String taobaoId) {
		this.taobaoId = taobaoId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMd5Password() {
		return md5Password;
	}

	public void setMd5Password(String md5Password) {
		this.md5Password = md5Password;
	}

	public String getUserActive() {
		return userActive;
	}

	public void setUserActive(String userActive) {
		this.userActive = userActive;
	}

}
