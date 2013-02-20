package com.doucome.corner.biz.dcome.model.star;

import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * AHsFateDTO
 * @author 
 *
 */
public abstract class AHsFateDTO extends AbstractModel {
	
	private static final long serialVersionUID = 1L;
	
	protected Integer getValidIndexValue(Integer indexValue) {
		Integer temp = indexValue > 100? 100: indexValue;
		temp = temp < 0? 0: temp;
		return temp;
	}
}
