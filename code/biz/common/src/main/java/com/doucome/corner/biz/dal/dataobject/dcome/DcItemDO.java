package com.doucome.corner.biz.dal.dataobject.dcome;

import java.math.BigDecimal;
import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * ��ޢ��Ʒ
 * @author langben 2012-7-21
 *
 */
public class DcItemDO extends AbstractModel {

	private static final long serialVersionUID = -7817061856420847344L;

	private Long id ;
	
	/**
	 * �ⲿ��ƷID�����Ա���itemId
	 */
	private String nativeId ;
	
	/**
	 * �ⲿ��Ʒ��Ŀ�����Ա���categoryId
	 */
	private Long nativeCategoryId ;
	
	/**
	 * ��Ʒ����
	 */
	private String itemTitle ;
	
	/**
	 * ������Ϣ
	 */
	private String itemDesc ;
	
	/**
	 * �۸�
	 */
	private BigDecimal itemPrice ;
	
	/**
	 * �����۸�
	 */
	private BigDecimal itemPromPrice ;
	
	/**
	 * �Ƽ�����
	 */
	private String recommReason ;
	
	/**
	 * �Ƽ�������
	 */
	private String recommType ;
	
	/**
	 * ��Ʒ��Դ
	 */
	private String source ;
	
	/**
	 * ��ƷԭʼURL
	 */
	private String srcUrl ;
	
	/**
	 * ͼƬ������ַ��DcItemDTO.PicModel json��ʽ
	 */
	private String picUrls ;       
	
	/**
	 * ���URL�������ɵ��Ա���URL��
	 */
	private String clickUrl ;
	
	/**
	 * ϲ��
	 */
	private Integer loves ;
	
	/**
	 * �۳�����
	 */
	private Integer sells ;
	
	/**
	 * ��������
	 */
	private Integer comments ;
	
	/**
	 * ͶƱ����
	 */
	private Integer rates ;
	
	/**
	 * ������ʽ
	 */
	private String genWay ;
	/**
	 * �Ƿ����.
	 */
	private String postalEnable;
	
	/**
	 * �������ݵ�USER_ID ��PGCĬ��0
	 */
	private Long creatorUserId ;
	
	private String creatorNick;
	
	/**
	 * ��Ŀ
	 */
	private Long categoryId ;
	
	/**
	 * Ӷ��������Ա���ԭʼӶ�������
	 */
	private BigDecimal commissionRate ;
	
	/**
	 * ��������
	 */
	private Integer taokeSellCount ;
	
	/**
	 * ��ʾ˳��
	 */
	private Long displayOrder ;
	
	private Date gmtModified ;
	
	private Date gmtCreate ;
	
	/**
	 * ��Ʒ����ʱ��
	 */
	private Date gmtItemUpdate ;
	/**
	 * ��Ʒ״̬�����DcItemStatusEnum.
	 */
	private String status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNativeId() {
		return nativeId;
	}

	public void setNativeId(String nativeId) {
		this.nativeId = nativeId;
	}

	public Long getNativeCategoryId() {
		return nativeCategoryId;
	}

	public void setNativeCategoryId(Long nativeCategoryId) {
		this.nativeCategoryId = nativeCategoryId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}
	
	public BigDecimal getCommissionRate() {
		return commissionRate == null? new BigDecimal("0"): commissionRate;
	}

	public void setCommissionRate(BigDecimal commissionRate) {
		this.commissionRate = commissionRate;
	}

	public Integer getRates() {
		return rates == null? 0: rates;
	}

	public void setRates(Integer rates) {
		this.rates = rates;
	}

	public String getGenWay() {
		return genWay;
	}

	public void setGenWay(String genWay) {
		this.genWay = genWay;
	}

	public Long getCreatorUserId() {
		return creatorUserId;
	}

	public void setCreatorUserId(Long creatorUserId) {
		this.creatorUserId = creatorUserId;
	}

	public String getCreatorNick() {
		return creatorNick;
	}

	public void setCreatorNick(String creatorNick) {
		this.creatorNick = creatorNick;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSrcUrl() {
		return srcUrl;
	}

	public void setSrcUrl(String srcUrl) {
		this.srcUrl = srcUrl;
	}

	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}
	
	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}
	

	public Integer getLoves() {
		return loves;
	}

	public void setLoves(Integer loves) {
		this.loves = loves;
	}

	public Integer getSells() {
		return sells;
	}

	public void setSells(Integer sells) {
		this.sells = sells;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public Date getGmtItemUpdate() {
		return gmtItemUpdate;
	}

	public void setGmtItemUpdate(Date gmtItemUpdate) {
		this.gmtItemUpdate = gmtItemUpdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecommReason() {
		return recommReason;
	}

	public void setRecommReason(String recommReason) {
		this.recommReason = recommReason;
	}

	public BigDecimal getItemPromPrice() {
		return itemPromPrice;
	}

	public void setItemPromPrice(BigDecimal itemPromPrice) {
		this.itemPromPrice = itemPromPrice;
	}

	public String getRecommType() {
		return recommType;
	}

	public void setRecommType(String recommType) {
		this.recommType = recommType;
	}

	public String getPostalEnable() {
		return postalEnable;
	}

	public void setPostalEnable(String postalEnable) {
		this.postalEnable = postalEnable;
	}

	public Integer getTaokeSellCount() {
		return taokeSellCount;
	}

	public void setTaokeSellCount(Integer taokeSellCount) {
		this.taokeSellCount = taokeSellCount;
	}

	public Long getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Long displayOrder) {
		this.displayOrder = displayOrder;
	}

}
