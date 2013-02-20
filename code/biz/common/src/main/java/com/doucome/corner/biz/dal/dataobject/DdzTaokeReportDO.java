package com.doucome.corner.biz.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.common.utils.ReflectUtils;
import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 淘客报表
 * 
 * @author langben 2012-3-6
 * 
 */
public class DdzTaokeReportDO extends AbstractModel {

	private Long id;

	/**
	 * 实际付款金额
	 */
	private BigDecimal realPayFee;

	/**
	 * outCode
	 */
	private String outCode;

	/**
	 * 交易号
	 */
	private Long tradeId;

	/**
	 * 支付时间
	 */
	private Date gmtPaid;

	/**
	 * 支付金额
	 */
	private BigDecimal payPrice;

	/**
	 * 商品ID
	 */
	private Long numIid;

	/**
	 * 标题
	 */
	private String itemTitle;

	/**
	 * 交易数量
	 */
	private Long itemNum;

	/**
	 * category
	 */
	private Long categoryId;

	/**
	 * 佣金
	 */
	private BigDecimal commission;

	/**
	 * 佣金比例
	 */
	private BigDecimal commissionRate;

	/**
	 * 用户获得的佣金
	 */
	private BigDecimal userCommission;

	/**
	 * 用户获得的佣金比率
	 */
	private BigDecimal userCommissionRate;
	
	/**
	 * 用户获得的集分宝比率
	 */
	private BigDecimal userJfbRate ;

	/**
	 * 卖家的淘宝账号
	 */
	private String sellerNick;

	/**
	 * 结算的支付宝账号
	 */
	private String settleAlipay;

	/**
	 * 结算的userId
	 */
	private String settleUid;

	/**
	 * 结算的淘宝ID
	 */
	private String settleTaobaoNick;

	/**
	 * 结算的费用
	 */
	private BigDecimal settleFee;
	
	/**
	 * 结算的集分宝
	 */
	private Integer settleJfb ;
	
	/**
	 * 结算方式（现金、集分宝）
	 */
	private String settleWay ;

	/**
	 * 是否已经结算
	 */
	private String settleStatus;
	/**
	 * 结算结果.
	 */
	private String settleResult;
	
	/**
	 * outCode类型
	 * @see OutCodeUtils
	 */
	private String outcodeType ;
	
	/**
	 * 插入时的批次号
	 */
	private String insertBatch ;
	
	/**
	 * 退款维权状态
	 */
	private String refundStatus ;
		
	/**
	 * Dcome itemId
	 */
	private Long dcItemId ;
	
	/**
	 * Dcome userId
	 */
	private Long dcUserId ;
	
	/**
	 * 结算时间
	 */
	private Date gmtSettled;

	private Date gmtCreate;

	private Date gmtModified;
	
	private Long settleId;
	
	public DdzTaokeReportDO deepCopy(){
		DdzTaokeReportDO reportClone = new DdzTaokeReportDO() ;
		ReflectUtils.reflectTo(this, reportClone) ;
		return reportClone ;
	}

	public String getSettleTaobaoNick() {
		return settleTaobaoNick;
	}

	public void setSettleTaobaoNick(String settleTaobaoNick) {
		this.settleTaobaoNick = settleTaobaoNick;
	}

	public BigDecimal getUserCommission() {
		return userCommission;
	}

	public void setUserCommission(BigDecimal userCommission) {
		this.userCommission = userCommission;
	}

	public BigDecimal getUserCommissionRate() {
		return userCommissionRate;
	}

	public void setUserCommissionRate(BigDecimal userCommissionRate) {
		this.userCommissionRate = userCommissionRate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public BigDecimal getRealPayFee() {
		return realPayFee;
	}

	public void setRealPayFee(BigDecimal realPayFee) {
		this.realPayFee = realPayFee;
	}

	public String getOutCode() {
		return outCode;
	}

	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Date getGmtPaid() {
		return gmtPaid;
	}

	public void setGmtPaid(Date gmtPaid) {
		this.gmtPaid = gmtPaid;
	}

	public BigDecimal getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public Long getItemNum() {
		return itemNum;
	}

	public void setItemNum(Long itemNum) {
		this.itemNum = itemNum;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public String getSettleAlipay() {
		return settleAlipay;
	}

	public void setSettleAlipay(String settleAlipay) {
		this.settleAlipay = settleAlipay;
	}

	public String getSettleUid() {
		return settleUid;
	}

	public void setSettleUid(String settleUid) {
		this.settleUid = settleUid;
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

	public Date getGmtSettled() {
		return gmtSettled;
	}

	public void setGmtSettled(Date gmtSettled) {
		this.gmtSettled = gmtSettled;
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

	public String getSettleResult() {
		return settleResult;
	}

	public void setSettleResult(String settleResult) {
		this.settleResult = settleResult;
	}

	public Long getSettleId() {
		return settleId;
	}

	public void setSettleId(Long settleId) {
		this.settleId = settleId;
	}

	public String getOutcodeType() {
		return outcodeType;
	}

	public void setOutcodeType(String outcodeType) {
		this.outcodeType = outcodeType;
	}

	public String getInsertBatch() {
		return insertBatch;
	}

	public void setInsertBatch(String insertBatch) {
		this.insertBatch = insertBatch;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Long getDcItemId() {
		return dcItemId;
	}

	public void setDcItemId(Long dcItemId) {
		this.dcItemId = dcItemId;
	}

	public Long getDcUserId() {
		return dcUserId;
	}

	public void setDcUserId(Long dcUserId) {
		this.dcUserId = dcUserId;
	}

	public Integer getSettleJfb() {
		return settleJfb;
	}

	public void setSettleJfb(Integer settleJfb) {
		this.settleJfb = settleJfb;
	}

	public String getSettleWay() {
		return settleWay;
	}

	public void setSettleWay(String settleWay) {
		this.settleWay = settleWay;
	}

	public BigDecimal getUserJfbRate() {
		return userJfbRate;
	}

	public void setUserJfbRate(BigDecimal userJfbRate) {
		this.userJfbRate = userJfbRate;
	}
	
}
