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
	 * 佣金比率。比如：0.01代表1%
	 */
	private BigDecimal commissionRate;

	/**
	 * 实际支付金额 ,比如：123.12
	 */
	private BigDecimal realPayFee;

	/**
	 * 应用授权码,比如：12
	 */
	private String appKey;

	/**
	 * 推广渠道 ,比如：12
	 */
	private String outerCode;

	/**
	 * 淘宝交易号,比如：12 <strong style="color:red">隐私</strong>
	 */
	private Long tradeId;

	/**
	 * 成交时间,比如：2000-01-01 00:00:00 <strong style="color:red">隐私</strong>
	 */
	private Date payTime;

	/**
	 * 成交价格,比如：12.15 <strong style="color:red">隐私</strong>
	 */
	private BigDecimal payPrice;

	/**
	 * 商品ID, 比如：12 <strong style="color:red">隐私</strong>
	 */
	private Long numIid;

	/**
	 * 商品标题,比如：好
	 * 
	 */
	private String itemTitle;

	/**
	 * 商品成交数量,比如：12 <strong style="color:red">隐私</strong>
	 */
	private Long itemNum;

	/**
	 * 所购买商品的类目ID,比如：12 <strong style="color:red">隐私</strong>
	 */
	private Long categoryId;

	/**
	 * 所购买商品的类目名称,12 <strong style="color:red">隐私</strong>
	 */
	private String categoryName;

	/**
	 * 店铺名称,很好 <strong style="color:red">隐私</strong>
	 */
	private String shopTitle;

	/**
	 * 用户获得的佣金,12.15 <strong style="color:red">隐私</strong>
	 */
	private BigDecimal commission;

	/**
	 * 商品字符串ID(注意：iid近期即将废弃，请用num_iid参数) <strong style="color:red">隐私</strong>
	 */
	private String iid;

	/**
	 * 卖家昵称,jayzhou
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
