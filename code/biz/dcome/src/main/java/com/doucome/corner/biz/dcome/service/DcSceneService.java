package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.core.enums.TrueFalseEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcSceneSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDO;
import com.doucome.corner.biz.dcome.model.DcSceneDTO;

public interface DcSceneService {

	/**
	 * �½�һ������
	 * @param scene
	 * @return
	 */
	Long createScene(DcSceneDO scene);

	/**
	 * ����ID��ѯ����
	 * @return
	 */
	DcSceneDTO getSceneById(long sceneId) ;
	
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
	int updateSceneActiveById(long sceneId, TrueFalseEnums active);
	
	/**
	 * ��ҳ��ѯ����
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	QueryResult<DcSceneDTO> getScenesWithPagination(DcSceneSearchCondition searchCondition , Pagination pagination) ;
	
}
