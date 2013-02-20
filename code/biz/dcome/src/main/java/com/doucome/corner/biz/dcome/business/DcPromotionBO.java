package com.doucome.corner.biz.dcome.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.core.utils.UUIDUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionItemSearchCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionSearchCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionItemSearchCondition.OrderEnum;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionAwardDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionItemDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionRateDetailDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionSnapInfoDO;
import com.doucome.corner.biz.dcome.cache.DcPromotionDynamicCache;
import com.doucome.corner.biz.dcome.enums.DcGameEnum;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardReviewStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardSendStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionRateStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionRateTypeEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionStatusEnum;
import com.doucome.corner.biz.dcome.enums.DcPromotionTypeEnum;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.exception.DuplicateOperateException;
import com.doucome.corner.biz.dcome.exception.IntegralNotEnoughException;
import com.doucome.corner.biz.dcome.exception.ObjectNotExistException;
import com.doucome.corner.biz.dcome.exception.PromotionNotExistException;
import com.doucome.corner.biz.dcome.exception.PromotionRateException;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionDynamicDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionSnapInfoDTO;
import com.doucome.corner.biz.dcome.model.facade.DcUserModel;
import com.doucome.corner.biz.dcome.model.facade.PromRateResult;
import com.doucome.corner.biz.dcome.model.param.DcPromItemModel;
import com.doucome.corner.biz.dcome.model.param.DcStealStarModel;
import com.doucome.corner.biz.dcome.model.param.DcUgcItemModel;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcPromotionItemService;
import com.doucome.corner.biz.dcome.service.DcPromotionRateDetailService;
import com.doucome.corner.biz.dcome.service.DcPromotionService;
import com.doucome.corner.biz.dcome.service.DcPromotionSnapInfoService;
import com.doucome.corner.web.common.model.ResultModel;
/**
 * pk活动业务逻辑类.
 * @author ze2200
 *
 */
public class DcPromotionBO {
	@Autowired
	private DcPromotionService dcPromotionService;
	@Autowired
	private DcPromotionItemService dcPromotionItemService;
	@Autowired
	private DcPromotionRateDetailService dcPromotionRateDetailService ;
	@Autowired
	private DcItemBO dcItemBO;
	@Autowired
	private DcItemService dcItemService;
	@Autowired
	private DcUserIntegralBO dcUserIntegralBO ;
	@Autowired
	private DcGameBO dcGameBO;
	@Autowired
	private DcPromotionSnapInfoService dcPromotionSnapInfoService ;
	
	private static final Log logger = LogFactory.getLog(DcPromotionBO.class);

	@Autowired
	private DcPromotionDynamicCache dcPromotionDynamicCache ;
	
	/**
	 * 获取动态
	 * @return
	 */
	public List<DcPromotionDynamicDTO> getDynamic(Long promotionId) {
		List<DcPromotionDynamicDTO> dynamicList = dcPromotionDynamicCache.getCache(promotionId) ;
		return dynamicList;
	}
	
	/**
	 * 添加一个动态
	 * @param dynamic
	 */
	public void addDynamic(Long promotionId ,DcPromotionDynamicDTO dynamic) {
//		if(dynamic == null){
//			return ;
//		}
//		List<DcPromotionDynamicDTO> dynamicList = dcPromotionDynamicCache.getCache(promotionId) ;
//		if(dynamicList == null) {
//			dynamicList = new ArrayList<DcPromotionDynamicDTO>() ;
//		}
//		dynamicList.add(0,dynamic) ;
//		while(dynamicList.size() > 20){ //防止集合太大
//			dynamicList.remove(dynamicList.size() - 1) ;
//		}
//		dcPromotionDynamicCache.setCache(promotionId, dynamicList) ;
	}
	/**
	 * 检验参与活动的商品是否符合要求.
	 * @param promotionId 活动id.
	 * @param tbItemUrl 商品url.
	 * @return code: success, data: null/dc_item.id(已有用户创建过商品)
	 *         code: failed, detail:
	 *                 promotion.ended 活动已结束
	 *                 promotion.not.start 活动未开始
	 *                 takein.repeat(已参与), data: dc_promotion_item.id
	 *                 item.not.exist 商品不存在.
	 *                 itemPrice.more(商品价格过高)
	 *                 internal.error(服务器内部错误)
	 */
	public ResultModel<Long> ugcTakeinPromCheck(DcUgcItemModel ugcInfo) {
		ResultModel<Long> result = new ResultModel<Long>();
		
		try {
			//检验活动自身
			DcPromotionDTO promotion = dcPromotionService.getCurPromotion();
			if(promotion == null){
				result.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_PROM_ENDED);
				return result ;
			}
			ResultModel<Boolean> tempResult = promotion.checkSelf();
			if (!tempResult.isSuccess()) {
				result.setFail(tempResult.getCode(), tempResult.getDetail());
				return result;
			}
			//检验用户是否参与过活动, 数据库在user_id和promotion_id上添加了唯一约束
			DcPromotionItemDTO temp = dcPromotionItemService.getUsersPromItems(ugcInfo.getCreatorId(), promotion.getId());
			if (temp != null) {
				result.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_TAKEIN_REPEAT);
				result.setData(temp.getId());
				return result;
			}
			//校验商品.
			DcItemDTO itemDTO = dcItemBO.getUgcDcItem(ugcInfo);
			if (itemDTO == null) {
				result.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_ITEM_NOT_EXIST);
				return result;
			}
			//检查商品是否符合活动规则
			tempResult = promotion.checkItem(itemDTO);		
			if (tempResult.isSuccess()) {
				result.setSuccess(ResultModel.CODE_SUCCESS, itemDTO.getId());
			} else {
				result.setFail(tempResult.getCode(), tempResult.getDetail());
			}
		} catch (ObjectNotExistException e) {
			logger.error(e);
			result.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_PROM_ENDED);
		} catch (Exception e) {
			logger.error(e);
			result.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_INTERNAL_ERROR);
		}
		return result;
	}
	
	/**
	 * 校验参与活动的豆蔻商品是否符合活动规则.
	 * @param itemId 
	 * @return code: success
	 *         code: fail, detail：
	 *                 promotion.ended 活动已结束
	 *                 promotion.not.start 活动未开始
	 *                 takein.repeat(已参与), data: dc_promotion_item.id
	 *                 item.not.exist 商品不存在.
	 *                 itemPrice.more(商品价格过高)
	 *                 internal.error(服务器内部错误)
	 */
	public ResultModel<Long> dcItemTakeinPromCheck(Long itemId, Long userId) {
		ResultModel<Long> result = new ResultModel<Long>();
		
		DcPromotionDTO promotion = dcPromotionService.getCurPromotion();
		ResultModel<Boolean> tempResult = promotion.checkSelf();
		if (!tempResult.isSuccess()) {
			result.setFail(tempResult.getCode(), tempResult.getDetail());
			return result;
		}
		DcPromotionItemDTO promItem = dcPromotionItemService.getUsersPromItems(userId, promotion.getId());
		if (promItem != null) {
			result.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_TAKEIN_REPEAT);
			result.setData(promItem.getId());
			return result;
		}
		DcItemDTO item = dcItemService.getItemById(itemId);
		if (item == null) {
			result.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_ITEM_NOT_EXIST);
			return result;
		}
		tempResult = promotion.checkItem(item);
		if (tempResult.isSuccess()) {
			result.setSuccess(ResultModel.CODE_SUCCESS, null);
		} else {
			result.setFail(tempResult.getCode(), tempResult.getDetail());
		}
		return result;
	}
	
	/**
	 * 参与pk活动.
	 * @param promotionId pk活动id.
	 * @param dcItem 参与活动的商品.
	 * @return 
	 */
	public ResultModel<Long> takeinPromotion(DcPromItemModel pkItem) {
		DcPromotionDTO promotion = dcPromotionService.getCurPromotion();
		ResultModel<Long> result = new ResultModel<Long>();
		DcPromotionItemDO promotionItemDO = new DcPromotionItemDO();
		promotionItemDO.setPromotionId(promotion.getId());
		promotionItemDO.setItemId(pkItem.getItemId());
		promotionItemDO.setUserId(pkItem.getUserId());
		promotionItemDO.setUserNick(pkItem.getUserNick());
		//默认0颗愿望星
		promotionItemDO.setRateCount(0);
		try {
			Long id = dcPromotionItemService.createPromotionItem(promotionItemDO);
			result.setSuccess(ResultModel.CODE_SUCCESS, id);
			
			//奖励1次砸金蛋机会.
			dcGameBO.award1GameChance(pkItem.getUserId(), DcGameEnum.GOLDEN_EGG);
			
			//愿望数统计
			try {
				int effectCount = dcPromotionSnapInfoService.incrWishCountByPromotionAndItem(promotion.getId(), pkItem.getItemId()) ;
				if(effectCount < 1){
					//插入
					DcPromotionSnapInfoDO snapInfo = new DcPromotionSnapInfoDO() ;
					snapInfo.setItemId(pkItem.getItemId()) ;
					snapInfo.setPromotionId(promotion.getId()) ;
					snapInfo.setWishCount(1) ;
					dcPromotionSnapInfoService.createPromotionSnap(snapInfo) ;
				}
			} catch (Exception e){
				logger.error(e);
			}
		} catch (DcDuplicateKeyException e) {
			DcPromotionItemDTO promItem = dcPromotionItemService.getUsersPromItems(pkItem.getUserId(), promotion.getId());
			if (promItem == null) {
				logger.error("---- can't find promotionitem from cache, unconsistent with db");
				result.setSuccess(ResultModel.CODE_SUCCESS, null);
			} else {
				result.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_TAKEIN_REPEAT);
				result.setData(promItem.getId());
			}
			result.setDetail(ResultModel.DETAIL_TAKEIN_REPEAT);
		} catch (Exception e) {
			logger.error(e);
			result.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_INTERNAL_ERROR);
		}
		return result;
	}
		
	
	/**
	 * 增加活动投票
	 * @param rateDetail
	 * @return
	 * @throws DuplicateOperateException
	 */
	public Long addRate(DcUserModel user ,Long promotionId , Long promotionItemId) throws DuplicateOperateException ,PromotionNotExistException {
		
		if(IDUtils.isNotCorrect(promotionId) || IDUtils.isNotCorrect(promotionItemId) || IDUtils.isNotCorrect(user.getUserId())){
			throw new IllegalArgumentException("rate itemId and rateUserId cant be null.") ;
		}
		DcPromotionDTO promotion = dcPromotionService.getPromotionById(promotionId) ;
		
		if(promotion == null || !DateUtils.isBetween(promotion.getStartTime(), promotion.getEndTime(), new Date())){
			//活动不存在或者活动时间不对
			throw new PromotionNotExistException("promotion not exists") ;
		}
		
		
		DcPromotionRateDetailDO rateDetail = new DcPromotionRateDetailDO() ;
		rateDetail.setPromotionId(promotionId) ;
		rateDetail.setPromotionItemId(promotionItemId) ;
		rateDetail.setAmount(1) ;
		rateDetail.setRateType(DcPromotionRateTypeEnums.RATE.getValue());
		rateDetail.setRateUserId(user.getUserId()) ;
		rateDetail.setRateUserNick(user.getNick()) ;
		Long id = dcPromotionRateDetailService.createRateDetail(rateDetail) ;
		dcPromotionItemService.incrRateCountById(rateDetail.getPromotionItemId()) ;
		
		return id ;
	}
	
	/**
	 * 抢票，把别人的票加到自己身上
	 * @param user
	 * @param promotionId
	 * @param myPromotionItemId
	 * @param stealPromotionItemId
	 * @throws PromotionNotExistException 活动不存在
	 * @throws IntegralNotEnoughException 积分不足
	 * @return 消耗的积分数
	 */
	public PromRateResult stealRate(DcUserModel user , Long promotionId ,  Long myPromotionItemId , Long stealPromotionItemId) 
			throws PromotionRateException  {
		if(IDUtils.isNotCorrect(user.getUserId()) || IDUtils.isNotCorrect(promotionId) || IDUtils.isNotCorrect(myPromotionItemId) || 
					IDUtils.isNotCorrect(stealPromotionItemId))  {
			throw new IllegalArgumentException("rateUserId and promotionId and myPromotionItemId and stealPromotionItemId cant be null.") ;
		}
		
		if(stealPromotionItemId == myPromotionItemId){ //同一商品
			throw new PromotionRateException(PromotionRateException.ERROR_STEAL_ITEM_SAME) ;
		}
		
		DcPromotionDTO promotion = dcPromotionService.getPromotionById(promotionId) ;
		
		if(promotion == null || !promotion.isOnGoing()){
			//活动不存在或者活动时间不对
			throw new PromotionRateException(PromotionRateException.ERROR_PROMOTION_REQUIRED) ;
		}
		
		//积分是否够
		if(!dcUserIntegralBO.hasEnoughIntegral(user.getUserId(), DcIntegralSourceEnums.STEAL_PROMO_RATE)){
			throw new PromotionRateException(PromotionRateException.ERROR_INTEGRAL_NOT_ENOUGH) ;
		}
		
		int currHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) ;
		int effectCount = dcPromotionItemService.decrDrawCountByHourAndId(stealPromotionItemId , currHour) ;
		
		if(effectCount <= 0){ //被抢的商品没有票数
			throw new PromotionRateException(PromotionRateException.ERROR_STEAL_ITEM_LOWRATE) ;
		}
		
		//增加自己的票数
		effectCount = dcPromotionItemService.incrRateCountByIdAndUser(myPromotionItemId, user.getUserId()) ;
				
		DcPromotionRateDetailDO rateDetail = new DcPromotionRateDetailDO() ;
		rateDetail.setPromotionId(promotionId) ;
		rateDetail.setPromotionItemId(myPromotionItemId) ;
		rateDetail.setStealPromotionItemId(stealPromotionItemId) ;
		rateDetail.setAmount(1) ;
		rateDetail.setRateType(DcPromotionRateTypeEnums.STEAL.getValue()) ;
		rateDetail.setRateUserId(user.getUserId());
		rateDetail.setRateUserNick(user.getNick()) ;
		rateDetail.setStatus(DcPromotionRateStatusEnums.NORMAL.getValue()) ;
		
		//记录给自己投票
		dcPromotionRateDetailService.createRateDetail(rateDetail) ;
		
		DcStealStarModel stealModel = new DcStealStarModel();
		stealModel.setToUserId(user.getUserId());
		stealModel.setToUserNick(user.getNick());
		stealModel.setFromPromItemId(stealPromotionItemId);
		int integralCount = dcUserIntegralBO.doStealPromRate(stealModel) ;
		
		
		//添加一个动态
		DcPromotionDynamicDTO dynamic = DcPromotionDynamicDTO.newStealRateDynamic(promotionId, myPromotionItemId, user.getUserId(), integralCount) ;
		this.addDynamic(promotionId, dynamic) ;
		
		return new PromRateResult(integralCount , 0) ;
	}
	
	/**
	 * 支取我当前的愿望值
	 * @param user
	 * @param promotionId
	 * @param myPromotionItemId
	 * @throws IllegalArgumentException , PromotionRateException
	 * 	ERROR_DRAW_RATE_LOW ,ERROR_PROMOTION_NOT_EXISTS 
	 * @return 
	 */
	public PromRateResult drawMyRate(DcUserModel user , Long promotionId) throws PromotionRateException {
		if(IDUtils.isNotCorrect(user.getUserId()) || IDUtils.isNotCorrect(promotionId))  {
			throw new IllegalArgumentException("rateUserId and promotionId and myPromotionItemId cant be null.") ;
		}
		
		DcPromotionDTO promotion = dcPromotionService.getPromotionById(promotionId) ;
		
		if(promotion == null || !DateUtils.isBetween(promotion.getStartTime(), promotion.getEndTime(), new Date())){
			//活动不存在或者活动时间不对
			throw new PromotionRateException(PromotionRateException.ERROR_PROMOTION_REQUIRED) ;
		}		
		
		DcPromotionItemDTO promItem = dcPromotionItemService.getUsersPromItems(user.getUserId(), promotionId) ;
		if(promItem == null){
			//用户没有参加活动
			throw new PromotionRateException(PromotionRateException.ERROR_PROMOTION_USER_REQUIRED) ;
		}
		int currHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) ;
		int canDrawCount = promItem.getCanDrawCountByHour(currHour) ; //查询可以支取的愿望值
		if(canDrawCount <= 0 ){ //没有可以领取的愿望值
			throw new PromotionRateException(PromotionRateException.ERROR_DRAW_RATE_LOW) ;
		}
		
		int effectCount = dcPromotionItemService.drawRateByHour(promItem.getId(), currHour) ;
		
		DcPromotionRateDetailDO rateDetail = new DcPromotionRateDetailDO() ;
		rateDetail.setPromotionId(promotionId) ;
		rateDetail.setPromotionItemId(promItem.getId()) ;
		rateDetail.setAmount(canDrawCount) ;
		rateDetail.setStealPromotionItemId(promItem.getId()) ;
		rateDetail.setRateType(DcPromotionRateTypeEnums.DRAW.getValue()) ;
		rateDetail.setRateUserId(user.getUserId());
		rateDetail.setRateUserNick(user.getNick()) ;
		rateDetail.setStatus(DcPromotionRateStatusEnums.NORMAL.getValue()) ;
		//记录领取愿望值的记录
		dcPromotionRateDetailService.createRateDetail(rateDetail) ;
		PromRateResult res = new PromRateResult(0 , canDrawCount) ;
		res.setRateCount(promItem.getRateCount() + canDrawCount) ;
		return res ;
	}
	
	/**
	 * 获取投票top n商品信息.
	 * @param promotionId
	 * @param count
	 * @return
	 */
	public List<DcPromotionItemDTO> getPromotionTopRanks(Long promotionId, int topN) {
		DcPromotionItemSearchCondition condition = new DcPromotionItemSearchCondition();
		condition.setPromotionId(promotionId);
		condition.setOrder(OrderEnum.rate_count);
		Pagination pagination = new Pagination(1, topN);
		List<DcPromotionItemDTO> promItems = dcPromotionItemService.getPromotionItemsNoPagination(condition, pagination);
		if (CollectionUtils.isEmpty(promItems)) {
			return promItems;
		}
		fillPromItemDetails(promItems);
		int rank = 1, rateCount = promItems.get(0).getRateCount();
		for (DcPromotionItemDTO temp: promItems) {
			if (temp.getRateCount() < rateCount) {
				rank++;
			}
			temp.setRank(rank);
		}
		return promItems;
	}
	
	/**
	 * 默认排序
	 * @param promotionId
	 * @param pagination
	 * @return
	 */
	public List<DcPromotionItemDTO> getDefaultItems(Long promotionId , Pagination pagination) {
		DcPromotionItemSearchCondition condition = new DcPromotionItemSearchCondition() ;
		condition.setOrder(OrderEnum.gmt_modified) ;
		condition.setPromotionId(promotionId) ;
		return dcPromotionItemService.getPromotionItemsNoPagination(condition, pagination) ;
	}
	
	/**
	 * 获取用户今日活动数据.
	 * @param userId
	 * @return
	 */
	public DcPromotionItemDTO getUserCurPromotionItem(Long userId) {
		DcPromotionDTO promotion = dcPromotionService.getCurPromotion();
		if (promotion == null) {
			return null;
		}
		DcPromotionItemSearchCondition condition = new DcPromotionItemSearchCondition();
		condition.setPromotionId(promotion.getId()) ;
		condition.setOrder(OrderEnum.no_sort) ;
		condition.setUserId(userId) ;
		List<DcPromotionItemDTO> promItems = dcPromotionItemService.getPromotionItemsNoPagination(condition, new Pagination(1,1));
		if (CollectionUtils.isEmpty(promItems)) {
			return null;
		}
		DcPromotionItemDTO myProm = promItems.get(0);
		DcItemDTO item = dcItemService.getItemById(myProm.getItemId());
		myProm.setItem(item);
		return myProm;
	}
	
	/**
	 * 获取许愿最多的商品列表.
	 * @return
	 */
	public List<DcPromotionSnapInfoDTO> getMostWishItems(Long promotionId) {
	    List<DcPromotionSnapInfoDTO> promSnapInfos = dcPromotionSnapInfoService.getPromotionSnapInfosDesc(promotionId);
	    fillPromSnapInfoDetails(promSnapInfos);
	    return promSnapInfos;
	}
	
	private void fillPromItemDetails(List<DcPromotionItemDTO> promItems) {
		if (CollectionUtils.isEmpty(promItems)) {
			return ;
		}
		List<Long> itemIds = new ArrayList<Long>();
		for (DcPromotionItemDTO temp: promItems) {
			itemIds.add(temp.getItemId());
		}
		List<DcItemDTO> tempItems = dcItemService.getItemsByIds(itemIds);
		for (DcPromotionItemDTO temp: promItems) {
			for (DcItemDTO item: tempItems) {
				if(item.getId().equals(temp.getItemId())) {
					temp.setItem(item);
					break;
				}
			}
		}
	}
	
	private void fillPromSnapInfoDetails(List<DcPromotionSnapInfoDTO> snapInfos) {
		if (CollectionUtils.isEmpty(snapInfos)) {
			return;
		}
		 List<Long> itemIds = new ArrayList<Long>();
	    for (DcPromotionSnapInfoDTO temp: snapInfos) {
	    	itemIds.add(temp.getItemId());
	    }
	    List<DcItemDTO> items = dcItemService.getItemsByIds(itemIds);
	    for (DcPromotionSnapInfoDTO temp: snapInfos) {
	    	for (DcItemDTO item: items) {
	    		if (item.getId().equals(temp.getItemId())) {
	    			temp.setItemTitle(item.getItemTitle());
	    			temp.setItemPrice(item.getItemPromPriceFormat());
	    			temp.setItemPicUrl160x160(item.getPicUrl160x160());
	    			break;
	    		}
	    	}
	    }
	}
	
	
}