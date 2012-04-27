package com.doucome.corner.biz.core.taobao.dto;

import java.math.BigDecimal;

import com.doucome.corner.biz.core.model.AbstractModel;
import com.doucome.corner.biz.core.utils.ReflectUtils;
import com.taobao.api.domain.FavoriteItem;

/**
 * �Ƽ��Ĺ�����Ʒ 
 * @author shenjia.caosj 2012-3-22
 *
 */
public class TaobaoFavoriteItemDTO extends AbstractModel {
	
	public TaobaoFavoriteItemDTO(FavoriteItem item){
		ReflectUtils.reflectTo(item, this) ;
	}

	/**
	 * �����۸�
	 */
	private BigDecimal promotionPrice  ;
	
	/**
	 * itemId
	 */
	private Long itemId  ;
	
	/**
	 * ��������
	 */
	private String itemName  ;
	
	/**
	 * �����۸�
	 */
	private BigDecimal itemPrice  ;
	
	/**
	 * ����ͼƬ
	 */
	private String itemPictrue ;
	
	/**
	 * url
	 */
	private String itemUrl  ;
	
	/**
	 * ��Ʒ���۴���
	 */
	private Long sellCount  ;

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemPictrue() {
		return itemPictrue;
	}

	public void setItemPictrue(String itemPictrue) {
		this.itemPictrue = itemPictrue;
	}

	public String getItemUrl() {
		return itemUrl;
	}

	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}

	public Long getSellCount() {
		return sellCount;
	}

	public void setSellCount(Long sellCount) {
		this.sellCount = sellCount;
	}
	
	
	
}
