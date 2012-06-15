package com.doucome.corner.biz.dal.condition;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 报表结算查询
 * 
 * @author shenjia.caosj 2012-5-6
 */
public class DdzTaokeReportSettleSearchCondition {

    /**
     * 结算状态 U|P|F|S
     */
    private String  settleStatus;

    /**
     * 邮件状态 U:需要|F：失败|S：成功
     */
    private String  emailStatus;

    /**
     * 结算UID
     */
    private String  settleUid;

    /**
     * 结算支付宝号
     */
    private String  settleAlipay;

    /**
     * 结算start
     */
    private Date    gmtSettledStart;

    /**
     * 结算end
     */
    private Date    gmtSettledEnd;

    /**
     * 结算批号
     */
    private String  settleBatchno;

    /**
     * 多少天内打款
     */
    private Integer settleInDays;

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getSettleUid() {
        return settleUid;
    }

    public void setSettleUid(String settleUid) {
        this.settleUid = settleUid;
    }

    public String getSettleAlipay() {
        return StringUtils.trim(settleAlipay);
    }

    public void setSettleAlipay(String settleAlipay) {
        this.settleAlipay = settleAlipay;
    }

    public Date getGmtSettledStart() {
        return gmtSettledStart;
    }

    public void setGmtSettledStart(Date gmtSettledStart) {
        this.gmtSettledStart = gmtSettledStart;
    }

    public Date getGmtSettledEnd() {
        return gmtSettledEnd;
    }

    public void setGmtSettledEnd(Date gmtSettledEnd) {
        this.gmtSettledEnd = gmtSettledEnd;
    }

    public String getSettleBatchno() {
        return StringUtils.trim(settleBatchno);
    }

    public void setSettleBatchno(String settleBatchno) {
        this.settleBatchno = settleBatchno;
    }

    public String getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }

    public Integer getSettleInDays() {
        return settleInDays;
    }

    public void setSettleInDays(Integer settleInDays) {
        this.settleInDays = settleInDays;
    }

}
