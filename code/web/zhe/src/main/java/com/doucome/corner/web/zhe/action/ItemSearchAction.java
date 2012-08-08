package com.doucome.corner.web.zhe.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.service.ShortUrlService;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.core.utils.ValidateUtil;
import com.doucome.corner.biz.dal.dataobject.DdzSearchLogDO;
import com.doucome.corner.biz.zhe.enums.SearchWayEnums;
import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.biz.zhe.service.DdzSearchLogService;
import com.doucome.corner.biz.zhe.service.DdzTaobaokeService;
import com.doucome.corner.web.common.constant.CookieConstants;
import com.doucome.corner.web.common.cookie.CookieHelper;
import com.doucome.corner.web.zhe.authz.DdzSessionOperator;
import com.doucome.corner.web.zhe.model.GuideVar;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 单商品查询
 * 
 * @author shenjia.caosj 2012-3-17
 */
@SuppressWarnings("serial")
public class ItemSearchAction extends DdzBasicAction implements ModelDriven<TaobaokeItemFacade> {

    @Autowired
    private ShortUrlService           shortUrlService;

    @Autowired
    private DdzAccountService         ddzAccountService;

    @Autowired
    private DdzSearchLogService       ddzSearchLogService;

    @Autowired
    private DdzSessionOperator        ddzSessionOperator;
    
    @Autowired
    private DdzTaobaokeService	ddzTaobaokeService ;
    
    private String                    id;

    private TaobaokeItemFacade        item;

    private List<TaobaokeItemFacade>  recommendList;

    private String                    alipayId;

    private String                    accountId;

    private GuideVar                  guideVar = new GuideVar(false, false, false, true, true);

    private boolean                   userGuide;
    
    private boolean 				 isRecommend = false ;
    
    @Override
    public String execute() throws Exception {
        if (StringUtils.isNotBlank(id)) {

            if (!StringUtils.isNumeric(id)) { // 商品ID有问题
                return SUCCESS;
            }
            
            accountId = generateAccountId();
            
            //兼容以前商品推荐代码
            
            if ("vip@diandianzhe.com".equals(alipayId)) {
            	CookieHelper.writeCookie(getResponse(), CookieConstants.DDZ_DEFAULT_DOMAIN, "__ddz_y_id", null,
                        CookieConstants.EXPIRY_TIME_YEAR);
            	alipayId = null;
            }
            
            //推荐过来，但是有支付宝
            if(isRecommend && StringUtils.isNotBlank(accountId)){
            	return "redirectItem" ;
            }

            String outCode = OutCodeUtils.encodeOutCode(accountId, OutCodeEnums.DDZ_ACCOUNT_ID);

            
            item = ddzTaobaokeService.conventItem(id, outCode) ;
            
            if(item == null){ //木有折扣或者item不存在
            	//查询商品信息
            	try {
            		item = ddzTaobaokeService.getTaobaoItem(id) ;
            	}catch(TaobaoRemoteException e){
            		if(StringUtils.equals(e.getErrCode(),TaobaoRemoteException.ERR_ITEM_NOT_FOUND)){
            			//item不存在
            			id = null ;
            			return SUCCESS ;
            		}
            	}
            }
            
            if (item != null) {
            	
            	if(item.getCommission() != null){// 生成短链
            		String clickUrlShorten = shortUrlService.insertUrl(item.getClickUrl());
                    item.setClickUrlShorten(clickUrlShorten);
            	}
            	
            	//记录日志
            	dblog() ;
            }

        }
        
        return SUCCESS;
    }
    
    private void dblog(){
    	// 记录日志
        DdzSearchLogDO searchLog = new DdzSearchLogDO();
        searchLog.setAlipayId(alipayId);
        searchLog.setSearchBrief(id);
        searchLog.setCommission(item.getCommission());
        searchLog.setCommissionRate(item.getCommissionRate());
        searchLog.setPrice(item.getPrice());
        searchLog.setSearchTitle(item.getTitle()) ;
        searchLog.setSearchWay(SearchWayEnums.ITEM.getValue());
        ddzSearchLogService.createLog(searchLog);
    }

    public String generateAccountId() {
        if (StringUtils.isBlank(alipayId)) {
            // get from cookie
            alipayId = ddzAuthz.getAlipayId();
        }

        // validate alipayId
        if (!ValidateUtil.checkIsEmail(alipayId) && !ValidateUtil.checkIsMobile(alipayId)) {
            alipayId = null;
        }

        // update to cookie
        if (!StringUtils.equals(alipayId, ddzAuthz.getAlipayId())) {
            ddzSessionOperator.setAlipayId(alipayId);
        }

        if (StringUtils.isBlank(accountId) && StringUtils.isNotBlank(alipayId)) {
            accountId = ddzAccountService.getAccountIdByAlipayId(alipayId);
        }
        return accountId;
    }

    @Override
    public TaobaokeItemFacade getModel() {
        return item;
    }

    /**
     * ------------------------------------------------------------------------------------
     */

    public String getAlipayId() {
        return alipayId;
    }

    public boolean isUserGuide() {
        return userGuide;
    }

    public void setUserGuide(boolean userGuide) {
        this.userGuide = userGuide;
    }

    public List<TaobaokeItemFacade> getRecommendList() {
        return recommendList;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TaobaokeItemFacade getItem() {
        return item;
    }

    public void setItem(TaobaokeItemFacade item) {
        this.item = item;
    }

    public GuideVar getGuideVar() {
        return guideVar;
    }

	public void setIsRecommend(boolean isRecommend) {
		this.isRecommend = isRecommend;
	}
 
    
}
