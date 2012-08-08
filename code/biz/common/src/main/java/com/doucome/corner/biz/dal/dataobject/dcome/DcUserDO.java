package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * ��ޢ�û�
 * 
 * @author shenjia.caosj 2012-7-21
 */
public class DcUserDO extends AbstractModel {

    /**
     * ϵͳ���ɵ�UserId
     */
    private long   userId;
    /**
     * �ⲿƽ̨id
     */
    private String externalId;
    /**
     * ��Դƽ̨
     */
    private String externalPf;
    /**
     * �ǳ�
     */
    private String nick;

    /**
     * �Ա�
     */
    private String gender;

    /**
     * mobile
     */
    private String mobile;

    /**
     * email
     */
    private String email;

    /**
     * ����
     */
    private String md5Password;

    /**
     * ��Ա��Դ
     */
    private String source;

    private Date   gmtModified;

    private Date   gmtCreate;

    private Date   gmtLastLogin;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getExternalPf() {
        return externalPf;
    }

    public void setExternalPf(String externalPf) {
        this.externalPf = externalPf;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getMd5Password() {
        return md5Password;
    }

    public void setMd5Password(String md5Password) {
        this.md5Password = md5Password;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtLastLogin() {
        return gmtLastLogin;
    }

    public void setGmtLastLogin(Date gmtLastLogin) {
        this.gmtLastLogin = gmtLastLogin;
    }

}
