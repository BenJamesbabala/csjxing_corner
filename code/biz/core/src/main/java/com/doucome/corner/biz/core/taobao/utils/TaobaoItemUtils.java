package com.doucome.corner.biz.core.taobao.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.taobao.api.domain.ItemImg;

/**
 * 
 * @author langben 2012-11-1
 *
 */
public class TaobaoItemUtils {

	/**
	 * 是否包邮
	 * @param item
	 * @return
	 */
	public static boolean isFreePostage(TaobaoItemDTO item){
		
		if(item == null){
			return false ;
		}
		
		//卖家承担运费
		if(StringUtils.equals(item.getFreightPayer() , "seller")){
			return true ;
		}
		
		//快递运费是0
//		if(item.getExpressFee() != null && DecimalUtils.lessEqualThan(item.getExpressFee(),DecimalConstant.ZERO)){
//			return true ;
//		}
		
		return false ;
	}
	
	public static Map<Long,TaobaoItemDTO> convertTaobaoItemToMap(List<TaobaoItemDTO> taobaoList){
		Map<Long,TaobaoItemDTO> map = new HashMap<Long,TaobaoItemDTO>() ;
		if(CollectionUtils.isEmpty(taobaoList)){
			return map ;
		}
		
		for(TaobaoItemDTO dto : taobaoList){
			if(dto != null){
				map.put(dto.getNumIid(), dto) ;
			}
		}
		
		return map ;
	}
	
	public static Map<Long,TaobaokeItemDTO> convertTaobaokeItemToMap(List<TaobaokeItemDTO> taobaokeList){
		Map<Long,TaobaokeItemDTO> map = new HashMap<Long,TaobaokeItemDTO>() ;
		if(CollectionUtils.isEmpty(taobaokeList)){
			return map ;
		}
		
		for(TaobaokeItemDTO dto : taobaokeList){
			if(dto != null){
				map.put(dto.getNumIid(), dto) ;
			}
		}
		
		return map ;
	}
	
	public static List<String> getItemImgUrls(TaobaoItemDTO item){
		List<String> picList = new ArrayList<String>() ;
		if(item == null){
			return picList ;
		}
		List<ItemImg> itemImgs = item.getItemImgs() ;
		if(CollectionUtils.isNotEmpty(itemImgs)){
			for(ItemImg itemImg : itemImgs){
				picList.add(itemImg.getUrl()) ;
			}
		}
		if(CollectionUtils.isEmpty(picList)){
			picList.add(item.getPicUrl()) ;
		}
		return picList ;
	}
	
}
