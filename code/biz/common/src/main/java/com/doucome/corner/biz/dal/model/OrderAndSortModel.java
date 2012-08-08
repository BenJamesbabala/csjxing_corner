package com.doucome.corner.biz.dal.model;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 排序模型
 * @author shenjia.caosj 2012-7-4
 *
 */
public class OrderAndSortModel {

	/**
	 * 
	 * @param orderStr 如 name_asc , settle_desc 
	 * @param orderOptions  允许排序的子段， 如 {"name","settle"}
	 */
	public OrderAndSortModel(String orderStr , String[] orderOptions){
		
		if(StringUtils.isBlank(orderStr)){
			return ;
		}
		
		orderStr = StringUtils.lowerCase(orderStr) ;
		
		String[] orderAndSort = StringUtils.split(orderStr,"--") ;
		if(orderAndSort == null || orderAndSort.length != 2){
			return ;
		}
		
		String order = orderAndSort[0] ;
		
		String sort = orderAndSort[1] ;
		
		if(StringUtils.equals(sort,"desc") || StringUtils.equals(sort, "asc")){
			this.sort = sort ;
		}
		
		if(!ArrayUtils.isEmpty(orderOptions)){
			for(String oo : orderOptions){
				if(StringUtils.equals(oo, order)) {
					this.order = order ;
					break ;
				}
			}
		}
		
		if(StringUtils.isNotBlank(this.sort) && StringUtils.isNotBlank(this.order)){
			isFormat = true ;
		}
		
	}
	
	/**
	 * order 字段
	 */
	private String order ;
	
	/**
	 * sort 字段
	 */
	private String sort ;
	
	private boolean isFormat = false ;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public boolean isFormat() {
		return isFormat;
	}

	public void setFormat(boolean isFormat) {
		this.isFormat = isFormat;
	}
	
	
}
