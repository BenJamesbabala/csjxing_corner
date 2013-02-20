package com.doucome.corner.web.bops.action.dcome.prom;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.dcome.enums.DcItemSourceEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcTaobaoService;
import com.doucome.corner.web.bops.action.BopsBasicAction;

public class RedirectItemAction extends BopsBasicAction{

	private static final String taobao_url = "http://item.taobao.com/item.htm?id=" ;
	
	private Long itemId ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Autowired
	private DcTaobaoService dcTaobaoService ;
	
	@Override
	public String execute() throws Exception {
		
		DcItemDTO item = dcItemService.getItemById(itemId) ;
		
		if(item == null){
			return INPUT ;
		}
		
		//现在只有淘宝商品
		DcItemSourceEnum source = DcItemSourceEnum.getItemSource(item.getSource());
		
		//淘宝商品ID
		String nativeItemId = item.getNativeId() ;
		String outCode = OutCodeUtils.encodeOutCode("redirect", OutCodeEnums.SYSTEM) ;
		TaobaokeItemDTO dto = dcTaobaoService.conventItem(nativeItemId, outCode) ;
		
		String url = null ;
		
		if(dto == null){
			url = taobao_url + item.getNativeId() ;
			
		} else {
			url = dto.getClickUrl() ;
		}
		
		redirect(url) ;
		
		return SUCCESS ;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

}
