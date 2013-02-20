package com.doucome.corner.biz.dcome.enums;

import java.util.Date;

import com.doucome.corner.biz.dcome.exception.DcUnSupportOperationException;

/**
 * ��ޢ����ö��
 * @author ze2200
 *
 */
public enum DcPromScheduleEnum {
	/**
	 * ������
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
	 * ����
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
	 * Ԥ��
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
	 * ���ֶһ���Ʒ����comparator.
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
