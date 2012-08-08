package com.doucome.corner.biz.dcome.model.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dcome.model.DcCategoryDTO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcSceneDTO;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;

/**
 * չ�ָ�QQ�ռ�ǰ�˵����ݽṹ
 * @author shenjia.caosj 2012-7-28
 *
 */
public class DcSceneQIndexFacade extends AbstractModel {

	private static final long serialVersionUID = -40807569013635079L;

	private DcSceneDTO dcSceneDTO ;
	
	private Map<Long,List<DcItemDTO>> catItemMappings = new HashMap<Long,List<DcItemDTO>>() ;
	
	private Map<Long,DcCategoryDTO> catMappings = new HashMap<Long,DcCategoryDTO>() ;
	
	public DcSceneQIndexFacade(DcSceneDTO dto){
		if(dto == null){
			return ;
		}
		
		this.dcSceneDTO = dto ;
		
		List<DcItemDTO> itemList = dto.getItemList() ;
		if(CollectionUtils.isEmpty(itemList)){
			return ;
		}		
		
		//����category������Ʒ
		for(DcItemDTO item : itemList){
			Long catId = item.getCategoryId() ;
			if(!catItemMappings.containsKey(catId)){
				catItemMappings.put(catId, new ArrayList<DcItemDTO>()) ;
			}
			List<DcItemDTO> itemListByCat = catItemMappings.get(catId) ;
			itemListByCat.add(item) ;
		}
		
		//��ÿ�������������
		Set<Long> keySet = catItemMappings.keySet();
		for(Long catId : keySet){
			List<DcItemDTO> itemListByCat = catItemMappings.get(catId) ;
			DcItemUtils.sortByLoves(itemListByCat) ;//����ϲ������
		}
	}

	public DcSceneDTO getDcSceneDTO() {
		return dcSceneDTO;
	}

	public Map<Long, List<DcItemDTO>> getCatItemMappings() {
		return catItemMappings;
	}

	public Map<Long, DcCategoryDTO> getCatMappings() {
		return catMappings;
	}

	public void setCatMappings(Map<Long, DcCategoryDTO> catMappings) {
		this.catMappings = catMappings;
	}
}
