package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.TrueFalseEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcSceneSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDO;
import com.doucome.corner.biz.dal.dcome.DcSceneDAO;
import com.doucome.corner.biz.dal.dcome.DcSceneDetailDAO;
import com.doucome.corner.biz.dcome.cache.DcSceneCache;
import com.doucome.corner.biz.dcome.model.DcSceneDTO;
import com.doucome.corner.biz.dcome.service.DcSceneService;

public class DcSceneServiceImpl implements DcSceneService {

	@Autowired
	private DcSceneDAO dcSceneDAO ;
	
	@Autowired
	private DcSceneDetailDAO dcSceneDetailDAO ;
	
	@Autowired
	private DcSceneCache dcSceneCache ;
	
	@Override
	public Long createScene(DcSceneDO scene) {
		return dcSceneDAO.insertScene(scene) ;
	}

	@Override
	public DcSceneDTO getSceneById(long sceneId) {
		
		DcSceneDTO dto = dcSceneCache.get(sceneId) ;
		if(dto == null){
			DcSceneDO scene = dcSceneDAO.querySceneById(sceneId) ;
			if(scene == null){
				return null ;
			}
			dto =  new DcSceneDTO(scene);
		}
		
		return dto ;
	}
	
	@Override
	public DcSceneDTO getSceneWithDetailsById(long sceneId) {
		DcSceneDTO dto = dcSceneCache.get(sceneId) ;
		if(dto == null){
			DcSceneDO scene = dcSceneDAO.querySceneById(sceneId) ;
			if(scene == null){
				return null ;
			}
			dto =  new DcSceneDTO(scene);
			List<Long> itemIdList = dcSceneDetailDAO.queryItemsBySceneWithPagination(sceneId, 1 , 200) ;
			dto.setItemIdList(itemIdList) ;
			dcSceneCache.set(dto) ;
		}
		return dto;
	}

	@Override
	public int updateSceneById(DcSceneDO scene) {
		int effectCount = dcSceneDAO.updateSceneById(scene);  
		triggerCacheModified(scene.getId()) ;
		return effectCount ;
	}

	@Override
	public int updateSceneActiveById(long sceneId, TrueFalseEnums active) {
		int effectCount = dcSceneDAO.updateSceneActiveById(sceneId, active.getValue()) ;
		triggerCacheModified(sceneId) ;
		return effectCount ;
	}
	
	@Override
	public QueryResult<DcSceneDTO> getScenesWithPagination(DcSceneSearchCondition searchCondition, Pagination pagination) {
		int totalRecords = dcSceneDAO.countScenesWithPagination(searchCondition) ;
        if (totalRecords <= 0) {
            return new QueryResult<DcSceneDTO>(new ArrayList<DcSceneDTO>(), pagination, totalRecords);
        }
        List<DcSceneDO> itemList = dcSceneDAO.queryScenesWithPagination(searchCondition, pagination.getStart(), pagination.getSize());
        
        List<DcSceneDTO> dtoList = new ArrayList<DcSceneDTO>() ;
        if(!CollectionUtils.isEmpty(itemList)){
	        for(DcSceneDO item : itemList){
	        	dtoList.add(new DcSceneDTO(item)) ;
	        }
        }
        
        return new QueryResult<DcSceneDTO>(dtoList, pagination, totalRecords);
	}

	
	private void triggerCacheModified(Long id) {
		dcSceneCache.remove(id) ;
	}
	
}
