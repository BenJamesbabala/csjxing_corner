package com.doucome.corner.biz.dcome.enums;

import java.util.Date;

import com.doucome.corner.biz.dcome.exception.DcUnSupportOperationException;

/**
 * 豆蔻分享枚举
 * @author ze2200
 *
 */
public enum DcPromScheduleEnum {
	/**
	 * 进行中
	 */
	ONGOING("ON") {
		
		public int exchangeCompareTo(DcPromScheduleEnum schedule) {
			if (schedule == this) {
				return 0;
			}
			return 1;
		}
	},
	/**
	 * 结束
	 */
	ENDED("EN") {
		@Override
		public int exchangeCompareTo(DcPromScheduleEnum schedule) {
			if (schedule == this) {
				return 0;
			}
			return -1;
		}
	},
	/**
	 * 预告
	 */
	FUTURE("FU") {
		@Override
		public int exchangeCompareTo(DcPromScheduleEnum schedule) {
			if (schedule == this) {
				return 0;
			}
			return schedule == ONGOING ? -1: 1;
		}
	},
	UNKNOW("") {
		@Override
		public int exchangeCompareTo(DcPromScheduleEnum schedule) {
			throw new DcUnSupportOperationException("unsupport operation");
		}
	};
	
	private String value;
	
	private DcPromScheduleEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	/**
	 * 积分兑换物品排序comparator.
	 * @param schedule
	 * @return
	 */
	public abstract int exchangeCompareTo(DcPromScheduleEnum schedule);
	
	public static DcPromScheduleEnum toEnum(Date start, Date end) {
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
