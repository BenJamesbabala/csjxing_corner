package com.doucome.corner.biz.dal.dataobject;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;


/**
 * DdzRecommendCategoryDO
 * @author shenjia.caosj 2012-5-23
 *
 */
public class DdzRecommendTaskDO extends AbstractModel {

	private int id ;
	
	/**
	 * 类目IDs ， 多类目“，”隔开
	 */
	private String categoryIds ;
	
	private String recommendType ;
	
	private String lastTaskBatch ;
	
	private Date gmtCreate ;
	
	private Date gmtModified ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	public String getLastTaskBatch() {
		return lastTaskBatch;
	}

	public void setLastTaskBatch(String lastTaskBatch) {
		this.lastTaskBatch = lastTaskBatch;
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

	public String getRecommendType() {
		return recommendType;
	}

	public void setRecommendType(String recommendType) {
		this.recommendType = recommendType;
	}
	
	
}
