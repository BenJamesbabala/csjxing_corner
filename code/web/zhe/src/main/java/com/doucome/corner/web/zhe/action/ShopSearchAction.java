package com.doucome.corner.web.zhe.action;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.zhe.model.DdzBrandPartnerDTO;
import com.doucome.corner.biz.zhe.model.TaobaokeShopFacade;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.biz.zhe.service.DdzBrandPartnerService;
import com.doucome.corner.biz.zhe.service.DdzTaobaokeService;

@SuppressWarnings("serial")
public class ShopSearchAction extends DdzBasicAction {

	@Autowired
	private DdzTaobaokeService ddzTaobaokeService ;
	
	@Autowired
    private DdzAccountService         ddzAccountService;
	
	@Autowired
	private DdzBrandPartnerService ddzBrandPartnerService ;
	
	/**
	 * alipayID
	 */
	private String alipayId ;
	
	/**
	 * 店铺ID
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
        
        //查询合作商铺
		Map<String,DdzBrandPartnerDTO> partnerMap = ddzBrandPartnerService.getBrandPartnerMap();
        
        TaobaokeShopFacade facade = ddzTaobaokeService.conventShop(sid, outCode) ;
        
        if(facade == null || StringUtils.isBlank(facade.getClickUrl())){
        	return NONE ;
        }
        
        //对于合作的商铺，替换默认属性
		
		if(partnerMap.containsKey(sid)){
			DdzBrandPartnerDTO partner = partnerMap.get(sid) ;
			facade.setBrandPartner(partner) ;
		}
		
        
        String clickUrl = facade.getUserClickUrl() ;
		
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
