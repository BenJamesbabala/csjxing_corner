package com.doucome.corner.biz.dcome.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.service.taobao.TaobaoRecommandDecorator;
import com.doucome.corner.biz.core.service.taobao.TaobaoServiceDecorator;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoPromotionDisplayDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoPromotionInItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaoFields;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.dcome.service.DcTaobaoService;

public class DcTaobaoServiceImpl implements DcTaobaoService {
	
	@Autowired
	TaobaokeServiceDecorator taobaokeServiceDecorator ;
	
	@Autowired
	TaobaoServiceDecorator taobaoServiceDecorator ;
	
	@Autowired
	TaobaoRecommandDecorator taobaoRecommandDecorator ;
	
	
	@Override
	public TaobaokeItemDTO conventItem(String tbItemId, String outCode) throws TaobaoRemoteException {

		TaobaokeItemDTO taokeItem = taobaokeServiceDecorator.widgetConventItem(tbItemId, outCode, TaobaokeFields.taoke_item_fields) ;

		return taokeItem;
	}

	@Override
	public TaobaoItemDTO getTaobaoItem(String tbItemId) throws TaobaoRemoteException {
		TaobaoItemDTO tbItem = taobaoServiceDecorator.getItem(Long.valueOf(tbItemId), TaobaoFields.taobao_item_fields_full) ;
		return tbItem ;
	}

	@Override
	public List<TaobaokeItemDTO> convertItems(String[] tbItemIds, String outCode) throws TaobaoRemoteException {
		List<TaobaokeItemDTO> taokeItemList = taobaokeServiceDecorator.widgetConventItems(tbItemIds, outCode, TaobaokeFields.taoke_item_fields) ;
		return taokeItemList ;
	}

	@Override
	public List<TaobaoItemDTO> getTaobaoItems(String[] tbItemIds)
			throws TaobaoRemoteException {
		List<TaobaoItemDTO> taobaoItemList = taobaoServiceDecorator.getListItems(tbItemIds, TaobaoFields.taobao_item_fields_full) ;
		return taobaoItemList ;
	}

	@Override
	public BigDecimal getItemPromoPrice(Long tbItemId) throws TaobaoRemoteException {
		
		TaobaoPromotionDisplayDTO promotionDisplay = taobaoRecommandDecorator.getPromotionUmp(tbItemId, null) ;
		
		if( promotionDisplay == null){
			return null ;
		}
		
		TaobaoPromotionInItemDTO dto = promotionDisplay.getPromotionInItem() ;
		if(dto == null) {
			return null;
		}
		
		return dto.getItemPromoPrice() ;
	}
	
}
