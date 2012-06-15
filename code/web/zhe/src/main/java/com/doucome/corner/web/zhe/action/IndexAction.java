package com.doucome.corner.web.zhe.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.zhe.buyingRecomm.BuyingRecommendService;
import com.doucome.corner.biz.zhe.vo.BuyingRecommendVO;
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
	
	private List<BuyingRecommendVO> buyingRecommendList ;
	
    @Autowired
    private BuyingRecommendService buyingRecommendService ;
	
    @Override
    public String execute() throws Exception {
        
    	buyingRecommendList = buyingRecommendService.getBuyingItems(18) ;
    	
        return SUCCESS;
    }
    
	public GuideVar getGuideVar() {
		return guideVar;
	}

	public List<BuyingRecommendVO> getBuyingRecommendList() {
		return buyingRecommendList;
	}

    
}
