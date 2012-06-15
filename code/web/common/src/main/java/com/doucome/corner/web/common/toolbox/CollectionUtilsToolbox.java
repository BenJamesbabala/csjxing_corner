package com.doucome.corner.web.common.toolbox;

import java.util.Collection;

import org.springframework.util.CollectionUtils;

public class CollectionUtilsToolbox {

	public boolean isEmpty(Collection c){
		return CollectionUtils.isEmpty(c);
	}
}
