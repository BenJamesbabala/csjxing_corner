package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDetailDO;

public interface DcSceneDetailService {

	/**
	 * �½�һ��������Ʒ��ϵ
	 * @param detail
	 * @return
	 */
	Long createSceneDetail(DcSceneDetailDO detail) ;
	
	/**
	 * ���ݳ�����ѯ��Ʒ
	 * @param sceneId
	 * @return
	 */
	List<Long> getItemsByScene(long sceneId ,int size) ;
	
	/**
	 * ��ҳ��ѯ������Ʒ
	 * @param sceneId
	 * @return
	 */
	QueryResult<Long> getItemsBySceneWithPagination(long sceneId , Pagination pagination) ;
	
	/**
	 * ɾ��������Ʒ��ϵ
	 * @param itemId
	 * @param sceneId
	 */
	int removeSceneDetail(long itemId , long sceneId) ;
	
}
