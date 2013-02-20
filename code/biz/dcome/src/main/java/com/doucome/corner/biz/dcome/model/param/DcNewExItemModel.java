package com.doucome.corner.biz.dcome.model.param;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.common.model.ResultModel;

public class DcNewExItemModel {
	/**
	 * 价格.
	 */
	private BigDecimal price;
	/**
	 * 邮费.
	 */
	private BigDecimal postalFee;
	/**
	 * 可兑换数量.
	 */
	private int exCount;
	
	private String itemType;
	/**
	 * 是否是公用兑换.
	 */
	private boolean isPublic = true;
	/**
	 * 私用兑换用户id.
	 */
	private Long userId;

	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getPostalFee() {
		return postalFee;
	}
	
	public void setPostalFee(BigDecimal postalFee) {
		this.postalFee = postalFee;
	}
	
	public int getExCount() {
		return exCount;
	}
	
	public void setExCount(int exCount) {
		this.exCount = exCount;
	}
	
	public String getItemType() {
		if (StringUtils.isEmpty(itemType)) {
			return "N";
		}
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Long getUserId() {
		if (isPublic) {
			return DcItemUtils.PGC_ITEM_CREATOR;
		}
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public int getExIntegral() {
		if (price == null) {
			return 0;
		}
		BigDecimal temp = price;
		if (postalFee != null) {
			temp = temp.add(postalFee);
		}
		return temp.multiply(new BigDecimal("100")).intValue();
	}
	
	public ResultModel<Boolean> validate() {
		ResultModel<Boolean> result = new ResultModel<Boolean>();
		if (isPublic && IDUtils.isNotCorrect(userId)) {
			result.setFail(JsonModel.CODE_FAIL, "no.userid");
		} else {
			result.setSuccess(JsonModel.CODE_SUCCESS, true);
		}
		return result;
	}
}
