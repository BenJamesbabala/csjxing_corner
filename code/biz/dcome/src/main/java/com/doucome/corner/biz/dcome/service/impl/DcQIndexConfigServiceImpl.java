package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcQIndexConfigDO;
import com.doucome.corner.biz.dal.dcome.DcQIndexConfigDAO;
import com.doucome.corner.biz.dcome.cache.DcQIndexConfigCache;
import com.doucome.corner.biz.dcome.enums.DcQIndexPubStatusEnums;
import com.doucome.corner.biz.dcome.model.DcQIndexConfigDTO;
import com.doucome.corner.biz.dcome.service.DcQIndexConfigService;

public class DcQIndexConfigServiceImpl implements DcQIndexConfigService{
	
	@Autowired
	private DcQIndexConfigDAO dcQIndexConfigDAO ;
	
	@Autowired
	private DcQIndexConfigCache dcQIndexConfigCache ;

	@Override
	public Long createConfig(DcQIndexConfigDO config) {
		return dcQIndexConfigDAO.insertConfig(config) ;
	}

	@Override
	public int updateSceneIdBySysAndStatus(Long sceneId , Long systemId,DcQIndexPubStatusEnums pubStatus) {
		int effectCount = dcQIndexConfigDAO.updateSceneIdBySysAndStatus(sceneId , systemId, pubStatus.getValue()) ;
		triggerCacheModified(systemId,pubStatus.getValue()) ;
		return effectCount ;
	}

	@Override
	public DcQIndexConfigDTO getConfigBySysAndStatus(Long systemId,DcQIndexPubStatusEnums pubStatus) {
		DcQIndexConfigDTO dto = dcQIndexConfigCache.get(systemId, pubStatus.getValue()) ;
		if(dto == null){
			DcQIndexConfigDO config = dcQIndexConfigDAO.queryConfigBySysAndStatus(systemId, pubStatus.getValue()) ;
			if(config == null){
				return null ;
			}
			dto =  new DcQIndexConfigDTO(config) ;
			dcQIndexConfigCache.set(dto) ;
		}
		return dto ;
	}

	@Override
	public List<DcQIndexConfigDTO> getConfigsByStatus(DcQIndexPubStatusEnums pubStatus) {
		List<DcQIndexConfigDO> confList = dcQIndexConfigDAO.queryConfigsByStatus(pubStatus.getValue()) ;
		
		List<DcQIndexConfigDTO> dtoList = new ArrayList<DcQIndexConfigDTO>() ;
        if(!CollectionUtils.isEmpty(confList)){
	        for(DcQIndexConfigDO item : confList){
	        	dtoList.add(new DcQIndexConfigDTO(item)) ;
	        }
        }
		return dtoList;
	}
	
	private void triggerCacheModified(Long id , String status) {
		dcQIndexConfigCache.remove(id,status) ;
	}

}
