package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDetailDO;

public interface DcSceneDetailService {

	/**
	 * 新建一个场景商品关系
	 * @param detail
	 * @return
	 */
	Long createSceneDetail(DcSceneDetailDO detail) ;
	
	/**
	 * 根据场景查询商品
	 * @param sceneId
	 * @return
	 */
	List<Long> getItemsByScene(long sceneId ,int size) ;
	
	/**
	 * 分页查询场景商品
	 * @param sceneId
	 * @return
	 */
	QueryResult<Long> getItemsBySceneWithPagination(long sceneId , Pagination pagination) ;
	
	/**
	 * 删除场景商品关系
	 * @param itemId
	 * @param sceneId
	 */
	int removeSceneDetail(long itemId , long sceneId) ;
	
}
