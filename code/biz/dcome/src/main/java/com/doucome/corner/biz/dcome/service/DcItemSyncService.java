package com.doucome.corner.biz.dcome.service;

import java.util.Date;

/**
 * 商品信息同步类.
 * @author ze2200
 *
 */
public interface DcItemSyncService {
	/**
	 * 同步折扣数据
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer syncItemDiscount(Date startDate, Date endDate);
}
