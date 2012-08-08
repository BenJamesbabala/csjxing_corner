package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcQIndexConfigDO;

public interface DcQIndexConfigDAO {

	/**
	 * 插入首页配置
	 * @param config
	 * @return
	 */
	Long insertConfig(DcQIndexConfigDO config) ;
	
	/**
	 * 更新状态
	 * @param configId
	 * @return
	 */
	int updateSceneIdBySysAndStatus(Long sceneId , Long systemId , String pubStatus) ;
	
	/**
	 * 根据类目和状态查询
	 * @param topCatId
	 * @param status
	 * @return
	 */
	DcQIndexConfigDO queryConfigBySysAndStatus(Long systemId , String pubStatus) ;

	/**
	 * 根据发布状态查询
	 * @param value
	 * @return
	 */
	List<DcQIndexConfigDO> queryConfigsByStatus(String value);
}
