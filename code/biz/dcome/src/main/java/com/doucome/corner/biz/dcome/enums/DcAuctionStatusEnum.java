package com.doucome.corner.biz.dcome.enums;

import java.util.Date;

/**
 * 豆蔻分享枚举
 * @author ze2200
 *
 */
public enum DcAuctionStatusEnum {
	/**
	 * 进行中
	 */
	ONGOING,
	/**
	 * 结束
	 */
	ENDED,
	/**
	 * 预告
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
