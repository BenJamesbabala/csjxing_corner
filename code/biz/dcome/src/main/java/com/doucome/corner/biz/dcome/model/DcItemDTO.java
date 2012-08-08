package com.doucome.corner.biz.dcome.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.doucome.corner.biz.core.enums.PictureSizeEnums;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.PictureUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * @author shenjia.caosj 2012-7-21
 *
 */
public class DcItemDTO extends AbstractModel {

	private static final long serialVersionUID = 2714724008075358867L;

	private DcItemDO item ;

	private List<String> picUrlList;
	
	public DcItemDTO() {
		this.item = new DcItemDO();
		this.picUrlList = new ArrayList<String>();
	}
	
	public DcItemDTO(DcItemDO item){
		if(item == null){
			item = new DcItemDO() ;
		}
		this.item = item ;
		this.picUrlList = ArrayStringUtils.toList(item.getPicUrls()) ;
	}
	
	public String getPicUrl(int index , String type){
		if(CollectionUtils.isEmpty(this.picUrlList)){
			return null ;
		}
		if(index >= this.picUrlList.size()){
			return null ;
		}
		
		String picUrl = this.picUrlList.get(index);
		return PictureUtils.findPic(picUrl, PictureSizeEnums.toEnum(type)) ;
	}
	
	public DcItemDO getItem() {
		return this.item;
	}
	
	public List<String> getPicUrlList() {
		return picUrlList;
	}
	
	public void setPicUrlList(List<String> picUrlList) {
		this.picUrlList = picUrlList;
	}
	
	/**
	 * -----------------------------------------------------------------------
	 */
	
	public Long getId() {
		return item.getId();
	}
	
	public void setId(Long id) {
		this.item.setId(id);
	}

	public String getNativeId() {
		return item.getNativeId();
	}

	public void setNativeId(String nativeId) {
		this.item.setNativeId(nativeId);
	}
	
	public Long getNativeCategoryId() {
		return item.getNativeCategoryId();
	}

	public void setNativeCategoryId(Long nativeCategoryId) {
		item.setNativeCategoryId(nativeCategoryId);
	}
	
	public String getItemTitle() {
		return item.getItemTitle();
	}
	
	public void setItemTitle(String itemTitle) {
		this.item.setItemTitle(itemTitle);
	}

	public String getItemDesc() {
		return item.getItemDesc();
	}
	
	public void setItemDesc(String itemDesc) {
		item.setItemDesc(itemDesc);
	}

	public BigDecimal getItemPrice() {
		return item.getItemPrice();
	}
	
	public void setItemPrice(BigDecimal itemPrice) {
	    item.setItemPrice(itemPrice);
	}

	public String getSource() {
		return item.getSource();
	}
	
	public void setSource(String source) {
		item.setSource(source);
	}

	public String getSrcUrl() {
		return item.getSrcUrl();
	}
	
	public void setSrcUrl(String srcUrl) {
		item.setSrcUrl(srcUrl);
	}

	public String getClickUrl() {
		return item.getClickUrl();
	}
	
	public void setClickUrl(String clickUrl) {
		item.setClickUrl(clickUrl);
	}

	public String getPicUrls() {
		return item.getPicUrls();
	}
	
	public void setPicUrls(String picUrls) {
		item.setPicUrls(picUrls);
	}

	public Integer getLoves() {
		return item.getLoves();
	}
	
	public void setLoves(int loves) {
		item.setLoves(loves);
	}

	public Integer getSells() {
		return item.getSells();
	}
	
	public void setSells(int sells) {
		item.setSells(sells);
	}

	public Integer getComments() {
		return item.getComments();
	}
	
	public void setComments(int comments) {
		item.setComments(comments);
	}

	public Long getCategoryId() {
		return item.getCategoryId();
	}
	
	public void setCategoryId(long categoryId) {
		item.setCategoryId(categoryId);
	}

	public Date getGmtModified() {
		return item.getGmtModified();
	}
	
	public void setGmtModified(Date gmtModified) {
		item.setGmtModified(gmtModified);
	}


	public Date getGmtCreate() {
		return item.getGmtCreate();
	}

	public void setGmtCreate(Date gtmCreate) {
		item.setGmtCreate(gtmCreate);
	}
	
	public String getStatus() {
		return item.getStatus();
	}

	public void setStatus(String status) {
		item.setStatus(status);
	}
}
