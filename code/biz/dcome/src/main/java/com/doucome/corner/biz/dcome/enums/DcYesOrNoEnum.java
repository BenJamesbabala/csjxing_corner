package com.doucome.corner.biz.dcome.enums;

import org.apache.commons.lang.StringUtils;

/**
 * ∂πﬁ¢∑÷œÌ√∂æŸ
 * @author ze2200
 *
 */
public enum DcYesOrNoEnum {
	YES("Y") {
		@Override
		public String getTBPostalPayer() {
			return "seller";
		}
	},
	NO("N") {
		@Override
		public String getTBPostalPayer() {
			return "buyer";
		}
	},
	UNKNOWN("") {
		@Override
		public String getTBPostalPayer() {
			throw new RuntimeException("invalid.operation");
		}
	};
	
	private String value;
	
	private DcYesOrNoEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public abstract String getTBPostalPayer();
	
	public static DcYesOrNoEnum toEnum(String value) {
		if (StringUtils.isEmpty(value)) {
			return UNKNOWN;
		}
		try {
			for (DcYesOrNoEnum temp: DcYesOrNoEnum.values()) {
				if (temp.getValue().equalsIgnoreCase(value)) {
					return temp;
				}
			}
			return Enum.valueOf(DcYesOrNoEnum.class, value);
		} catch (Exception e) {
			return UNKNOWN;
		}
	}
}
