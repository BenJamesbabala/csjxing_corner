package com.doucome.corner.web.bops.action;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.utils.OutCodeUtils;

@SuppressWarnings("serial")
public class RedirectTaobaoItemAction extends BopsBasicAction {

	private String outCode ;
	
	private String numIid ;
	
	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator ;
	
	@Override
	public String execute() throws Exception {
		
		if(StringUtils.isEmpty(numIid) || !StringUtils.isNumeric(numIid)) {
			return INPUT ;
		}
		
        outCode = StringUtils.substring(outCode, 0 , 11) ;
        
        String outCodeStr = OutCodeUtils.encodeOutCode(outCode, OutCodeEnums.SYSTEM) ;
        
        TaobaokeItemDTO dto = taobaokeServiceDecorator.widgetConventItem(numIid, outCodeStr ,TaobaokeFields.taoke_item_fields) ;
        
        String detailUrl ;
        
        if(dto == null) {
        	detailUrl = "http://item.taobao.com/item.htm?id=" + numIid ;
        } else {
        	detailUrl = dto.getClickUrl() ;
        }
        
		redirect(detailUrl) ;
		
		return SUCCESS ;
	}

	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}

	public void setNumIid(String numIid) {
		this.numIid = numIid;
	}
	
	
}
