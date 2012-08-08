package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.core.enums.TrueFalseEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcSceneSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDO;
import com.doucome.corner.biz.dcome.model.DcSceneDTO;

public interface DcSceneService {

	/**
	 * 新建一个场景
	 * @param scene
	 * @return
	 */
	Long createScene(DcSceneDO scene);

	/**
	 * 根据ID查询场景
	 * @return
	 */
	DcSceneDTO getSceneById(long sceneId) ;
	
	/**
	 * 更新场景
	 * @param id
	 * @param name
	 * @return
	 */
	int updateSceneById(DcSceneDO scene) ;
	
	/**
	 * 更新场景 激活状态
	 * @param sceneId
	 * @param active
	 * @return
	 */
	int updateSceneActiveById(long sceneId, TrueFalseEnums active);
	
	/**
	 * 分页查询场景
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	QueryResult<DcSceneDTO> getScenesWithPagination(DcSceneSearchCondition searchCondition , Pagination pagination) ;
	
}
