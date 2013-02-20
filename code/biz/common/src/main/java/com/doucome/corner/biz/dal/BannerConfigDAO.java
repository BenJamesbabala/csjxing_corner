package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.condition.BannerConfigSearchCondition;
import com.doucome.corner.biz.dal.dataobject.BannerConfigDO;

/**
 * Banner Config
 * @author langben 2012-9-27
 *
 */
public interface BannerConfigDAO {

	/**
	 * ��ѯ����
	 * @return
	 */
	BannerConfigDO queryConfigsByBannerId(String bannerId) ;
	
	/**
	 * ����banner
	 * @param bannerType
	 * @return
	 */
	int updateConfigByBannerId(BannerConfigDO config) ;
	
	/**
	 * count banner����
	 * @param condition
	 * @return
	 */
	int countConfigsWithPagination(BannerConfigSearchCondition condition) ;
	
	/**
	 * ��ѯbanner����
	 * @param condition
	 * @param pagination
	 * @return
	 */
	List<BannerConfigDO> queryConfigsWithPagination(BannerConfigSearchCondition searchCondition , int start, int size) ;

	/**
	 * 
	 * @param bannerId
	 * @param status
	 * @return
	 */
	int updateConfigStatusByBannerId(String bannerId, String status);
	
	/**
	 * banner���¼�
	 * @param bannerId
	 * @param bindEvent
	 * @param bindEventFunction
	 * @return
	 */
	int updateBindEventById(String bannerId , String bindEvent , String bindEventFunction) ;
}
