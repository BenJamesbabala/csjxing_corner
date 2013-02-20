package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 参加活动的一些统计信息
 * @author langben 2012-11-8
 *
 */
public class DcPromotionSnapInfoDO extends AbstractModel {

	private Long id ;
	
	private Long promotionId ;
	
	private Long itemId ;
	
	/**
	 * 愿望个数
	 */
	private Integer wishCount ;
	
	private Date gmtModified ;
	
	private Date gmtCreate ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getWishCount() {
		return wishCount;
	}

	public void setWishCount(Integer wishCount) {
		this.wishCount = wishCount;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	
	
}
