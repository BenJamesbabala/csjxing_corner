package com.doucome.corner.biz.core.taobao.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * ��Ʒ���Ż���Ϣ
 * 
 * @author langben 2012-3-28
 * 
 */
public class TaobaoPromotionInItemDTO extends AbstractModel {

	/**
	 * idValue��ֵ ,mjs2121_21332111-9842
	 */
	private String promotionId;

	/**
	 * �Ż�չʾ���� ,xy123
	 */
	private String name;

	/**
	 * �Ż����� , ��25Ԫ
	 */
	private String desc;

	/**
	 * �Żݿ�ʼʱ�� , 2000-01-01 00:00:00
	 */
	private Date startTime;

	/**
	 * �Żݽ���ʱ�� , 2000-01-01 00:00:00
	 */
	private Date endTime;

	/**
	 * �Ż��ۺ�۸�, 120
	 */
	private BigDecimal itemPromoPrice;

	/**
	 * ��Ҫ֧���������ʾΪ+xxx���磺+20�Խ��
	 */
	private String otherNeed;

	/**
	 * �磺��10�̳ǻ��� , ��10�̳ǻ��� ���Ͷ�����
	 */
	private String otherSend;

	/**
	 * sku�۸��Ӧ��id����֤����˳����ͬ��. 23221,23222,23229
	 */
	private String[] skuIdList;

	/**
	 * sku�۸��б� .50,90,85
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
