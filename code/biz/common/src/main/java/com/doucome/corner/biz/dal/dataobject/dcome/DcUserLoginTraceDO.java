package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 用户登陆日志
 * 
 * @author langben 2012-9-12
 */
public class DcUserLoginTraceDO extends AbstractModel {

    private Long   id;

    /**
     * userId
     */
    private Long   userId;
    
    private String nick;

    /**
     * 登陆IP
     */
    private Long   loginIp;

    /**
     * client agent
     */
    private String clientAgent;

    private String ubid;

    private Date   gmtCreate;
    
    private Date   gmtRegister;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(Long loginIp) {
        this.loginIp = loginIp;
    }

    public String getClientAgent() {
        return clientAgent;
    }

    public void setClientAgent(String clientAgent) {
        if (clientAgent != null && clientAgent.length() > 256) {
            clientAgent = clientAgent.substring(0, 256);
        }
        this.clientAgent = clientAgent;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getUbid() {
        return ubid;
    }

    public void setUbid(String ubid) {
        this.ubid = ubid;
    }
    
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
    
    public Date getGmtRegister() {
        return gmtRegister;
    }
    
    public void setGmtRegister(Date gmtRegister) {
        this.gmtRegister = gmtRegister;
    }

}
