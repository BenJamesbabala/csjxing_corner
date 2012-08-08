package com.doucome.corner.biz.dcome.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.doucome.corner.biz.dcome.model.DcItemDTO;

public class DcItemUtils {

	public static void sortByLoves(List<DcItemDTO> itemList){
		Collections.sort(itemList, new Comparator<DcItemDTO>() {
			@Override
			public int compare(DcItemDTO o1, DcItemDTO o2) {
				int l1 = getItemLoves(o1) ;
				int l2 = getItemLoves(o2) ;
				if(l1 == l2){
					return 0 ;
				}
				return l1 > l2 ? -1 : 1 ;
				
			}
		}) ;
	}

	public static void sortByComments(List<DcItemDTO> itemList){
		Collections.sort(itemList, new Comparator<DcItemDTO>() {
			@Override
			public int compare(DcItemDTO o1, DcItemDTO o2) {
				int l1 = getItemComments(o1) ;
				int l2 = getItemComments(o2) ;
				if(l1 == l2){
					return 0 ;
				}
				return l1 > l2 ? -1 : 1 ;
				
			}
		}) ;
	}
	
	public static int getItemLoves(DcItemDTO item){
		if(item == null){
			return 0 ;
		}
		if(item.getLoves() == null){
			return 0 ;
		}
		return item.getLoves() ;
	}
	
	public static int getItemComments(DcItemDTO item){
		if(item == null){
			return 0 ;
		}
		if(item.getComments() == null){
			return 0 ;
		}
		return item.getComments() ;
	}
}
