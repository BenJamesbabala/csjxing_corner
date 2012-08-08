package com.doucome.corner.biz.dal.model;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * ����ģ��
 * @author shenjia.caosj 2012-7-4
 *
 */
public class OrderAndSortModel {

	/**
	 * 
	 * @param orderStr �� name_asc , settle_desc 
	 * @param orderOptions  ����������ӶΣ� �� {"name","settle"}
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
	 * order �ֶ�
	 */
	private String order ;
	
	/**
	 * sort �ֶ�
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
