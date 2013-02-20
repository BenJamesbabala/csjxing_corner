package com.doucome.corner.biz.dcome.model;

import java.util.Date;

import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.common.utils.ReflectUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCategoryDO;
import com.doucome.corner.biz.dal.model.AbstractModel;

public class DcCategoryDTO extends AbstractModel {

	private static final long serialVersionUID = 2865756204786531636L;

	public DcCategoryDTO(DcCategoryDO category){
		if(category == null){
			return ;
		}
		
		ReflectUtils.reflectTo(category, this) ;
	}
	
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
		return StringUtils.trim(name);
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

	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
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
