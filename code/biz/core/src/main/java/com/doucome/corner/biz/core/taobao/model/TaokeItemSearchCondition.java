package com.doucome.corner.biz.core.taobao.model;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.taobao.enums.TaobaoSellerStartCreditEnums;
import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 淘宝客查询商品条件
 * @author langben 2012-2-27
 *
 */
public class TaokeItemSearchCondition extends AbstractModel {

	
	/**
	 * 商品标题中包含的关键字. 注意:查询时keyword,cid至少选择其中一个参数 
	 */
	private String keyword ;
	
	/**
	 * 商品所属分类id
	 */
	private Long cid ;
	
	/**
	 * 起始价格.传入价格参数时,需注意起始价格和最高价格必须一起传入,并且 start_price <= end_price
	 */
	private BigDecimal startPrice ;
	
	/**
	 * 最高价格
	 */
	private BigDecimal endPrice ;
	
	/**
	 * 是否自动发货
	 */
	private Boolean autoSend ;
	
	/**
	 * 商品所在地,杭州 		
	 */
	private String area ;
	
	/**
	 * 卖家信用: 1heart(一心) 2heart (两心) 3heart(三心) 4heart(四心) 5heart(五心) 1diamond(一钻) 2diamond(两钻) 3diamond(三钻) 4diamond(四钻) 5diamond(五钻) 1crown(一冠) 2crown(两冠) 3crown(三冠) 4crown(四冠) 5crown(五冠) 1goldencrown(一黄冠) 2goldencrown(二黄冠) 3goldencrown(三黄冠) 4goldencrown(四黄冠) 5goldencrown(五黄冠)
	 * @see {@link TaobaoSellerStartCreditEnums}
	 */
	private String startCredit ;
	
	/**
	 * 可选值和start_credit一样.start_credit的值一定要小于或等于end_credit的值。注：end_credit与start_credit一起使用才生效
	 * @see {@link TaobaoSellerStartCreditEnums}
	 */
	private String endCredit ;
	
	/**
	 * String 	可选 	price_desc 		默认排序:default price_desc(价格从高到低) price_asc(价格从低到高) credit_desc(信用等级从高到低) commissionRate_desc(佣金比率从高到低) commissionRate_asc(佣金比率从低到高) commissionNum_desc(成交量成高到低) commissionNum_asc(成交量从低到高) commissionVolume_desc(总支出佣金从高到低) commissionVolume_asc(总支出佣金从低到高) delistTime_desc(商品下架时间从高到低) delistTime_asc(商品下架时间从低到高)
	 */
	private String sort ;
	
	/**
	 * 是否查询消保卖家
	 */
	private Boolean guarantee ;
	
	/**
	 * 起始累计推广量佣金.注：返回的数据是30天内累计推广量，具该字段要与最高累计推广量一起使用才生效.,2345
	 */
	private Long startCommissionRate ;
	
	/**
	 * 最高佣金比率选项，如：2345表示23.45%。注：要起始佣金比率和最高佣金比率一起设置才有效。 ,2345 		
	 */
	private Long endCommissionRate ;
	
	/**
	 *  1000  ,起始累计推广量佣金.注：返回的数据是30天内累计推广量，具该字段要与最高累计推广量一起使用才生效
	 */
	private Long startCommissionNum ;
	
	/**
	 * 可选 	10000 		最高累计推广佣金选项
	 */
	private Long endCommissionNum ;
	
	/**
	 * 累计推广量范围开始
	 */
	private Long startTotalnum ;
	
	/**
	 * 累计推广量范围结束
	 */
	private Long endTotalnum ;
	
	/**
	 * 是否支持抵价券，设置为true表示该商品支持抵价券，设置为false或不设置表示不判断这个属性
	 */
	private  Boolean cashCoupon ;
	
	/**
	 * 是否支持VIP卡，设置为true表示该商品支持VIP卡，设置为false或不设置表示不判断这个属性
	 */
	private Boolean vipCard ;
	
	/**
	 * 是否海外商品，设置为true表示该商品是属于海外商品，默认为false
	 */
	private Boolean overseasItem ;
	
	/**
	 * 是否支持7天退换，设置为true表示该商品支持7天退换，设置为false或不设置表示不判断这个属性
	 */
	private Boolean sevendaysReturn ;
	
	/**
	 * 是否如实描述(即:先行赔付)商品，设置为true表示该商品是如实描述的商品，设置为false或不设置表示不判断这个属性
	 */
	private Boolean realDescribe ;
	
	/**
	 * 是否30天维修，设置为true表示该商品是支持30天维修，设置为false或不设置表示不判断这个属性
	 */
	private Boolean onemonthRepair ;
	
	/**
	 * 是否支持货到付款，设置为true表示该商品是支持货到付款，设置为false或不设置表示不判断这个属性
	 */
	private Boolean cashOndelivery ;
	
	/**
	 * 是否商城的商品，设置为true表示该商品是属于淘宝商城的商品，设置为false或不设置表示不判断这个属性
	 */
	private Boolean mallItem ;
	
	/**
	 * 标识一个应用是否来在无线或者手机应用,如果是true则会使用其他规则加密点击串.如果不穿值,则默认是false.
	 */
	private Boolean isMobile ;
	
	/**
	 * 
	 */
	private String outerCode ;

	public void complete() throws IllegalArgumentException {
		if(StringUtils.isBlank(keyword) && cid == null){
			throw new IllegalArgumentException("input keyword and cid both null .")  ;
		}
	}
	

	public String getOuterCode() {
		return outerCode;
	}

	public void setOuterCode(String outerCode) {
		this.outerCode = outerCode;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public BigDecimal getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}

	public BigDecimal getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}

	public Boolean getAutoSend() {
		return autoSend;
	}

	public void setAutoSend(Boolean autoSend) {
		this.autoSend = autoSend;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStartCredit() {
		return startCredit;
	}

	public void setStartCredit(String startCredit) {
		this.startCredit = startCredit;
	}

	public String getEndCredit() {
		return endCredit;
	}

	public void setEndCredit(String endCredit) {
		this.endCredit = endCredit;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Boolean getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(Boolean guarantee) {
		this.guarantee = guarantee;
	}

	
	public Long getStartCommissionRate() {
		return startCommissionRate;
	}

	public void setStartCommissionRate(Long startCommissionRate) {
		this.startCommissionRate = startCommissionRate;
	}

	public Long getEndCommissionRate() {
		return endCommissionRate;
	}

	public void setEndCommissionRate(Long endCommissionRate) {
		this.endCommissionRate = endCommissionRate;
	}

	public Long getStartCommissionNum() {
		return startCommissionNum;
	}

	public void setStartCommissionNum(Long startCommissionNum) {
		this.startCommissionNum = startCommissionNum;
	}

	public Long getEndCommissionNum() {
		return endCommissionNum;
	}

	public void setEndCommissionNum(Long endCommissionNum) {
		this.endCommissionNum = endCommissionNum;
	}

	public Long getStartTotalnum() {
		return startTotalnum;
	}

	public void setStartTotalnum(Long startTotalnum) {
		this.startTotalnum = startTotalnum;
	}

	public Long getEndTotalnum() {
		return endTotalnum;
	}

	public void setEndTotalnum(Long endTotalnum) {
		this.endTotalnum = endTotalnum;
	}

	public Boolean getCashCoupon() {
		return cashCoupon;
	}

	public void setCashCoupon(Boolean cashCoupon) {
		this.cashCoupon = cashCoupon;
	}

	public Boolean getVipCard() {
		return vipCard;
	}

	public void setVipCard(Boolean vipCard) {
		this.vipCard = vipCard;
	}

	public Boolean getOverseasItem() {
		return overseasItem;
	}

	public void setOverseasItem(Boolean overseasItem) {
		this.overseasItem = overseasItem;
	}

	public Boolean getSevendaysReturn() {
		return sevendaysReturn;
	}

	public void setSevendaysReturn(Boolean sevendaysReturn) {
		this.sevendaysReturn = sevendaysReturn;
	}

	public Boolean getRealDescribe() {
		return realDescribe;
	}

	public void setRealDescribe(Boolean realDescribe) {
		this.realDescribe = realDescribe;
	}

	public Boolean getOnemonthRepair() {
		return onemonthRepair;
	}

	public void setOnemonthRepair(Boolean onemonthRepair) {
		this.onemonthRepair = onemonthRepair;
	}

	public Boolean getCashOndelivery() {
		return cashOndelivery;
	}

	public void setCashOndelivery(Boolean cashOndelivery) {
		this.cashOndelivery = cashOndelivery;
	}

	public Boolean getMallItem() {
		return mallItem;
	}

	public void setMallItem(Boolean mallItem) {
		this.mallItem = mallItem;
	}

	public Boolean getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(Boolean isMobile) {
		this.isMobile = isMobile;
	}
		
}
