package com.doucome.corner.biz.dal.condition;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * ��������ѯ
 * 
 * @author shenjia.caosj 2012-5-6
 */
public class DdzTaokeReportSettleSearchCondition {

    /**
     * ����״̬ U|P|F|S
     */
    private String  settleStatus;

    /**
     * �ʼ�״̬ U:��Ҫ|F��ʧ��|S���ɹ�
     */
    private String  emailStatus;

    /**
     * ����UID
     */
    private String  settleUid;

    /**
     * ����֧������
     */
    private String  settleAlipay;

    /**
     * ����start
     */
    private Date    gmtSettledStart;

    /**
     * ����end
     */
    private Date    gmtSettledEnd;

    /**
     * ��������
     */
    private String  settleBatchno;

    /**
     * �������ڴ��
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
