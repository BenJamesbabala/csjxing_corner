package com.doucome.corner.biz.dal.condition.dcome;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DcPromotionSearchCondition {

    /**
     * 系统时间
     */
    private Date   sysTime;

    private String status;

    private String promType;
    /**
     * 开奖时间，查询endtime少于openTime的
     */
    private Date   openTime;

    public Map<String, Object> toMap() {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("sysTime", sysTime);
        condition.put("openTime", openTime);
        condition.put("status", status);
        condition.put("promType", promType);
        return condition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPromType() {
        return promType;
    }

    public void setPromType(String promType) {
        this.promType = promType;
    }

    public Date getSysTime() {
        return sysTime;
    }

    public void setSysTime(Date sysTime) {
        this.sysTime = sysTime;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

}
