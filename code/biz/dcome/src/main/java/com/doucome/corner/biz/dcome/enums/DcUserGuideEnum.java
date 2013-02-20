package com.doucome.corner.biz.dcome.enums;

import com.doucome.corner.biz.dcome.exception.DcUnSupportOperationException;
import com.doucome.corner.biz.dcome.model.DcUserDTO;

/**
 * 
 * 
 * @author ze2200
 */
public enum DcUserGuideEnum {
	
	UGC_LAYER("ugc", 1l) {
		@Override
		public DcIntegralSourceEnums getDoneAward() {
			return DcIntegralSourceEnums.UGC_ITEM;
		}

		@Override
		public boolean isShowFLayer(DcUserDTO user) {
			if (isDone(user.getNewGuide())) {
				return false;
			}
			
			return false;
		}
	},
	NOTIFICATE("noti", 2l) {
		@Override
		public DcIntegralSourceEnums getDoneAward() {
			return null;
		}

		@Override
		public boolean isShowFLayer(DcUserDTO user) {
			return !isDone(user.getNewGuide());
		}
	},
	UGC_SEARCH("ugc_s", 4L) {

		@Override
		public DcIntegralSourceEnums getDoneAward() {
			return null;
		}

		@Override
		public boolean isShowFLayer(DcUserDTO user) {
			return false;
		}
		
	},
	EX_SEARCH("ex_s", 8l) {
		@Override
		public DcIntegralSourceEnums getDoneAward() {
			return null;
		}

		@Override
		public boolean isShowFLayer(DcUserDTO user) {
			return false;
		}
	},
	
	INDEX_SEARCH("idx_sch" , 32L) {

		@Override
		public DcIntegralSourceEnums getDoneAward() {
			return null;
		}

		@Override
		public boolean isShowFLayer(DcUserDTO user) {
			return false;
		}
		
	} , 
	
	UNKNOW("", 0l){
		@Override
		public DcIntegralSourceEnums getDoneAward() {
			throw new DcUnSupportOperationException("this is unknow enum");
		}

		@Override
		public boolean isShowFLayer(DcUserDTO user) {
			throw new DcUnSupportOperationException("this is unknow enum");
		}
	};
	
	private DcUserGuideEnum(String guideStr, long value) {
		this.guideStr = guideStr;
		this.defaultValue = value;
	}
	
	private String guideStr;
	
	private long defaultValue;
	
	/**
	 * 
	 * @return
	 */
	public long getDefaultValue() {
		return this.defaultValue;
	}
	
	public String getGuideStr() {
		return this.guideStr;
	}
	
	public long getDoneValue(long oldValue) {
		return oldValue | getDefaultValue();
	}
	
	public abstract DcIntegralSourceEnums getDoneAward();
	
	public abstract boolean isShowFLayer(DcUserDTO user);
	
	/**
	 * 
	 * @param guideEnum
	 * @param guideValue
	 * @return
	 */
	public boolean isDone(long guideValue) {
		return (getDefaultValue() & guideValue) > 0;
	}
	
	public static DcUserGuideEnum toEnum(String guideStr) {
		for (DcUserGuideEnum guide: DcUserGuideEnum.values()) {
			if (guide.getGuideStr().equals(guideStr)) {
				return guide;
			}
		}
		return UNKNOW;
	}
}
