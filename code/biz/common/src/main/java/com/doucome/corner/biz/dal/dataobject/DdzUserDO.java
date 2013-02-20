package com.doucome.corner.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

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
     * loginId 点点折登陆账号，和淘宝账号一知
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
     * 支付宝登陆分配的登陆账号
     */
    private String nativeAlipayId;

    /**
     * 性别
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
    
    /**
     * 支付宝修改次数
     */
    private Integer modificationCount ;
    
    /**
     * 支付宝
     */
    private String alipayId ;

    private Date   gmtCreate;

    private Date   gmtModified;

    private Date   gmtLastLogin;

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

    public Date getGmtLastLogin() {
        return gmtLastLogin;
    }

    public void setGmtLastLogin(Date gmtLastLogin) {
        this.gmtLastLogin = gmtLastLogin;
    }

	public String getNativeAlipayId() {
		return nativeAlipayId;
	}

	public void setNativeAlipayId(String nativeAlipayId) {
		this.nativeAlipayId = nativeAlipayId;
	}

	public Integer getModificationCount() {
		return modificationCount;
	}

	public void setModificationCount(Integer modificationCount) {
		this.modificationCount = modificationCount;
	}

	public String getAlipayId() {
		return alipayId;
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}

}
