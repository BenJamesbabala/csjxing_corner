package com.doucome.corner.biz.dal.condition;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.model.OrderAndSortModel;

/**
 * 报表结算查询
 * 
 * @author shenjia.caosj 2012-5-6
 */
public class DdzTaokeReportSettleSearchCondition {
	
	private static final String[]  orderOptions = new String[]{"settle_fee"} ;

    /**
     * 结算状态 U|P|F|S|C
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
     * 创建时间（start）
     */
    private Date gmtCreateStart ;
    
    /**
     * 创建时间（end）
     */
    private Date gmtCreateEnd ;

    /**
     * 结算批号
     */
    private String  settleBatchno;

    /**
     * 多少天内打款
     */
    private Integer settleInDays;
    
    /**
     * 排序
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
