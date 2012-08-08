package com.doucome.corner.biz.dcome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.service.taobao.TaobaoServiceDecorator;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaoFields;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.dcome.service.DcTaobaokeService;

public class DcTaobaokeServiceImpl implements DcTaobaokeService {
	
	@Autowired
	TaobaokeServiceDecorator dcomeTaobaokeServiceDecorator ;
	
	@Autowired
	TaobaoServiceDecorator taobaoServiceDecorator ;

	@Override
	public TaobaokeItemDTO conventItem(String itemId, String outCode) throws TaobaoRemoteException {
		return dcomeTaobaokeServiceDecorator.conventItem(itemId, outCode, TaobaokeFields.taoke_item_fields) ;
	}

	@Override
	public TaobaoItemDTO getTaobaoItem(String itemId) throws TaobaoRemoteException {
		return taobaoServiceDecorator.getItem(Long.valueOf(itemId), TaobaoFields.taobao_item_fields_full) ;
	}
	
	@Override
	public TaobaoItemDTO getTaobaoItem(String itemId, String[] fields) throws TaobaoRemoteException {
		return taobaoServiceDecorator.getItem(Long.valueOf(itemId), fields) ;
	}
}
