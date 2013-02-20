package com.doucome.corner.biz.core.enums;

import java.util.HashMap;
import java.util.Map;

public enum GenderEnums {

	FEMALE("F"), MALE("M"), UNKNOW("");

	private GenderEnums(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return this.value;
	}

	private static final Map<String, GenderEnums> map = new HashMap<String, GenderEnums>();
	static {
		GenderEnums[] enums = GenderEnums.values();
		for (GenderEnums e : enums) {
			map.put(e.getValue(), e);
		}
	}

	public static GenderEnums get(String key) {
		return map.get(key);
	}
}
