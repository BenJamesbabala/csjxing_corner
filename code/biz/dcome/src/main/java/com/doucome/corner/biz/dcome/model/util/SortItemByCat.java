package com.doucome.corner.biz.dcome.model.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;

/**
 * 根据Cat归类商品
 * @author langben 2012-8-9
 *
 */
public class SortItemByCat {

	private List<DcItemDTO> itemList ;
	
	private Map<Long,List<DcItemDTO>> catSortMappings = new HashMap<Long,List<DcItemDTO>>() ;
	
	
	public SortItemByCat(List<DcItemDTO> itemList){
		this.itemList = itemList ;
		if(CollectionUtils.isEmpty(itemList)){
			return ;
		}	
		
		//根据catId分类商品
		for(DcItemDTO item : itemList){
			Long catId = item.getCategoryId() ;
			if(!catSortMappings.containsKey(catId)){
				catSortMappings.put(catId, new ArrayList<DcItemDTO>()) ;
			}
			List<DcItemDTO> itemListByCat = catSortMappings.get(catId) ;
			itemListByCat.add(item) ;
		}
		
		//对每个排序进行排序()
		Set<Long> keySet = catSortMappings.keySet();
		for(Long catId : keySet){
			List<DcItemDTO> itemListByCat = catSortMappings.get(catId) ;
			DcItemUtils.sortByLoves(itemListByCat) ;//按照喜欢排序
		}
	}


	public List<DcItemDTO> getItemList() {
		return itemList;
	}


	public Map<Long, List<DcItemDTO>> getCatSortMappings() {
		return catSortMappings;
	}
	
	public List<DcItemDTO> getItemListByCat(Long catId){
		return catSortMappings.get(catId) ;
	}
}
