package com.doucome.corner.biz.dcome.service;

import java.util.Date;

/**
 * ��Ʒ��Ϣͬ����.
 * @author ze2200
 *
 */
public interface DcItemSyncService {
	/**
	 * ͬ���ۿ�����
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer syncItemDiscount(Date startDate, Date endDate);
}
