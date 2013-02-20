package com.doucome.corner.biz.dcome.cache;

import java.util.List;
import java.util.Map;

import com.doucome.corner.biz.dcome.model.DcCategoryDTO;

/**
 * ��Ŀ����
 * @author langben 2012-7-28
 *
 */
public interface DcCategoryCache {

	/**
	 * ����ID��ȡ��Ŀ
	 * @param catId
	 * @return
	 */
	DcCategoryDTO get(Long catId) ;
	
	/**
	 * ������ȡCategory
	 * @param idList
	 * @return
	 */
	Map<Long,DcCategoryDTO> getCacheMap(List<Long> idList) ;
	
	/**
	 * ����
	 * @param cat
	 */
	void set(DcCategoryDTO cat) ;
	
	/**
	 * �Ƴ�
	 * @param catId
	 */
	void remove(Long catId) ;
}
