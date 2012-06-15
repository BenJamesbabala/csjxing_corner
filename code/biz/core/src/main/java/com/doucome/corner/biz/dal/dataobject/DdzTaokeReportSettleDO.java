package com.doucome.corner.biz.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.doucome.corner.biz.core.model.AbstractModel;

/**
 * @author ze2200
 */
public class DdzTaokeReportSettleDO extends AbstractModel {

    private Long       id;
    private String     settleId;
    private String     settleAlipay;
    private BigDecimal settleFee;
    private String     settleStatus;
    private String     settleBatchno;
    private String     alipayBatchno;
    private String     alipayStatus;
    private String     emailStatus;
    private Date       gmtCreate;
    private Date       gmtModified;
    private Date       gmtSettled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSettleId() {
        return settleId;
    }

    public void setSettleId(String settleId) {
        this.settleId = settleId;
    }

    public String getSettleAlipay() {
        return settleAlipay;
    }

    public void setSettleAlipay(String settleAlipay) {
        this.settleAlipay = settleAlipay;
    }

    public BigDecimal getSettleFee() {
        return settleFee;
    }

    public void setSettleFee(BigDecimal settleFee) {
        this.settleFee = settleFee;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getSettleBatchno() {
        return settleBatchno;
    }

    public void setSettleBatchno(String settleBatchno) {
        this.settleBatchno = settleBatchno;
    }

    public String getAlipayBatchno() {
        return alipayBatchno;
    }

    public void setAlipayBatchno(String alipayBatchno) {
        this.alipayBatchno = alipayBatchno;
    }

    public String getAlipayStatus() {
        return alipayStatus;
    }

    public void setAlipayStatus(String alipayStatus) {
        this.alipayStatus = alipayStatus;
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

    public Date getGmtSettled() {
        return gmtSettled;
    }

    public String getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }

    public void setGmtSettled(Date gmtSettled) {
        this.gmtSettled = gmtSettled;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
