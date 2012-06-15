package com.doucome.corner.web.zhe.action;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.service.ShortUrlService;
import com.doucome.corner.biz.core.service.taobao.TaobaoRecommandDecorator;
import com.doucome.corner.biz.core.service.taobao.TaobaoServiceDecorator;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaoFields;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.utils.ValidateUtil;
import com.doucome.corner.biz.dal.dataobject.DdzSearchLogDO;
import com.doucome.corner.biz.zhe.buyingRecomm.BuyingRecommendService;
import com.doucome.corner.biz.zhe.condition.EatDiscountCondition;
import com.doucome.corner.biz.zhe.enums.OutCodeEnums;
import com.doucome.corner.biz.zhe.enums.SearchWayEnums;
import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.biz.zhe.service.DdzEatDiscountService;
import com.doucome.corner.biz.zhe.service.DdzSearchLogService;
import com.doucome.corner.biz.zhe.service.DdzTaobaoRecommendService;
import com.doucome.corner.biz.zhe.utils.OutCodeUtils;
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

    private static final BigDecimal   ZERO     = new BigDecimal("0.00");

    @Autowired
    private TaobaokeServiceDecorator  taobaokeServiceDecorator;

    @Autowired
    private TaobaoRecommandDecorator  taobaoRecommandDecorator;

    @Autowired
    private DdzEatDiscountService     ddzEatDiscountService;

    @Autowired
    private ShortUrlService           shortUrlService;

    @Autowired
    private DdzAccountService         ddzAccountService;

    @Autowired
    private DdzSearchLogService       ddzSearchLogService;

    @Autowired
    private DdzSessionOperator        ddzSessionOperator;

    @Autowired
    private DdzTaobaoRecommendService ddzTaobaoRecommendService;

    @Autowired
    private TaobaoServiceDecorator    taobaoServiceDecorator;
    

    private String                    id;

    private TaobaokeItemFacade        item;

    private List<TaobaokeItemFacade>  recommendList;

    private String                    alipayId;

    private String                    accountId;

    private GuideVar                  guideVar = new GuideVar(false, false, false, true, true);

    private boolean                   userGuide;

    @Override
    public String execute() throws Exception {
        if (StringUtils.isNotBlank(id)) {

            if (!StringUtils.isNumeric(id)) { // 商品ID有问题
                return SUCCESS;
            }
            
            accountId = generateAccountId();

            String outCode = OutCodeUtils.encodeOutCode(accountId, OutCodeEnums.DDZ_ACCOUNT_ID);

            // 转换商品
            TaobaokeItemDTO internalItem = taobaokeServiceDecorator.conventItem(id, outCode,
                                                                                TaobaokeFields.taoke_item_fields);
            TaobaoItemDTO taobaoItem = null;
            // 木有折扣或者item不存在
            // 查不到商品或者折扣为0时 //查询推荐
            if (internalItem == null) {
                // 查询商品信息
                taobaoItem = taobaoServiceDecorator.getItem(Long.valueOf(id), TaobaoFields.taobao_item_fields_short);
            }

            // 计算用户显示的折扣
            EatDiscountCondition condition = new EatDiscountCondition();
            condition.setNeedPromotionPrice(true);
            if (taobaoItem != null) {
                item = ddzEatDiscountService.eatDiscount(taobaoItem, condition);
            } else {
                item = ddzEatDiscountService.eatDiscount(internalItem, condition);
                if (item != null) { // 生成短链
                    String clickUrlShorten = shortUrlService.insertUrl(item.getClickUrl());
                    item.setClickUrlShorten(clickUrlShorten);
                }
            }

            if (item != null) {

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

        }
        
        

        return SUCCESS;
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

}
