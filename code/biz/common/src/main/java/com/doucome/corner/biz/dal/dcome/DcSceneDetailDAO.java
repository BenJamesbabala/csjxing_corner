package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDetailDO;

/**
 * 场景与商品关系
 * @author shenjia.caosj 2012-7-23
 *
 */
public interface DcSceneDetailDAO {

	/**
	 * 新建一个场景商品关系
	 * @param detail
	 * @return
	 */
	Long insertSceneDetail(DcSceneDetailDO detail) ;
	
	/**
	 * 根据场景查询商品
	 * @param sceneId
	 * @return
	 */
	List<Long> queryItemsBySceneWithPagination(long sceneId , int start , int size) ;
	
	/**
	 * Count场景下商品
	 * @param sceneId
	 * @return
	 */
	int countItemsBySceneWithPagination(long sceneId);
	
	/**
	 * 删除场景商品关系
	 * @param itemId
	 * @param sceneId
	 */
	int deleteSceneDetail(long itemId , long sceneId) ;

	
	
	
}
