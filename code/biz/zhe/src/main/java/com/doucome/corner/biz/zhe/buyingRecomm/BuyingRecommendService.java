package com.doucome.corner.biz.zhe.buyingRecomm;

import java.util.List;

import com.doucome.corner.biz.zhe.vo.BuyingRecommendVO;

public interface BuyingRecommendService {

	/**
	 * ��ȡ�Ƽ�
	 * @return
	 */
	List<BuyingRecommendVO> getBuyingItems(int count) ;
	
	
}
