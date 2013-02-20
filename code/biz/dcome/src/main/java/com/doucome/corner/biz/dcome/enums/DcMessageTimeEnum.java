package com.doucome.corner.biz.dcome.enums;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.doucome.corner.biz.core.utils.DateUtils;

/**
 * ��ޢ��Ϣö��.
 * @author ze2200
 *
 */
public enum DcMessageTimeEnum {
	//�ո�(10������)
	JUST("JUST") {
		@Override
		public String formatDate(Date date) {
			return "";
		}
	},
	//������ǰ(10-20������)
	MINUTE("MINUTE") {
		@Override
		public String formatDate(Date date) {
			return String.valueOf(DateUtils.getDiffInMinute(new Date(), date));
		}
	},
	TODAY("TODAY") {
		@Override
		public String formatDate(Date date) {
			return new SimpleDateFormat("HH:mm").format(date);
		}
	},
	//ĳ��
	SOMEDAY("SOMEDAY") {
		@Override
		public String formatDate(Date date) {
			return new SimpleDateFormat("MM-dd").format(date);
		}
	},
	//δ֪ʱ��.
	UNKNOW("UNKNOW") {
		@Override
		public String formatDate(Date date) {
			return "";
		}
	};
	
	private String type;
	
	private String value;
	
	private DcMessageTimeEnum(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getValue() {
		return value;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFmtTime(Date date) {
		try {
			return formatDate(date);
		} catch(Exception e) {
			return "";
		}
	}
	
	public abstract String formatDate(Date date);
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static DcMessageTimeEnum getEnum(Date date) {
		if (date == null) {
			return UNKNOW;
		}
		long minute = DateUtils.getDiffInMinute(new Date(), date);
		if (minute <= 10) {
			return JUST;
		}
		if (minute <= 20) {
			return MINUTE;
		}
		if (DateUtils.isSameDay(date, new Date())) {
			return TODAY;
		}
		return SOMEDAY;
	}
}
