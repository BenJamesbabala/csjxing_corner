package com.doucome.corner.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 类DdzConfigDO.java的实现描述：ddz配置
 * 
 * @author ib 2012-6-23 上午01:36:00
 */
public class DdzConfigDO extends AbstractModel {

    private Integer id;
    /**
     * 模块
     */
    private String  module;
    /**
     * key
     */
    private String  keyName;

    /**
     * 值
     */
    private String  value;

    /**
	 * 
	 */
    private Date    gmtCreate;

    private Date    gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

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

}
