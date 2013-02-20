package com.doucome.corner.biz.dal.condition.dcome;

import java.util.HashMap;
import java.util.Map;

/**
 * ≤È—Ø¿‡ƒø
 * @author langben 2012-7-24
 *
 */
public class DcCategorySearchCondition {

	private String categoryLevel ;
	
	private Long id ;
		
	private String name;
	
	public Map<String,Object> toMap(){
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("id", id);
		condition.put("categoryLevel", categoryLevel); 
		condition.put("name", name) ;
		return condition ;
	}

	public String getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(String categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
