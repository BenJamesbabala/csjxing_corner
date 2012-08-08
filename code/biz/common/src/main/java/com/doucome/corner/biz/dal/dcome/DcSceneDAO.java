package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcSceneSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDO;

/**
 * 场景
 * @author shenjia.caosj 2012-7-23
 *
 */
public interface DcSceneDAO {

	/**
	 * 新建一个场景
	 * @param scene
	 * @return
	 */
	Long insertScene(DcSceneDO scene);

	/**
	 * 根据ID查询场景
	 * @return
	 */
	DcSceneDO querySceneById(long sceneId) ;
	
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
	int updateSceneActiveById(long sceneId, String active);

	/**
	 * 分页查询场景
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	List<DcSceneDO> queryScenesWithPagination(DcSceneSearchCondition searchCondition, int start, int size);

	/**
	 * 分页查询场景
	 * @param searchCondition
	 * @return
	 */
	int countScenesWithPagination(DcSceneSearchCondition searchCondition);

	
	
}
