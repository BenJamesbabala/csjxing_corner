package com.doucome.corner.biz.core.taobao.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 单品级优惠信息
 * 
 * @author langben 2012-3-28
 * 
 */
public class TaobaoPromotionInItemDTO extends AbstractModel {

	/**
	 * idValue的值 ,mjs2121_21332111-9842
	 */
	private String promotionId;

	/**
	 * 优惠展示名称 ,xy123
	 */
	private String name;

	/**
	 * 优惠描述 , 减25元
	 */
	private String desc;

	/**
	 * 优惠开始时间 , 2000-01-01 00:00:00
	 */
	private Date startTime;

	/**
	 * 优惠结束时间 , 2000-01-01 00:00:00
	 */
	private Date endTime;

	/**
	 * 优惠折后价格, 120
	 */
	private BigDecimal itemPromoPrice;

	/**
	 * 需要支付附加物，显示为+xxx。如：+20淘金币
	 */
	private String otherNeed;

	/**
	 * 如：送10商城积分 , 送10商城积分 赠送东西。
	 */
	private String otherSend;

	/**
	 * sku价格对应的id（保证二者顺序相同）. 23221,23222,23229
	 */
	private String[] skuIdList;

	/**
	 * sku价格列表 .50,90,85
	 */
	private BigDecimal[] skuPriceList;

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getItemPromoPrice() {
		return itemPromoPrice;
	}

	public void setItemPromoPrice(BigDecimal itemPromoPrice) {
		this.itemPromoPrice = itemPromoPrice;
	}

	public String getOtherNeed() {
		return otherNeed;
	}

	public void setOtherNeed(String otherNeed) {
		this.otherNeed = otherNeed;
	}

	public String getOtherSend() {
		return otherSend;
	}

	public void setOtherSend(String otherSend) {
		this.otherSend = otherSend;
	}

	public String[] getSkuIdList() {
		return skuIdList;
	}

	public void setSkuIdList(String[] skuIdList) {
		this.skuIdList = skuIdList;
	}

	public BigDecimal[] getSkuPriceList() {
		return skuPriceList;
	}

	public void setSkuPriceList(BigDecimal[] skuPriceList) {
		this.skuPriceList = skuPriceList;
	}

}
