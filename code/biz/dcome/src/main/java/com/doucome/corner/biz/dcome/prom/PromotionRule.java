package com.doucome.corner.biz.dcome.prom;

import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.web.common.model.ResultModel;

/**
 * 活动规则.
 * @author ze2200
 *
 */
public interface PromotionRule {
	/**
	 * 校验活动自身.
	 * @return
	 */
	ResultModel<Boolean> checkSelf();
	/**
	 * 校验商品是否符合活动规则.
	 * @param dcItem 
	 * @return
	 */
	ResultModel<Boolean> checkItem(DcItemDTO dcItem);
}
