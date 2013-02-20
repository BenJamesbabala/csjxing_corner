package com.doucome.corner.biz.core.service;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.BannerConfigSearchCondition;
import com.doucome.corner.biz.dal.dataobject.BannerConfigDO;

public interface BannerConfigService {

	/**
	 * 
	 * @return
	 */
	BannerConfigDO getConfigByBannerId(String bannerId) ;
	
	/**
	 * 
	 * @param config
	 * @return
	 */
	int updateConfigByBannerId(BannerConfigDO config) ;
	
	/**
	 * ����״̬
	 * @param bannerId
	 * @param status
	 * @return
	 */
	int updateConfigStatusByBannerId(String bannerId , String status) ;
	
	/**
	 * ��ѯbanner����
	 * @param condition
	 * @param pagination
	 * @return
	 */
	QueryResult<BannerConfigDO> getConfigsWithPagination(BannerConfigSearchCondition searchCondition , Pagination pagination) ;
	
	/**
	 * banner���¼�
	 * @param bannerId
	 * @param bindEvent
	 * @param bindEventFunction
	 * @return
	 */
	int updateBindEventById(String bannerId , String bindEvent , String bindEventFunction) ;

}
