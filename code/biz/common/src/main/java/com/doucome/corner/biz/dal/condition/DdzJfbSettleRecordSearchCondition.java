package com.doucome.corner.biz.dal.condition;

import java.util.HashMap;
import java.util.Map;

public class DdzJfbSettleRecordSearchCondition {

	/**
	 * ���׺�
	 */
	private String tradeNo ;
	
	/**
	 * �������κ�
	 */
	private String settleBatchno ;
	
	/**
	 * �Ƿ��Ѿ����㣨�ϴ��˳ɹ��嵥��
	 */
	private String isSettled ;
	
	public Map<String,Object> toMap(){
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("tradeNo", tradeNo) ;
		condition.put("settleBatchno", settleBatchno) ;
		condition.put("isSettled", isSettled) ;
		return condition ;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getSettleBatchno() {
		return settleBatchno;
	}

	public void setSettleBatchno(String settleBatchno) {
		this.settleBatchno = settleBatchno;
	}

	public String getIsSettled() {
		return isSettled;
	}

	public void setIsSettled(String isSettled) {
		this.isSettled = isSettled;
	}
	
	
}
