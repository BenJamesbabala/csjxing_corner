package com.doucome.corner.biz.dal.dataobject.dcome;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * ͳ�Ƴ����� ÿ����Ŀ���� ��Ʒ
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
