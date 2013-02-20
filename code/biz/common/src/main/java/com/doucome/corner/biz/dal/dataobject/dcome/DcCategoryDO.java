package com.doucome.corner.biz.dal.dataobject.dcome;

import java.util.Date;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 类目
 * @author langben 2012-7-22
 *
 */
public class DcCategoryDO extends AbstractModel {

	private Long id ;
	
	/**
	 * 类目名称
	 */
	private String name ;
	
	/**
	 * 类目层级(0 顶级 大类， 1二级类目）
	 */
	private String categoryLevel ;
	
	/**
	 * 父级类目ID
	 */
	private Long parentCategoryId ;
	
	private Date gmtModified ;
	
	private Date gmtCreate ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(String categoryLevel) {
		this.categoryLevel = categoryLevel;
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

	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	
	
}
