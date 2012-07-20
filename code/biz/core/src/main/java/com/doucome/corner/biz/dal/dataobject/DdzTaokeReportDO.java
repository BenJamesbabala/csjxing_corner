package com.doucome.corner.biz.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.core.model.AbstractModel;

/**
 * �Կͱ���
 * 
 * @author shenjia.caosj 2012-3-6
 * 
 */
public class DdzTaokeReportDO extends AbstractModel {

	private int id;

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
	 * ͼƬ��ַ
	 */
	private String picUrl ;

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
	 * �Ƿ��Ѿ�����
	 */
	private String settleStatus;
	/**
	 * ������.
	 */
	private String settleResult;
	/**
	 * ����ʱ��
	 */
	private Date gmtSettled;

	private Date gmtCreate;

	private Date gmtModified;
	
	private Long settleId;

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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
}
