package com.doucome.corner.task.zhe.promotion;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.utils.UUIDUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionItemSearchCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionItemSearchCondition.OrderEnum;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionAwardDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionDO;
import com.doucome.corner.biz.dcome.enums.DcItemTypeEnum;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardReviewStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardSendStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionStatusEnum;
import com.doucome.corner.biz.dcome.enums.DcPromotionTypeEnum;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionAwardService;
import com.doucome.corner.biz.dcome.service.DcPromotionItemService;
import com.doucome.corner.biz.dcome.service.DcPromotionService;

/**
 * 类CalculateRateTask.java的实现描述：投票开奖任务
 * 
 * @author ib 2012-9-3 下午11:19:18
 */
public class CalculateRateTask {

    private static final Log        logger           = LogFactory.getLog(CalculateRateTask.class);
    private static final Log        calculateRateLog = LogFactory.getLog(LogConstant.calculate_rate_log);
    private static final int        AWARD_SIZE       = 10;
    private DcPromotionService      dcPromotionService;
    private DcPromotionItemService  dcPromotionItemService;
    private DcPromotionAwardService dcPromotionAwardService;

    public static void main(String[] args) {
        calculateRateLog.info("start to calculate rate.");
        try {

            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            DcPromotionService dcPromotionService = (DcPromotionService) ctx.getBean("dcPromotionService");
            DcPromotionItemService dcPromotionItemService = (DcPromotionItemService) ctx.getBean("dcPromotionItemService");
            DcPromotionAwardService dcPromotionAwardService = (DcPromotionAwardService) ctx.getBean("dcPromotionAwardService");
            CalculateRateTask task = new CalculateRateTask();
            task.setDcPromotionService(dcPromotionService);
            task.setDcPromotionItemService(dcPromotionItemService);
            task.setDcPromotionAwardService(dcPromotionAwardService);
            task.run();
            calculateRateLog.info("calculate rate end.");
        } catch (Exception e) {
            logger.error("calculate rate error", e);
        }
        System.exit(0);
    }

    public void run() {
        Pagination pagination = new Pagination(1, 1000);
        DcPromotionSearchCondition searchCondition = new DcPromotionSearchCondition();
        searchCondition.setOpenTime(new Date());
        searchCondition.setPromType(DcPromotionTypeEnum.PK.getType());
        searchCondition.setStatus(DcPromotionStatusEnum.NORMAL.getValue());
        List<DcPromotionDTO> promotionList = dcPromotionService.getPromotionsNoPagination(searchCondition, pagination);
        for (DcPromotionDTO dcPromotionDTO : promotionList) {
            try {
                Pagination itemPagination = new Pagination(1, AWARD_SIZE);
                DcPromotionItemSearchCondition itemSearch = new DcPromotionItemSearchCondition();
                itemSearch.setOrder(OrderEnum.rate_count);
                itemSearch.setPromotionId(dcPromotionDTO.getId());
                List<DcPromotionItemDTO> itemList = dcPromotionItemService.getPromotionItemsFromDB(itemSearch,
                                                                                                   itemPagination);
                for (int i = 0; i < itemList.size(); i++) {
                    Long id = null;
                    try {
                        DcPromotionItemDTO item = itemList.get(i);
                        DcPromotionAwardDO award = new DcPromotionAwardDO();
                        award.setItemId(item.getItemId());
                        award.setItemType(DcItemTypeEnum.NORMAL.getItemType());
                        award.setPromotionId(dcPromotionDTO.getId());
                        id = item.getId();
                        award.setPromotionItemId(item.getId());
                        award.setRateCount(item.getRateCount());
                        award.setUserId(item.getUserId());
                        award.setUserNick(item.getUserNick());
                        award.setSendStatus(DcPromotionAwardSendStatusEnums.UNSEND.getStatus());
                        award.setPromotionType(dcPromotionDTO.getPromType());
                        award.setReviewStatus(DcPromotionAwardReviewStatusEnums.UNVIEW.getStatus());
                        award.setCheckCode(UUIDUtils.random20Num());
                        dcPromotionAwardService.createAward(award);
                        calculateRateLog.info("crate award success,item promotion id:" + id);
                    } catch (Exception e) {
                        calculateRateLog.error("crate award error,item promotion id:" + id, e);
                    }
                }
                
                DcPromotionDO dcPromotionDO = dcPromotionDTO.getPromotion();
                dcPromotionDO.setStatus(DcPromotionStatusEnum.ENDING.getValue());
                dcPromotionService.updatePromotion(dcPromotionDTO);
                calculateRateLog.info("calculate promotion success,promotion id:" + dcPromotionDTO.getId());
            } catch (Exception e) {
                calculateRateLog.error("calculate promotion error,item promotion id:" + dcPromotionDTO.getId(), e);
            }
        }
    }

    public void setDcPromotionService(DcPromotionService dcPromotionService) {
        this.dcPromotionService = dcPromotionService;
    }

    public void setDcPromotionItemService(DcPromotionItemService dcPromotionItemService) {
        this.dcPromotionItemService = dcPromotionItemService;
    }

    public void setDcPromotionAwardService(DcPromotionAwardService dcPromotionAwardService) {
        this.dcPromotionAwardService = dcPromotionAwardService;
    }

}
