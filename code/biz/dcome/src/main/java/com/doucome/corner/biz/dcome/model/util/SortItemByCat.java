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
 * ����Cat������Ʒ
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
		
		//����catId������Ʒ
		for(DcItemDTO item : itemList){
			Long catId = item.getCategoryId() ;
			if(!catSortMappings.containsKey(catId)){
				catSortMappings.put(catId, new ArrayList<DcItemDTO>()) ;
			}
			List<DcItemDTO> itemListByCat = catSortMappings.get(catId) ;
			itemListByCat.add(item) ;
		}
		
		//��ÿ�������������()
		Set<Long> keySet = catSortMappings.keySet();
		for(Long catId : keySet){
			List<DcItemDTO> itemListByCat = catSortMappings.get(catId) ;
			DcItemUtils.sortByLoves(itemListByCat) ;//����ϲ������
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
