package com.doucome.corner.biz.dcome.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcQIndexConfigDO;
import com.doucome.corner.biz.dcome.enums.DcQIndexPubStatusEnums;
import com.doucome.corner.biz.dcome.model.DcSystemDTO;
import com.doucome.corner.biz.dcome.service.DcQIndexConfigService;
import com.doucome.corner.biz.dcome.service.DcSystemService;

/**
 * 
 * @author langben 2012-7-27
 *
 */
public class DcQIndexConfigBO {

	@Autowired
	private DcQIndexConfigService dcQIndexConfigService ;
	
	@Autowired
	private DcSystemService dcSystemService ;
	
	/**
	 * Ԥ���� - ����
	 * @param systemId
	 * @param sceneId
	 * @param pubStatus
	 */
	public void release(Long systemId , Long sceneId , DcQIndexPubStatusEnums pubStatus){
		if(systemId == null || sceneId == null){
			throw new IllegalArgumentException("systemId and sceneId cant be null .") ;
		}
		
		int effectCount = dcQIndexConfigService.updateSceneIdBySysAndStatus(sceneId , systemId, pubStatus) ;
		if(effectCount < 1){ //��������С��1 �� ���Ƿ���������¼
			DcSystemDTO system  = dcSystemService.getSystemById(systemId) ;
			if(system == null){
				//������systemId = xxx ��APP
				throw new IllegalArgumentException("illegal input systemId : " + systemId) ;
			}
			
					
			//����һ����¼
			DcQIndexConfigDO config = new DcQIndexConfigDO() ;
			config.setPubStatus(pubStatus.getValue()) ;
			config.setSceneId(sceneId);
			config.setSystemId(systemId);
			Long newId = dcQIndexConfigService.createConfig(config) ;
		}
		
	}
}
