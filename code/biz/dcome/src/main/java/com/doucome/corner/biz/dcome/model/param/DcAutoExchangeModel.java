package com.doucome.corner.biz.dcome.model.param;

import java.math.BigDecimal;

import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.common.model.ResultModel;
import com.doucome.corner.web.common.utils.TaobaoUrlUtils;

/**
 * 
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcAutoExchangeModel extends AbstractModel {
	
	private String itemNativeId;
	
	private String itemSize;
	
	private String itemColor;
	
	private BigDecimal itemPrice;
	
	private BigDecimal postalFee;
	
	private Long exIntegral = -1l;
	
	private String memo;
	
	private Long userId;
	
	private String userNick;

	public void setItemUrl(String itemUrl) {
		itemNativeId = TaobaoUrlUtils.parseItemId(itemUrl);
	}
	
	public String getItemNativeId() {
		return this.itemNativeId;
	}
	
	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

	public String getItemColor() {
		return itemColor;
	}

	public void setItemColor(String itemColor) {
		this.itemColor = itemColor;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		if (itemPrice != null) {
			try {
				this.itemPrice = new BigDecimal(itemPrice);
				this.exIntegral = this.itemPrice.multiply(new BigDecimal("100")).longValue();
			} catch (Exception e) {
				
			}
		}
	}

	public BigDecimal getPostalFee() {
		return postalFee;
	}

	public void setPostalFee(String postalFee) {
		if (postalFee != null) {
			try {
				this.postalFee = new BigDecimal(postalFee);
				this.exIntegral = this.postalFee.add(itemPrice).multiply(new BigDecimal("100")).longValue();
			} catch (Exception e) {
				
			}
		}
	}
	
	public Long getExIntegral() {
		return exIntegral;
	}
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	
	public ResultModel<Boolean> validate() {
		ResultModel<Boolean> result = new ResultModel<Boolean>();
		result.setSuccess(JsonModel.CODE_SUCCESS, true);
		if (itemNativeId == null) {
			result.setFail(JsonModel.CODE_FAIL, "unknown.item");
		} else if (itemPrice == null) {
			result.setFail(JsonModel.CODE_FAIL, "itemprice.error");
		}
		return result;
	}
}
