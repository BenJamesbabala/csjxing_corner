package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dal.dataobject.dcome.DcCategoryDO;

/**
 * ��Ŀ����
 * @author shenjia.caosj 2012-7-28
 *
 */
public interface DcCategoryCache {

	/**
	 * ����ID��ȡ��Ŀ
	 * @param catId
	 * @return
	 */
	DcCategoryDO get(Long catId) ;
	
	/**
	 * ����
	 * @param cat
	 */
	void set(DcCategoryDO cat) ;
	
	/**
	 * �Ƴ�
	 * @param catId
	 */
	void remove(Long catId) ;
}
