package com.doucome.corner.web.bops.action.zhe;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.model.URLModel;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.utils.HttpUrlHelper;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.common.utils.TaobaoUrlUtils;

public class TaokeConvertAction extends BopsBasicAction {

	private String wd ;
	
	private String isMobileCheck ;
	
	private String outCode ;
	
	private TaobaokeItemDTO itemDTO ;
	
	private Boolean noCommission  ;
	
	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator ; 
	
	@Override
	public String execute() throws Exception {
		
		URLModel model = HttpUrlHelper.parseURL(wd);
        if (model != null) {
            // taobao url
           String itemId = TaobaoUrlUtils.parseItemId(model);
           Boolean isMobile = null ;
           
           if(StringUtils.equals(isMobileCheck,"on")){
        	   isMobile = Boolean.TRUE ;
           }
           outCode = StringUtils.substring(outCode, 0 , 11) ;
           
           String outCodeStr = OutCodeUtils.encodeOutCode(outCode, OutCodeEnums.SYSTEM) ;
           
           itemDTO = taobaokeServiceDecorator.widgetConventItem(itemId, outCodeStr, isMobile ,TaobaokeFields.taoke_item_fields) ;
           
           if(itemDTO == null){
        	   noCommission = true ;
           }
		
        }
		return SUCCESS ;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public String getIsMobileCheck() {
		return isMobileCheck;
	}

	public void setIsMobileCheck(String isMobileCheck) {
		this.isMobileCheck = isMobileCheck;
	}

	public TaobaokeItemDTO getItemDTO() {
		return itemDTO;
	}

	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}

	public Boolean getNoCommission() {
		return noCommission;
	}

	public String getWd() {
		return wd;
	}

	public String getOutCode() {
		return outCode;
	}
	
}
