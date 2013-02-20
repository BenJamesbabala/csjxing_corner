package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.dal.dataobject.dcome.DcAutoExchangeDO;
import com.doucome.corner.biz.dcome.model.param.DcAutoExchangeModel;

/**
 * itemService
 * @author langben 2012-7-22
 *
 */
public interface DcAutoExchangeService {
	/**
	 * 
	 * @param autoExchangeDO
	 * @return
	 */
	Long insertAutoExchange(DcAutoExchangeDO autoExchangeDO);
	/**
	 * 
	 * @param exchangeModel
	 * @return
	 */
	Long createAutoExchange(DcAutoExchangeModel exchangeModel);
}
