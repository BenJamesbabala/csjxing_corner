package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcSceneDetailDO;

/**
 * ��������Ʒ��ϵ
 * @author shenjia.caosj 2012-7-23
 *
 */
public interface DcSceneDetailDAO {

	/**
	 * �½�һ��������Ʒ��ϵ
	 * @param detail
	 * @return
	 */
	Long insertSceneDetail(DcSceneDetailDO detail) ;
	
	/**
	 * ���ݳ�����ѯ��Ʒ
	 * @param sceneId
	 * @return
	 */
	List<Long> queryItemsBySceneWithPagination(long sceneId , int start , int size) ;
	
	/**
	 * Count��������Ʒ
	 * @param sceneId
	 * @return
	 */
	int countItemsBySceneWithPagination(long sceneId);
	
	/**
	 * ɾ��������Ʒ��ϵ
	 * @param itemId
	 * @param sceneId
	 */
	int deleteSceneDetail(long itemId , long sceneId) ;

	
	
	
}
