package com.doucome.corner.web.zhe.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.zhe.model.TaobaokeReportFacade;
import com.doucome.corner.biz.zhe.model.TaobaokeShopFacade;
import com.doucome.corner.biz.zhe.service.DdzRecommendService;
import com.doucome.corner.web.zhe.model.GuideVar;

/**
 * Ê×Ò³
 * 
 * @author shenjia.caosj 2012-2-24
 */
@SuppressWarnings("serial")
public class IndexAction extends DdzBasicAction {

//	@Autowired
//    private DdzAccountService        ddzAccountService;

	private GuideVar guideVar = new GuideVar(true , true , true , false , false ) ;
	
	private List<TaobaokeReportFacade> buyingRecommendList ;
	
	private Map<String,TaobaokeShopFacade> brandsMap ;
	
	private List<TaobaokeShopFacade> brandsList ;
	
    @Autowired
    private DdzRecommendService ddzRecommendService ;
	
    @Override
    public String execute() throws Exception {
        
    	buyingRecommendList = ddzRecommendService.getBuyingItems(18) ;
    	
    	this.brandsList = ddzRecommendService.getIndexBrands() ;
    	//this.brandsMap = brandsList2Map(brandsList) ;
    	
        return SUCCESS;
    }
    
	public GuideVar getGuideVar() {
		return guideVar;
	}

	public List<TaobaokeReportFacade> getBuyingRecommendList() {
		return buyingRecommendList;
	}

	public Map<String, TaobaokeShopFacade> getBrandsMap() {
		return brandsMap;
	}
	
	public List<TaobaokeShopFacade> getBrandsList() {
		return brandsList;
	}

	private Map<String,TaobaokeShopFacade> brandsList2Map(List<TaobaokeShopFacade> list){
		if(list == null){
			return null ;
		}
		
		Map<String,TaobaokeShopFacade> m = new HashMap<String,TaobaokeShopFacade>() ;
		for(TaobaokeShopFacade shop : list){
			m.put(shop.getSid()	, shop) ;
		}
		
		return m ;
	}
    
}
