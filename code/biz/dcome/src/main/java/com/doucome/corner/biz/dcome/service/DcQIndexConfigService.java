package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcQIndexConfigDO;
import com.doucome.corner.biz.dcome.enums.DcQIndexPubStatusEnums;
import com.doucome.corner.biz.dcome.model.DcQIndexConfigDTO;

/**
 * QQ首页配置
 * @author langben 2012-7-26
 *
 */
public interface DcQIndexConfigService {

	/**
	 * 插入首页配置
	 * @param config
	 * @return
	 */
	Long createConfig(DcQIndexConfigDO config) ;
	
	/**
	 * 更新状态
	 * @param configId
	 * @return
	 */
	int updateSceneIdBySysAndStatus(Long sceneId , Long systemId , DcQIndexPubStatusEnums pubStatus) ;
	
	/**
	 * 根据类目和状态查询
	 * @param topCatId
	 * @param status
	 * @return
	 */
	DcQIndexConfigDTO getConfigBySysAndStatus(Long systemId , DcQIndexPubStatusEnums pubStatus) ;
	
	/**
	 * 根据发布状态查询
	 * @param pubStatus
	 * @return
	 */
	List<DcQIndexConfigDTO> getConfigsByStatus(DcQIndexPubStatusEnums pubStatus) ;
}
