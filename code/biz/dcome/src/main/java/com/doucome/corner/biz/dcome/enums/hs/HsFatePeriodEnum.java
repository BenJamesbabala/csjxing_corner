package com.doucome.corner.biz.dcome.enums.hs;

import java.util.Calendar;
import java.util.Date;

import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dcome.exception.DcUnSupportOperationException;

/**
 * 
 * @author ze2200
 *
 */
public enum HsFatePeriodEnum {
	TODAY(1) {
		@Override
		public Date[] getValidPeriod(Date date) {
			return DateUtils.getDayStartEnd(date);
		}
	},
	TOMORROW(2) {
		@Override
		public Date[] getValidPeriod(Date date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			return DateUtils.getDayStartEnd(calendar.getTime());
		}
	},
	WEEK(3) {
		@Override
		public Date[] getValidPeriod(Date date) {
			return DateUtils.getWeekStartEnd(date);
		}
	},
	MONTH(4) {
		@Override
		public Date[] getValidPeriod(Date date) {
			return DateUtils.getMonthStartEnd(date);
		}
	},
	UNKNOW(-1) {
		@Override
		public Date[] getValidPeriod(Date date) {
			throw new DcUnSupportOperationException("Unsupport Operation");
		}
	};
	
	private int id;
	
	private HsFatePeriodEnum(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	/**
	 * 获取有效时间.
	 * @return
	 */
	public abstract Date[] getValidPeriod(Date date);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static HsFatePeriodEnum toEnum(int id) {
		for (HsFatePeriodEnum temp: HsFatePeriodEnum.values()) {
			if (temp.getId().equals(id)) {
				return temp;
			}
		}
		return UNKNOW;
	}
}
