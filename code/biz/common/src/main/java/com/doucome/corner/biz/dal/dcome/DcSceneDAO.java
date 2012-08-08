package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcSceneSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDO;

/**
 * ����
 * @author shenjia.caosj 2012-7-23
 *
 */
public interface DcSceneDAO {

	/**
	 * �½�һ������
	 * @param scene
	 * @return
	 */
	Long insertScene(DcSceneDO scene);

	/**
	 * ����ID��ѯ����
	 * @return
	 */
	DcSceneDO querySceneById(long sceneId) ;
	
	/**
	 * ���³���
	 * @param id
	 * @param name
	 * @return
	 */
	int updateSceneById(DcSceneDO scene) ;
	
	/**
	 * ���³��� ����״̬
	 * @param sceneId
	 * @param active
	 * @return
	 */
	int updateSceneActiveById(long sceneId, String active);

	/**
	 * ��ҳ��ѯ����
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	List<DcSceneDO> queryScenesWithPagination(DcSceneSearchCondition searchCondition, int start, int size);

	/**
	 * ��ҳ��ѯ����
	 * @param searchCondition
	 * @return
	 */
	int countScenesWithPagination(DcSceneSearchCondition searchCondition);

	
	
}
