package com.doucome.corner.web.zhe.action;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.zhe.enums.OutCodeEnums;
import com.doucome.corner.biz.zhe.model.TaobaokeShopFacade;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.biz.zhe.service.DdzTaobaokeService;
import com.doucome.corner.biz.zhe.utils.OutCodeUtils;

@SuppressWarnings("serial")
public class ShopSearchAction extends DdzBasicAction {

	@Autowired
	private DdzTaobaokeService ddzTaobaokeService ;
	
	@Autowired
    private DdzAccountService         ddzAccountService;
	
	/**
	 * alipayID
	 */
	private String alipayId ;
	
	/**
	 * µÍ∆ÃID
	 */
	private String sid ;
	
	@Override
	public String execute() throws Exception {
		
		if(StringUtils.isBlank(alipayId)){
			this.alipayId = ddzAuthz.getAlipayId(); 
		}
				
		String accountId = null ;
        if (StringUtils.isNotBlank(alipayId)) {
            accountId = ddzAccountService.getAccountIdByAlipayId(alipayId);
        }
		
        String outCode = accountId == null ? null : OutCodeUtils.encodeOutCode(accountId, OutCodeEnums.DDZ_ACCOUNT_ID);
        
        TaobaokeShopFacade facade = ddzTaobaokeService.conventShop(sid, outCode) ;
        
        if(facade == null || StringUtils.isBlank(facade.getClickUrl())){
        	return NONE ;
        }
        
        String clickUrl = facade.getClickUrl() ;
		
        //redirect to clickUrl 
        redirect(clickUrl) ;
        
		return SUCCESS ;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}

	
}
