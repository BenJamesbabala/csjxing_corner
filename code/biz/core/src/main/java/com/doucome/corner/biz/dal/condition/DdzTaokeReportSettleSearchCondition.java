package com.doucome.corner.biz.dal.condition;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.model.OrderAndSortModel;

/**
 * ��������ѯ
 * 
 * @author shenjia.caosj 2012-5-6
 */
public class DdzTaokeReportSettleSearchCondition {
	
	private static final String[]  orderOptions = new String[]{"settle_fee"} ;

    /**
     * ����״̬ U|P|F|S|C
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
     * ����ʱ�䣨start��
     */
    private Date gmtCreateStart ;
    
    /**
     * ����ʱ�䣨end��
     */
    private Date gmtCreateEnd ;

    /**
     * ��������
     */
    private String  settleBatchno;

    /**
     * �������ڴ��
     */
    private Integer settleInDays;
    
    /**
     * ����
     */
    private String order ;
        
    public Map<String,Object> toMap(){
    	Map<String,Object> condition = new HashMap<String,Object>() ;
		
		condition.put("gmtSettledStart", this.getGmtSettledStart()) ;
		condition.put("gmtSettledEnd", this.getGmtSettledEnd()) ;
		condition.put("settleStatus", this.getSettleStatus()) ;
		condition.put("settleAlipay", this.getSettleAlipay()) ;
		condition.put("settleUid", this.getSettleUid()) ;
		condition.put("emailStatus", this.getEmailStatus()) ;
		condition.put("settleBatchno", this.getSettleBatchno()) ;
		condition.put("settleInDays", this.getSettleInDays());
		condition.put("gmtCreateStart", this.getGmtCreateStart()) ;
		condition.put("gmtCreateEnd", this.getGmtCreateEnd()) ;
		
		OrderAndSortModel osm = new OrderAndSortModel(this.order, orderOptions) ;
		if(osm.isFormat()){
			condition.put("order", osm.getOrder()) ;
			condition.put("sort", osm.getSort()) ;
		}
		return condition ;
    }

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

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	
}
