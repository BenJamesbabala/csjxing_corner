package com.doucome.corner.biz.dcome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcAutoExchangeDO;
import com.doucome.corner.biz.dal.dcome.DcAutoExchangeDAO;
import com.doucome.corner.biz.dcome.model.param.DcAutoExchangeModel;
import com.doucome.corner.biz.dcome.service.DcAutoExchangeService;

public class DcAutoExchangeServiceImpl implements DcAutoExchangeService {
	@Autowired
	private DcAutoExchangeDAO dcAutoExchangeDAO;

	@Override
	public Long insertAutoExchange(DcAutoExchangeDO autoExchangeDO) {
		return dcAutoExchangeDAO.insertAutoExchange(autoExchangeDO);
	}
	

	@Override
	public Long createAutoExchange(DcAutoExchangeModel exchangeModel) {
		DcAutoExchangeDO exchangeDO = new DcAutoExchangeDO();
		exchangeDO.setNativeId(exchangeModel.getItemNativeId());
		exchangeDO.setItemSize(exchangeModel.getItemSize());
		exchangeDO.setItemColor(exchangeModel.getItemColor());
		exchangeDO.setPrice(exchangeModel.getItemPrice());
		exchangeDO.setPostalFee(exchangeModel.getPostalFee());
		exchangeDO.setUserId(exchangeModel.getUserId());
		exchangeDO.setUserNick(exchangeModel.getUserNick());
		exchangeDO.setMemo(exchangeModel.getMemo());
		return dcAutoExchangeDAO.insertAutoExchange(exchangeDO);
	}
	
}
