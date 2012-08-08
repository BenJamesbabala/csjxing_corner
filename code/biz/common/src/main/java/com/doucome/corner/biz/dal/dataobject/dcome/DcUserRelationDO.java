package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 类DcUserRelationDO.java的实现描述：用户关联信息
 * 
 * @author ib 2012-7-28 下午10:55:31
 */
public class DcUserRelationDO extends AbstractModel {

    private long   id;
    private long   userId;
    private String pfId;
    private String pfName;
    private String pfNick;
    private Date   gmtModified;
    private Date   gmtCreate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPfId() {
        return pfId;
    }

    public void setPfId(String pfId) {
        this.pfId = pfId;
    }

    public String getPfName() {
        return pfName;
    }

    public void setPfName(String pfName) {
        this.pfName = pfName;
    }

    public String getPfNick() {
        return pfNick;
    }

    public void setPfNick(String pfNick) {
        this.pfNick = pfNick;
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

}
