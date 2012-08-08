package com.doucome.corner.biz.dal.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


public class AbstractModel implements Serializable {
	
	private static final long serialVersionUID = 1279724549267632658L;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this ,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
