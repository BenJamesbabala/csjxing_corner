package com.doucome.corner.biz.zhe.model;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.enums.TaobaoPicEnums;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.core.utils.ReflectUtils;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.zhe.utils.TaobaoPicUtils;

public class TaobaokeReportFacade {

	public TaobaokeReportFacade(DdzTaokeReportDO report){
		if(report != null){
			ReflectUtils.reflectTo(report, this) ;
		}
	}
	
	private String tradeId ;
	
	/**
	 * 实际付款金额
	 */
	private BigDecimal realPayFee;
	
	/**
	 * 支付时间
	 */
	private Date gmtPaid;
	
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
	 * 卖家的淘宝账号
	 */
	private String sellerNick;

	/**
	 * 结算的支付宝账号
	 */
	private String settleAlipay;
	
	/**
	 * 支付金额
	 */
	private BigDecimal payPrice;
	
	/**
	 * 宝贝图片
	 */
	private String picUrl ;
	
	/**
	 * outCode
	 */
	private String outCode;
	
	
	public String getPrivateSettleAlipay(){
		return StringUtils.substring(this.settleAlipay, 0, 2)  + "****" ;
	}
	
	public String getPicUrl(String type){
    	return TaobaoPicUtils.findPic(this.picUrl, TaobaoPicEnums.toTaobaoPicEnums(type)) ;
    }
	
	public BigDecimal getOriginUserCommission(){
		
		return DecimalUtils.multiply(this.payPrice, this.userCommissionRate) ;
	}
	
	public String getFormatUserCommission(){
		return DecimalUtils.format(this.userCommission, "0.00") ;
	}
	
	public String getFormatUserCommissionRate(){
		return DecimalUtils.format(this.userCommissionRate.multiply(new BigDecimal(100)), "0.00") ;
	}
	
	
	/*******************************************************************************************/

	public BigDecimal getRealPayFee() {
		return realPayFee;
	}

	public void setRealPayFee(BigDecimal realPayFee) {
		this.realPayFee = realPayFee;
	}

	public Date getGmtPaid() {
		return gmtPaid;
	}

	public String getOutCode() {
		return outCode;
	}

	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}

	public void setGmtPaid(Date gmtPaid) {
		this.gmtPaid = gmtPaid;
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

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public BigDecimal getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}
	
	
}
