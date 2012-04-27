package com.doucome.corner.biz.core.taobao.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.core.model.AbstractModel;
import com.doucome.corner.biz.core.utils.ReflectUtils;
import com.taobao.api.domain.TaobaokeReportMember;

/**
 * 
 * @author shenjia.caosj 2012-2-26
 * 
 */
public class TaobaokeReportMemberDTO extends AbstractModel {
	
	public TaobaokeReportMemberDTO(TaobaokeReportMember reportMemb){
		ReflectUtils.reflectTo(reportMemb, this ) ;
	}

	/**
	 * Ӷ����ʡ����磺0.01����1%
	 */
	private BigDecimal commissionRate;

	/**
	 * ʵ��֧����� ,���磺123.12
	 */
	private BigDecimal realPayFee;

	/**
	 * Ӧ����Ȩ��,���磺12
	 */
	private String appKey;

	/**
	 * �ƹ����� ,���磺12
	 */
	private String outerCode;

	/**
	 * �Ա����׺�,���磺12 <strong style="color:red">��˽</strong>
	 */
	private Long tradeId;

	/**
	 * �ɽ�ʱ��,���磺2000-01-01 00:00:00 <strong style="color:red">��˽</strong>
	 */
	private Date payTime;

	/**
	 * �ɽ��۸�,���磺12.15 <strong style="color:red">��˽</strong>
	 */
	private BigDecimal payPrice;

	/**
	 * ��ƷID, ���磺12 <strong style="color:red">��˽</strong>
	 */
	private Long numIid;

	/**
	 * ��Ʒ����,���磺��
	 * 
	 */
	private String itemTitle;

	/**
	 * ��Ʒ�ɽ�����,���磺12 <strong style="color:red">��˽</strong>
	 */
	private Long itemNum;

	/**
	 * ��������Ʒ����ĿID,���磺12 <strong style="color:red">��˽</strong>
	 */
	private Long categoryId;

	/**
	 * ��������Ʒ����Ŀ����,12 <strong style="color:red">��˽</strong>
	 */
	private String categoryName;

	/**
	 * ��������,�ܺ� <strong style="color:red">��˽</strong>
	 */
	private String shopTitle;

	/**
	 * �û���õ�Ӷ��,12.15 <strong style="color:red">��˽</strong>
	 */
	private BigDecimal commission;

	/**
	 * ��Ʒ�ַ���ID(ע�⣺iid���ڼ�������������num_iid����) <strong style="color:red">��˽</strong>
	 */
	private String iid;

	/**
	 * �����ǳ�,jayzhou
	 */
	private String sellerNick;

	public BigDecimal getRealPayFee() {
		return realPayFee;
	}

	public void setRealPayFee(BigDecimal realPayFee) {
		this.realPayFee = realPayFee;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getOuterCode() {
		return outerCode;
	}

	public void setOuterCode(String outerCode) {
		this.outerCode = outerCode;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getShopTitle() {
		return shopTitle;
	}

	public void setShopTitle(String shopTitle) {
		this.shopTitle = shopTitle;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

}
