package com.doucome.corner.biz.core.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class AbstractModel {

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this ,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
