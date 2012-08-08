package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcQIndexConfigDO;

public interface DcQIndexConfigDAO {

	/**
	 * ������ҳ����
	 * @param config
	 * @return
	 */
	Long insertConfig(DcQIndexConfigDO config) ;
	
	/**
	 * ����״̬
	 * @param configId
	 * @return
	 */
	int updateSceneIdBySysAndStatus(Long sceneId , Long systemId , String pubStatus) ;
	
	/**
	 * ������Ŀ��״̬��ѯ
	 * @param topCatId
	 * @param status
	 * @return
	 */
	DcQIndexConfigDO queryConfigBySysAndStatus(Long systemId , String pubStatus) ;

	/**
	 * ���ݷ���״̬��ѯ
	 * @param value
	 * @return
	 */
	List<DcQIndexConfigDO> queryConfigsByStatus(String value);
}
