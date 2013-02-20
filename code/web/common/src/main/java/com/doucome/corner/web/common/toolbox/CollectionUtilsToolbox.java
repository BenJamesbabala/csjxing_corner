package com.doucome.corner.web.common.toolbox;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.CollectionUtils;

public class CollectionUtilsToolbox extends CollectionUtils {

	public static boolean contains(Collection collection , Object element) {
		if(collection == null){
			return false ;
		}
		return collection.contains(element) ;
	}

	public static Object[] add(String[] arr , String element){
		return ArrayUtils.add(arr , element) ;
 	}
	
	public static List<String> add(List<String> list , String element){
		if(list == null){
			return null ;
		}
		list.add(element) ;
		return list ;
	}
}
