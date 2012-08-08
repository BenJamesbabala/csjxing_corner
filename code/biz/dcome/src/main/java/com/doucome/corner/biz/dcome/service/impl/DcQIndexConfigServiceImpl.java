package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcQIndexConfigDO;
import com.doucome.corner.biz.dal.dcome.DcQIndexConfigDAO;
import com.doucome.corner.biz.dcome.enums.DcQIndexPubStatusEnums;
import com.doucome.corner.biz.dcome.model.DcQIndexConfigDTO;
import com.doucome.corner.biz.dcome.service.DcQIndexConfigService;

public class DcQIndexConfigServiceImpl implements DcQIndexConfigService{
	
	@Autowired
	private DcQIndexConfigDAO dcQIndexConfigDAO ;

	@Override
	public Long createConfig(DcQIndexConfigDO config) {
		return dcQIndexConfigDAO.insertConfig(config) ;
	}

	@Override
	public int updateSceneIdBySysAndStatus(Long sceneId , Long systemId,DcQIndexPubStatusEnums pubStatus) {
		return dcQIndexConfigDAO.updateSceneIdBySysAndStatus(sceneId , systemId, pubStatus.getValue()) ;
	}

	@Override
	public DcQIndexConfigDTO getConfigBySysAndStatus(Long systemId,DcQIndexPubStatusEnums pubStatus) {
		DcQIndexConfigDO config = dcQIndexConfigDAO.queryConfigBySysAndStatus(systemId, pubStatus.getValue()) ;
		if(config == null){
			return null ;
		}
		return new DcQIndexConfigDTO(config) ;
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

}
