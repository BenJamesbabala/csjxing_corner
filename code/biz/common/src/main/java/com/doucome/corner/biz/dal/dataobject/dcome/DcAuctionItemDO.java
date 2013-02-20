package com.doucome.corner.biz.dal.dataobject.dcome;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * ��Account.java��ʵ���������ʺ�MODEL
 * 
 * @author ze2200
 */
public class DcAuctionItemDO extends AbstractModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long itemId;
	private String itemTitle;
	private String itemPictures;
	private BigDecimal itemPrice;
	/**
	 * ���ļ۸�.
	 */
	private Integer baseIntegral;
	/**
	 * �Ӽ۵�λ.
	 */
	private Integer integralPer;
	/**
	 * ��ʼʱ��.
	 */
	private Date gmtStart;
	/**
	 * ����ʱ��
	 */
	private Date gmtEnd;
	/**
	 * ��ǰ����.
	 */
	private Integer bidIntegral;
	/**
	 * �����û�id
	 */
	private Long bidUserId;
	/**
	 * �����û��ǳ�.
	 */
	private String bidUserNick;
	
	/**
	 * ���״̬
	 */
	private String reviewStatus ;
	
	private Date gmtModified;
	
	private Date gmtCreate;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getItemId() {
		return itemId;
	}
	
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	public String getItemPictures() {
		return itemPictures;
	}

	public void setItemPictures(String itemPictures) {
		this.itemPictures = itemPictures;
	}

	public String getItemTitle() {
		return itemTitle;
	}
	
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	
	public BigDecimal getItemPrice() {
		return itemPrice;
	}
	
	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public Integer getBaseIntegral() {
		return baseIntegral;
	}
	
	public void setBaseIntegral(Integer baseIntegral) {
		this.baseIntegral = baseIntegral;
	}
	
	public Integer getIntegralPer() {
		return integralPer;
	}

	public void setIntegralPer(Integer integralPer) {
		this.integralPer = integralPer;
	}

	public Date getGmtStart() {
		return gmtStart;
	}
	
	public void setGmtStart(Date gmtStart) {
		this.gmtStart = gmtStart;
	}
	
	public Date getGmtEnd() {
		return gmtEnd;
	}
	
	public void setGmtEnd(Date gmtEnd) {
		this.gmtEnd = gmtEnd;
	}
	
	public Integer getBidIntegral() {
		return bidIntegral;
	}
	
	public void setBidIntegral(Integer bidIntegral) {
		this.bidIntegral = bidIntegral;
	}
	
	public Long getBidUserId() {
		return bidUserId;
	}
	
	public void setBidUserId(Long bidUserId) {
		this.bidUserId = bidUserId;
	}
	
	public String getBidUserNick() {
		return bidUserNick;
	}
	
	public void setBidUserNick(String bidUserNick) {
		this.bidUserNick = bidUserNick;
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

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
}
