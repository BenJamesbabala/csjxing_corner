package com.doucome.corner.biz.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.common.utils.ReflectUtils;
import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * �Կͱ���
 * 
 * @author langben 2012-3-6
 * 
 */
public class DdzTaokeReportDO extends AbstractModel {

	private Long id;

	/**
	 * ʵ�ʸ�����
	 */
	private BigDecimal realPayFee;

	/**
	 * outCode
	 */
	private String outCode;

	/**
	 * ���׺�
	 */
	private Long tradeId;

	/**
	 * ֧��ʱ��
	 */
	private Date gmtPaid;

	/**
	 * ֧�����
	 */
	private BigDecimal payPrice;

	/**
	 * ��ƷID
	 */
	private Long numIid;

	/**
	 * ����
	 */
	private String itemTitle;

	/**
	 * ��������
	 */
	private Long itemNum;

	/**
	 * category
	 */
	private Long categoryId;

	/**
	 * Ӷ��
	 */
	private BigDecimal commission;

	/**
	 * Ӷ�����
	 */
	private BigDecimal commissionRate;

	/**
	 * �û���õ�Ӷ��
	 */
	private BigDecimal userCommission;

	/**
	 * �û���õ�Ӷ�����
	 */
	private BigDecimal userCommissionRate;
	
	/**
	 * �û���õļ��ֱ�����
	 */
	private BigDecimal userJfbRate ;

	/**
	 * ���ҵ��Ա��˺�
	 */
	private String sellerNick;

	/**
	 * �����֧�����˺�
	 */
	private String settleAlipay;

	/**
	 * �����userId
	 */
	private String settleUid;

	/**
	 * ������Ա�ID
	 */
	private String settleTaobaoNick;

	/**
	 * ����ķ���
	 */
	private BigDecimal settleFee;
	
	/**
	 * ����ļ��ֱ�
	 */
	private Integer settleJfb ;
	
	/**
	 * ���㷽ʽ���ֽ𡢼��ֱ���
	 */
	private String settleWay ;

	/**
	 * �Ƿ��Ѿ�����
	 */
	private String settleStatus;
	/**
	 * ������.
	 */
	private String settleResult;
	
	/**
	 * outCode����
	 * @see OutCodeUtils
	 */
	private String outcodeType ;
	
	/**
	 * ����ʱ�����κ�
	 */
	private String insertBatch ;
	
	/**
	 * �˿�άȨ״̬
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
	 * ����ʱ��
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
