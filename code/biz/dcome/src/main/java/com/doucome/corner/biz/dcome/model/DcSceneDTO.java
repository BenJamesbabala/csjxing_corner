package com.doucome.corner.biz.dcome.model;

import java.util.Date;
import java.util.List;

import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDO;
import com.doucome.corner.biz.dal.model.AbstractModel;

public class DcSceneDTO extends AbstractModel {
	
	private static final long serialVersionUID = 2484924690008258738L;

	private DcSceneDO scene ;
	
	private List<Long> itemIdList ;
	
	private List<Long> catIdList ;
	
	public DcSceneDTO(DcSceneDO scene){
		if(scene == null){
			scene = new DcSceneDO() ;
		}
		this.scene = scene ;
		this.catIdList = ArrayStringUtils.toLongList(scene.getCategoryIds()) ;
	}
				
	public List<Long> getCatIdList() {
		return catIdList;
	}

	public void setCatIdList(List<Long> catIdList) {
		this.catIdList = catIdList;
		this.scene.setCategoryIds(ArrayStringUtils.toString(catIdList)) ;
	}
	
	public List<Long> getItemIdList() {
		return itemIdList;
	}

	public void setItemIdList(List<Long> itemIdList) {
		this.itemIdList = itemIdList;
	}

	/**
	 * --------------------------------------------------------
	 */
	
	public Long getId() {
		return scene.getId();
	}


	public String getName() {
		return scene.getName();
	}


	public String getSceneDesc() {
		return scene.getSceneDesc();
	}

	public Long getSystemId() {
		return scene.getSystemId();
	}

	public String getBannerPicUrl() {
		return scene.getBannerPicUrl();
	}


	public String getActive() {
		return scene.getActive();
	}

	public String getCategoryIds(){
		return scene.getCategoryIds() ;
	}

	public Date getGmtCreate() {
		return scene.getGmtCreate();
	}

	public void setBannerPicUrl(String bannerPicUrl) {
		this.scene.setBannerPicUrl(bannerPicUrl);
	}
	
	public void setActive(String active) {
		this.scene.setActive(active);
	}

	public void setSystemId(Long systemId) {
		this.scene.setSystemId(systemId);
	}

	public void setCategoryIds(String categoryIds) {
		this.scene.setCategoryIds(categoryIds);
		this.catIdList = ArrayStringUtils.toLongList(categoryIds) ;
	}

	public void setSceneDesc(String sceneDesc) {
		this.scene.setSceneDesc(sceneDesc);
	}

	public void setId(Long id) {
		this.scene.setId(id);
	}

	public void setName(String name) {
		this.scene.setName(name);
	}

	public void setGmtModified(Date gmtModified) {
		this.scene.setGmtModified(gmtModified);
	}

	public void setGmtCreate(Date gmtCreate) {
		this.scene.setGmtCreate(gmtCreate);
	}

	public DcSceneDO getScene() {
		return scene;
	}
	
}
