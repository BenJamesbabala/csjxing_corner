package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcQIndexConfigDO;
import com.doucome.corner.biz.dcome.enums.DcQIndexPubStatusEnums;
import com.doucome.corner.biz.dcome.model.DcQIndexConfigDTO;

/**
 * QQ��ҳ����
 * @author langben 2012-7-26
 *
 */
public interface DcQIndexConfigService {

	/**
	 * ������ҳ����
	 * @param config
	 * @return
	 */
	Long createConfig(DcQIndexConfigDO config) ;
	
	/**
	 * ����״̬
	 * @param configId
	 * @return
	 */
	int updateSceneIdBySysAndStatus(Long sceneId , Long systemId , DcQIndexPubStatusEnums pubStatus) ;
	
	/**
	 * ������Ŀ��״̬��ѯ
	 * @param topCatId
	 * @param status
	 * @return
	 */
	DcQIndexConfigDTO getConfigBySysAndStatus(Long systemId , DcQIndexPubStatusEnums pubStatus) ;
	
	/**
	 * ���ݷ���״̬��ѯ
	 * @param pubStatus
	 * @return
	 */
	List<DcQIndexConfigDTO> getConfigsByStatus(DcQIndexPubStatusEnums pubStatus) ;
}
