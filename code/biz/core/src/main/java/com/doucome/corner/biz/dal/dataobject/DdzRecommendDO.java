package com.doucome.corner.biz.dal.dataobject;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.core.enums.RecommendTypeEnums;
import com.doucome.corner.biz.core.model.AbstractModel;

/**
 * �Ƽ�
 * @author shenjia.caosj 2012-5-22
 *
 */
public class DdzRecommendDO extends AbstractModel {

	private Integer id ;
	
	/**
	 * yyyy-MM-dd ����
	 */
	private String batchNo ;
	
	/**
	 * ��ƷID
	 */
	private Long numIid ;
	
	/**
	 * ��Ŀ
	 */
	private Long categoryId  ;
	
	/**
	 * Ӷ��
	 */
	private BigDecimal commission ;
	
	/**
	 * ����
	 */
	private BigDecimal commissionRate ;
	
	/**
	 * ��Ʒ�۸�
	 */
	private BigDecimal itemPrice ;
	
	/**
	 * ��Ʒ����
	 */
	private String itemTitle ;
	
	/**
	 * 
	 */
	private String itemPicUrl ;
	
	/**
	 * �Ƽ�����
	 * @see {@link RecommendTypeEnums}
	 */
	private String recommendType ;
	
	/**
	 * �Ƿ�չʾ
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
