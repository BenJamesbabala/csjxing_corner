package com.doucome.corner.biz.dal.dataobject.dcome;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 统计场景下 每个类目多少 商品
 * @author langben 2012-7-28
 *
 */
public class DcCatCountDO extends AbstractModel {

	private Long categoryId ;
	
	private Integer itemCount ;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	
	
}
