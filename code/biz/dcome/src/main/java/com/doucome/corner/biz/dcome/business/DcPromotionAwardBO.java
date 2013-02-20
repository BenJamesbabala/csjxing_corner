package com.doucome.corner.biz.dcome.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.UUIDUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionAwardCondition;
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
import com.doucome.corner.biz.dcome.enums.DcShareObjectEnum;
import com.doucome.corner.biz.dcome.enums.DcShareStatusEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.param.DcShareModel;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcPromotionAwardService;
import com.doucome.corner.biz.dcome.service.DcPromotionItemService;
import com.doucome.corner.biz.dcome.service.DcPromotionService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.common.model.ResultModel;

/**
 * 
 * @author ze2200
 *
 */
public class DcPromotionAwardBO {
	
	@Autowired
	private DcPromotionService dcPromotionService;
	
	@Autowired
	private DcPromotionAwardService dcPromotionAwardService;
	
	@Autowired
	private DcItemService dcItemService;
	
	@Autowired
	private DcPromotionItemService dcPromotionItemService ;
	
	@Autowired
	private DcUserIntegralOperateBO dcUserIntegralOperateBO ;
	
	private static final Log logger = LogFactory.getLog(DcPromotionAwardBO.class);
	
    private static final Log        calculateRateLog = LogFactory.getLog(LogConstant.calculate_rate_log);
    
    private static final int        AWARD_SIZE       = 10;
	
	/**
	 * 获取某天的中奖数据
	 * @param date
	 * @return
	 */
	public List<DcPromotionAwardDTO> getPromAwardsByDate(Date date) {
		DcPromotionDTO promotion = dcPromotionService.getPromotionByDate(date);
		if (promotion == null || promotion.isOnGoing() ) {
			return new ArrayList<DcPromotionAwardDTO>();
		}
		DcPromotionAwardCondition condition = new DcPromotionAwardCondition();
		condition.setPromotionType(DcPromotionTypeEnum.PK.getType());
		condition.setPromotionId(promotion.getId());
		condition.setReviewStatus(DcPromotionAwardReviewStatusEnums.SUCCESS.getStatus());
		condition.setOrder("RATE_COUNT");
		List<DcPromotionAwardDTO> awards = dcPromotionAwardService.getAwardsNoPagination(condition, new Pagination(1, 10));
		//22点开奖后，如果还未审批，需要查询未审批状态的数据
		if (CollectionUtils.isEmpty(awards) && isLatestFinishPromDate(date)) {
			condition.setReviewStatus(DcPromotionAwardReviewStatusEnums.UNVIEW.getStatus());
			awards = dcPromotionAwardService.getAwardsNoPagination(condition, new Pagination(1, 5));
		}
		fillAwardItems(awards);
		
		return awards;
	}
	
	/**
	 * 获取中奖纪录.
	 * @param condition
	 * @param page
	 * @return
	 */
	public List<DcPromotionAwardDTO> getAwardDetails(DcPromotionAwardCondition condition, Pagination page) {
		try {
			List<DcPromotionAwardDTO> awards = dcPromotionAwardService.getAwardsNoPagination(condition, page);
			fillAwardItems(awards);
			return awards;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	/**
	 * 用户是否获过奖.
	 * @param userId
	 * @return
	 */
	public boolean isUserWonAward(Long userId) {
		DcPromotionAwardCondition condition = new DcPromotionAwardCondition();
		condition.setUserId(userId);
		condition.setReviewStatus(DcPromotionAwardReviewStatusEnums.SUCCESS.getStatus());
		List<DcPromotionAwardDTO> awards = dcPromotionAwardService.getAwardsNoPagination(condition, new Pagination(1, 1));
		return CollectionUtils.isNotEmpty(awards);
	}
	
	private boolean isLatestFinishPromDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int curDay = calendar.get(Calendar.DAY_OF_YEAR);
		int day = tempCal.get(Calendar.DAY_OF_YEAR);
		if (hour >= 22) {
			return curDay == day;
		} else {
			return curDay == day + 1;
		}
	}
	
	private boolean fillAwardItems(List<DcPromotionAwardDTO> awards) {
		List<Long> itemIds = new ArrayList<Long>();
		for (DcPromotionAwardDTO temp: awards) {
			itemIds.add(temp.getItemId());
		}
		List<DcItemDTO> items = dcItemService.getItemsByIds(itemIds);
		for (DcPromotionAwardDTO temp: awards) {
			for (DcItemDTO item: items) {
				if (temp.getItemId().equals(item.getId())) {
					temp.setDcItemDTO(item);
				}
			}
		}
		return true;
	}
	
	public void calculateRate() {
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
	
	/**
	 * 领奖
	 * @param user
	 * @param awardId
	 * @param inviteeOpenIds
	 * @return
	 */
	public ResultModel<String> shareAward(DcUserDTO user, Long awardId, String inviteeOpenIds) {
		ResultModel<String> result = new ResultModel<String>();
		try {
			String[] tempOpenIds = ArrayStringUtils.toArray(inviteeOpenIds);
			int count = dcPromotionAwardService.updateShareStatus(awardId, user.getUserId(), DcShareStatusEnum.YES);
			if (count == 1) {
				DcShareModel model = new DcShareModel();
				model.setUserId(user.getUserId());
				model.setUserNick(user.getNick());
				model.setShareObjectId(awardId);
				model.setShareObject(DcShareObjectEnum.AWARD);
				model.setOtherInfo(inviteeOpenIds);
				dcUserIntegralOperateBO.doShare(user , model);
				result.setSuccess(JsonModel.CODE_SUCCESS, "success");
				return result;
			} else {
				result.setFail(JsonModel.CODE_FAIL, "no.award");
				return result;
			}
		} catch(Exception e) {
			logger.error(e);
			result.setFail(JsonModel.CODE_FAIL, "internal.error");
			return result;
		}
	}
	
	/**
	 * 获取奖品派发地址信息.
	 * @param awardId
	 * @param userId
	 * @return
	 */
	public ResultModel<DcPromotionAwardDTO> queryAwardAddr(Long awardId, Long userId) {
		ResultModel<DcPromotionAwardDTO> result = new ResultModel<DcPromotionAwardDTO>();
		List<DcPromotionAwardDTO> awards = dcPromotionAwardService.getAwardByUserId(userId);
		if (CollectionUtils.isEmpty(awards)) {
			result.setFail(ResultModel.CODE_FAIL, "no.award");
			return result;
		}
		for (DcPromotionAwardDTO award: awards) {
			if (award.getId().equals(awardId)) {
				result.setSuccess(ResultModel.CODE_SUCCESS, award);
				return result;
			}
		}
		result.setFail(ResultModel.CODE_FAIL, "no.award");
		return result;
	}
}
