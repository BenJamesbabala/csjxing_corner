package com.doucome.corner.biz.dcome.prom;

import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.web.common.model.ResultModel;

/**
 * �����.
 * @author ze2200
 *
 */
public interface PromotionRule {
	/**
	 * У������.
	 * @return
	 */
	ResultModel<Boolean> checkSelf();
	/**
	 * У����Ʒ�Ƿ���ϻ����.
	 * @param dcItem 
	 * @return
	 */
	ResultModel<Boolean> checkItem(DcItemDTO dcItem);
}
