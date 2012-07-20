package com.doucome.corner.biz.zhe.service;

import java.util.List;

import com.doucome.corner.biz.zhe.model.TaobaokeReportFacade;
import com.doucome.corner.biz.zhe.model.TaobaokeShopFacade;

/**
 * �Ƽ�
 * @author shenjia.caosj 2012-5-22
 *
 */
public interface DdzRecommendService {
	
	/**
	 * ��ҳƷ��
	 * @param shopIdList
	 * @param outCode
	 * @return
	 */
	List<TaobaokeShopFacade> getIndexBrands() ;

	
	/**
	 * ����������
	 * @param count
	 * @return
	 */
	List<TaobaokeReportFacade> getBuyingItems(int count) ;
	
}
