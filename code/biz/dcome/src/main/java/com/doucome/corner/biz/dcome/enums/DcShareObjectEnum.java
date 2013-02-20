package com.doucome.corner.biz.dcome.enums;

import java.util.Calendar;
import java.util.Date;

import com.doucome.corner.biz.core.utils.DateUtils;

/**
 * �������Enum
 * @author ze2200
 *
 */
public enum DcShareObjectEnum {
	PROMOTION("PROM") {
		@Override
		public int getAwardIntegral() {
			return 10;
		}

		@Override
		public Date getCurValidShareStartTime() {
			Calendar cal = Calendar.getInstance();
			if (cal.get(Calendar.HOUR_OF_DAY) < 22) {
				cal.add(Calendar.DAY_OF_MONTH, -1);
			}
			cal.set(Calendar.HOUR_OF_DAY, 22);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 1);
			cal.set(Calendar.MILLISECOND, 0);
			return cal.getTime();
		}
	},
	APP("APP") {
		@Override
		public int getAwardIntegral() {
			return 10;
		}
	},
	ITEM("ITEM") {
		@Override
		public int getAwardIntegral() {
			return 10;
		}
	},
	AWARD("AWARD") {
		@Override
		public int getAwardIntegral() {
			return 0;
		}
		
		/**
		 * �κ�ʱ��Ʒ�����ǿ��Ե�.
		 */
		@Override
		public Date getCurValidShareStartTime() {
			Calendar cal = Calendar.getInstance();
			cal.set(9999, 12, 30);
			return cal.getTime();
		}
		
	};
	
	private String value;
	
	private DcShareObjectEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String getExactValue() {
		return DcIntegralSourceEnums.SHARE + "_" + this.value;
	}
	
	/**
	 *
	 * @return
	 */
	public abstract int getAwardIntegral();
	/**
	 * ������Ч����Ŀ�ʼʱ��.
	 * @return
	 */
	public Date getCurValidShareStartTime() {
		return DateUtils.trimDate(new Date(), Calendar.HOUR_OF_DAY);
	}
	
	public static DcShareObjectEnum toEnum(String name) {
		try {
			return Enum.valueOf(DcShareObjectEnum.class, name);
		} catch (Exception e) {
			for (DcShareObjectEnum temp: DcShareObjectEnum.values()) {
				if (temp.getValue().equalsIgnoreCase(name)) {
					return temp;
				}
			}
		}
		return null;
	}
}
