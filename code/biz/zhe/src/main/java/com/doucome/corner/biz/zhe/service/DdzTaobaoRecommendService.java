package com.doucome.corner.biz.zhe.service;

import java.util.List;

import com.doucome.corner.biz.core.taobao.dto.TaobaoFavoriteItemDTO;
import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;

public interface DdzTaobaoRecommendService {

	/**
	 * �����Ľ� TaobaoFavoriteItemDTO => TaobaokeItemFacade
	 * <br/> ���ѯԶ�̽ӿ�
	 * @param favoList
	 * @return
	 */
	List<TaobaokeItemFacade> batchConventer(List<TaobaoFavoriteItemDTO> favoList , String outCode ) ;
}
