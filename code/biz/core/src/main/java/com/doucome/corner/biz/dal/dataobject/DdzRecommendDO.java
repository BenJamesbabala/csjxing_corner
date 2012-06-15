package com.doucome.corner.biz.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.core.enums.RecommendTypeEnums;
import com.doucome.corner.biz.core.model.AbstractModel;

/**
 * 推荐
 * @author shenjia.caosj 2012-5-22
 *
 */
public class DdzRecommendDO extends AbstractModel {

	private Integer id ;
	
	/**
	 * yyyy-MM-dd 批号
	 */
	private String batchNo ;
	
	/**
	 * 商品ID
	 */
	private Long numIid ;
	
	/**
	 * 类目
	 */
	private Long categoryId  ;
	
	/**
	 * 佣金
	 */
	private BigDecimal commission ;
	
	/**
	 * 比率
	 */
	private BigDecimal commissionRate ;
	
	/**
	 * 商品价格
	 */
	private BigDecimal itemPrice ;
	
	/**
	 * 商品名称
	 */
	private String itemTitle ;
	
	/**
	 * 
	 */
	private String itemPicUrl ;
	
	/**
	 * 推荐类型
	 * @see {@link RecommendTypeEnums}
	 */
	private String recommendType ;
	
	/**
	 * 是否展示
	 */
	private String isDisplay  ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getItemPicUrl() {
		return itemPicUrl;
	}

	public void setItemPicUrl(String itemPicUrl) {
		this.itemPicUrl = itemPicUrl;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public BigDecimal getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getRecommendType() {
		return recommendType;
	}

	public void setRecommendType(String recommendType) {
		this.recommendType = recommendType;
	}

	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	
}
