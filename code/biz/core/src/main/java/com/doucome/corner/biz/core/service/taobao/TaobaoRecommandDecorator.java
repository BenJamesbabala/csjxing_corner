package com.doucome.corner.biz.core.service.taobao;

import java.util.List;

import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.taobao.dto.TaobaoFavoriteItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoPromotionDisplayDTO;
import com.doucome.corner.biz.core.taobao.model.TaobaoRecommendItemCondition;

/**
 * �Ա������Ƽ�
 * @author langben 2012-3-22
 *
 */
public interface TaobaoRecommandDecorator {

	/**
	 * ����Ƽ���Ʒ
	 * @param condition
	 * @return
	 * @throws TaobaoRemoteException
	 */
	List<TaobaoFavoriteItemDTO> getRecommandItemsByItem(Long itemId , TaobaoRecommendItemCondition condition) throws TaobaoRemoteException ;
	
	/**
	 * ��Ʒ�Ż������ѯ 
	 * @param itemId ��Ʒid 
	 * @param channelKey ������Դ��������վ�� ��ѡ 
	 * @return
	 */
	TaobaoPromotionDisplayDTO getPromotionUmp(Long itemId ,String channelKey) ;
	
	/**
	 * ����Ƽ���Ʒ
	 * @param categoryId
	 * @param condition
	 * @return
	 */
	List<TaobaoFavoriteItemDTO> getRecommendItemsByCategory(Long categoryId , TaobaoRecommendItemCondition condition) ;
	
}
