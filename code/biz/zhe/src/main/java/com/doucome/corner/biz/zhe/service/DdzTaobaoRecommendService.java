package com.doucome.corner.biz.zhe.service;

import java.util.List;

import com.doucome.corner.biz.core.taobao.dto.TaobaoFavoriteItemDTO;
import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;

public interface DdzTaobaoRecommendService {

	/**
	 * 批量的将 TaobaoFavoriteItemDTO => TaobaokeItemFacade
	 * <br/> 会查询远程接口
	 * @param favoList
	 * @return
	 */
	List<TaobaokeItemFacade> batchConventer(List<TaobaoFavoriteItemDTO> favoList , String outCode ) ;
}
