package com.doucome.corner.biz.dal.condition.dcome;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.doucome.corner.biz.common.utils.IPUtils;

/**
 * ”√ªß
 * 
 * @author langben 2012-9-12
 */
public class DcUserLoginTraceCondition {

    public Map<String, Object> toMap() {

        if (IPUtils.isIPv4Address(loginIpStr)) {
            this.loginIp = IPUtils.toAddrLong(loginIpStr);
        }

        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("userId", userId);
        condition.put("ubid", ubid);
        condition.put("loginIp", loginIp);
        condition.put("gmtCreateStart", gmtCreateStart) ;
		condition.put("gmtCreateEnd", gmtCreateEnd) ;
		
        return condition;
    }
    

    /**
     * userId
     */
    private Long   userId;

    /**
     * loginIp
     */
    private Long   loginIp;

    private String ubid;

    private String loginIpStr;
    
    private Date gmtCreateStart ;
    
    private Date gmtCreateEnd ;

    public Date getGmtCreateStart() {
		return gmtCreateStart;
	}

	public void setGmtCreateStart(Date gmtCreateStart) {
		this.gmtCreateStart = gmtCreateStart;
	}

	public Date getGmtCreateEnd() {
		return gmtCreateEnd;
	}

	public void setGmtCreateEnd(Date gmtCreateEnd) {
		this.gmtCreateEnd = gmtCreateEnd;
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

    public String getLoginIpStr() {
        return loginIpStr;
    }

    public void setLoginIpStr(String loginIpStr) {
        this.loginIpStr = loginIpStr;
    }

    public String getUbid() {
        return ubid;
    }

    public void setUbid(String ubid) {
        this.ubid = ubid;
    }

}
