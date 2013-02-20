package com.doucome.corner.biz.dal.condition;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.dal.model.AbstractModel;

@SuppressWarnings("serial")
public class TaokeReportSearchCondition extends AbstractModel {

	private Date gmtPaidStart;

	private Date gmtPaidEnd;

	private String settleStatus;

	private String[] settleStatusList;

	private String settleUid;

	private String settleTaobaoNick;

	private String settleAlipay;

	private Date gmtSettledStart;

	private Date gmtSettledEnd;

	private Date gmtCreateStart;

	private Date gmtCreateEnd;

	private String payBatchno;

	private Long settleId;

	private String tradeId;
	
	private String keywords ;
	
	private String sellerNick ;
	
	private String insertBatch ;
	
	private String outcodeType ;
	
	private String[] outcodeTypeList ;
	
	private Boolean requireSettleId ;
	
	private Long numIid ;
	
	private Long dcUserId ;
	
	private Long dcItemId ;
	
	private String refundStatus ;
	
	public Map<String, Object> toMap() {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("gmtPaidStart", gmtPaidStart);
		condition.put("gmtPaidEnd", gmtPaidEnd);
		condition.put("settleStatus", settleStatus);
		condition.put("settleStatusList", settleStatusList);
		condition.put("settleUid", settleUid);
		condition.put("settleTaobaoNick", settleTaobaoNick);
		condition.put("settleAlipay", StringUtils.trim(settleAlipay));
		condition.put("gmtSettledStart", gmtSettledStart);
		condition.put("gmtSettledEnd", gmtSettledEnd);
		condition.put("gmtCreateStart", gmtCreateStart);
		condition.put("gmtCreateEnd", gmtCreateEnd);
		condition.put("payBatchno", payBatchno);
		condition.put("settleId", settleId);
		condition.put("tradeId", StringUtils.trim(tradeId)) ;
		condition.put("keywords", keywords) ;
		condition.put("sellerNick", sellerNick) ;
		condition.put("insertBatch", insertBatch) ;
		condition.put("outcodeType", outcodeType) ;
		condition.put("outcodeTypeList", outcodeTypeList) ;
		condition.put("requireSettleId", requireSettleId) ;
		condition.put("numIid", numIid) ;
		condition.put("dcUserId", dcUserId) ;
		condition.put("dcItemId", dcItemId) ;
		condition.put("refundStatus", refundStatus) ;
		
		return condition;
	}

	public Long getNumIid() {
		return numIid;
	}



	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}



	public Boolean getRequireSettleId() {
		return requireSettleId;
	}

	public void setRequireSettleId(Boolean requireSettleId) {
		this.requireSettleId = requireSettleId;
	}

	public Long getSettleId() {
		return settleId;
	}

	public void setSettleId(Long settleId) {
		this.settleId = settleId;
	}

	public String getPayBatchno() {
		return payBatchno;
	}

	public void setPayBatchno(String payBatchno) {
		this.payBatchno = StringUtils.trim(payBatchno);
	}

	public String getSettleUid() {
		return settleUid;
	}

	public void setSettleUid(String settleUid) {
		this.settleUid = StringUtils.trim(settleUid);
	}

	public String getSettleAlipay() {
		return settleAlipay;
	}

	public void setSettleAlipay(String settleAlipay) {
		this.settleAlipay = StringUtils.trim(settleAlipay);
	}

	public Date getGmtPaidStart() {
		return gmtPaidStart;
	}

	public void setGmtPaidStart(Date gmtPaidStart) {
		this.gmtPaidStart = gmtPaidStart;
	}

	public Date getGmtPaidEnd() {
		return gmtPaidEnd;
	}

	public void setGmtPaidEnd(Date gmtPaidEnd) {
		this.gmtPaidEnd = gmtPaidEnd;
	}

	public String[] getSettleStatusList() {
		return settleStatusList;
	}

	public void setSettleStatusList(String[] settleStatusList) {
		this.settleStatusList = settleStatusList;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = StringUtils.trim(settleStatus);
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

	public String getSettleTaobaoNick() {
		return settleTaobaoNick;
	}

	public void setSettleTaobaoNick(String settleTaobaoNick) {
		this.settleTaobaoNick = StringUtils.trim(settleTaobaoNick);
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

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public String getInsertBatch() {
		return insertBatch;
	}

	public void setInsertBatch(String insertBatch) {
		this.insertBatch = insertBatch;
	}

	public String getOutcodeType() {
		return outcodeType;
	}

	public void setOutcodeType(String outcodeType) {
		this.outcodeType = outcodeType;
	}

	public Long getDcUserId() {
		return dcUserId;
	}

	public void setDcUserId(Long dcUserId) {
		this.dcUserId = dcUserId;
	}

	public Long getDcItemId() {
		return dcItemId;
	}

	public void setDcItemId(Long dcItemId) {
		this.dcItemId = dcItemId;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String[] getOutcodeTypeList() {
		return outcodeTypeList;
	}

	public void setOutcodeTypeList(String[] outcodeTypeList) {
		this.outcodeTypeList = outcodeTypeList;
	}

	
}
