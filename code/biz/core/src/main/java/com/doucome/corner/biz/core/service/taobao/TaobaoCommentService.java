package com.doucome.corner.biz.core.service.taobao;

import java.util.List;

import com.doucome.corner.biz.core.taobao.dto.TaobaoCommentDTO;
import com.doucome.corner.biz.core.taobao.enums.TaobaoCommentEnum;

/**
 * 商品评论service.
 * @author ze2200
 *
 */
public interface TaobaoCommentService {
	/**
	 * 获取商品评论.
	 * @param itemNativeId 商品的淘宝数字ID
	 * @param sellerId 商品销售者id.
	 * @param commentEnum 
	 * @return 商品评论列表。null：请求失败.
	 */
	List<TaobaoCommentDTO> getItemComments(Long itemNativeId, Long sellerId, TaobaoCommentEnum commentEnum);
}
