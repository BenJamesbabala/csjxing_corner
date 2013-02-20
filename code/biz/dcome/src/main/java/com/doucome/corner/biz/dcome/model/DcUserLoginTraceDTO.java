package com.doucome.corner.biz.dcome.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.doucome.corner.biz.common.utils.IPUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserLoginTraceDO;
import com.doucome.corner.biz.dal.model.AbstractModel;

public class DcUserLoginTraceDTO extends AbstractModel {

    private DcUserLoginTraceDO trace;

    public DcUserLoginTraceDTO(DcUserLoginTraceDO trace){
        if (trace == null) {
            trace = new DcUserLoginTraceDO();
        }
        this.trace = trace;
    }

    public String getLoginIpStr() {
        return IPUtils.toAddrString(getLoginIp());
    }
    
    public String getGmtCreateFormat(){
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
    	return df.format(this.getGmtCreate()) ;
    }
    /**
     * -----------------------------
     */

    public Long getId() {
        return trace.getId();
    }

    public Long getUserId() {
        return trace.getUserId();
    }

    public Long getLoginIp() {
        return trace.getLoginIp();
    }

    public String getClientAgent() {
        return trace.getClientAgent();
    }

    public Date getGmtCreate() {
        return trace.getGmtCreate();
    }

    public String getUbid() {
        return trace.getUbid();
    }

    public void setUbid(String ubid) {
        trace.setUbid(ubid);
    }
    public String getNick() {
        return trace.getNick();
    }
    
    public Date getGmtRegister() {
        return trace.getGmtRegister();
    }
}
