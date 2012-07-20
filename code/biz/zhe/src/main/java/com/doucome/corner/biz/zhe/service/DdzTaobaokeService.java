package com.doucome.corner.biz.zhe.service;

import java.util.List;

import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;
import com.doucome.corner.biz.zhe.model.TaobaokeShopFacade;

/**
 * Ʒ��
 * @author shenjia.caosj 2012-6-28
 *
 */
public interface DdzTaobaokeService {

	/**
	 * ��ѯ����
	 * @param shopIds
	 * @param outCode
	 * @return
	 */
	List<TaobaokeShopFacade> conventShops(List<String> shopIdList , String outCode) ;
	
	/**
	 * ת������
	 * @param ShopId
	 * @param outCode
	 * @return
	 */
	TaobaokeShopFacade conventShop(String ShopId , String outCode) ;
	
	/**
	 * ת����Ʒ
	 * @param itemId
	 * @param outCode
	 * @return
	 */
	TaobaokeItemFacade conventItem(String itemId , String outCode);
	
	/**
	 * ��ѯ�Ա���Ʒ
	 * @return
	 */
	TaobaokeItemFacade getTaobaoItem(String itemId) ;
	
	
	/**
	 * ����ת���Ƽ���Ʒ
	 * @param favList
	 * @return
	 */
	List<TaobaokeItemFacade> getFavoriteItems(String itemId , int count) ;
	
}
