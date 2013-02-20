package com.doucome.corner.biz.dcome.enums;

import java.util.Date;

/**
 * ��ޢ����ö��
 * @author ze2200
 *
 */
public enum DcAuctionStatusEnum {
	/**
	 * ������
	 */
	ONGOING,
	/**
	 * ����
	 */
	ENDED,
	/**
	 * Ԥ��
	 */
	FUTURE,
	UNKNOW;
	
	public static DcAuctionStatusEnum toEnum(Date start, Date end) {
		if (start == null || end == null) {
			return UNKNOW;
		}
		Date now = new Date();
		if (now.compareTo(start) < 0) {
			return FUTURE;
		} else if (now.compareTo(end) > 0) {
			return ENDED;
		} else {
			return ONGOING;
		}
	}
}
