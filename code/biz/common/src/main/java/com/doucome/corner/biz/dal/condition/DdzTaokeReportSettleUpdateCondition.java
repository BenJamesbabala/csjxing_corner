package com.doucome.corner.biz.dal.condition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 报表结算查询
 * 
 * @author langben 2012-5-6
 */
public class DdzTaokeReportSettleUpdateCondition extends AbstractModel {
	
    /**
     * 结算状态 U|P|F|S|C
     */
    private String  equalSettleStatus;
    
    /**
     * 结算支付宝号
     */
    private String  settleAlipay;
    
    /**
     * 结算ID
     */
    private List<Long> settleIds ;
        
    public Map<String,Object> toMap(){
    	Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("equalSettleStatus", this.getEqualSettleStatus()) ;
		condition.put("settleAlipay", this.getSettleAlipay()) ;
		condition.put("settleIds", this.getSettleIds()) ;
		return condition ;
    }

	public List<Long> getSettleIds() {
		return settleIds;
	}

	public void setSettleIds(List<Long> settleIds) {
		this.settleIds = settleIds;
	}

	public String getEqualSettleStatus() {
		return equalSettleStatus;
	}

	public void setEqualSettleStatus(String equalSettleStatus) {
		this.equalSettleStatus = equalSettleStatus;
	}

	public String getSettleAlipay() {
		return settleAlipay;
	}

	public void setSettleAlipay(String settleAlipay) {
		this.settleAlipay = settleAlipay;
	}

}
