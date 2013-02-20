package com.doucome.corner.biz.dcome.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.core.utils.UUIDUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcExchangeItemCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionAwardCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionAwardDO;
import com.doucome.corner.biz.dal.model.DcItemPicModel;
import com.doucome.corner.biz.dcome.constant.DcAwardConstant;
import com.doucome.corner.biz.dcome.enums.DcPromScheduleEnum;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardReviewStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardSendStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcPromotionTypeEnum;
import com.doucome.corner.biz.dcome.model.DcExchangeItemDTO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.param.DcExchangeItemModel;
import com.doucome.corner.biz.dcome.model.param.DcNewExItemModel;
import com.doucome.corner.biz.dcome.model.param.DcUgcItemModel;
import com.doucome.corner.biz.dcome.service.DcExchangeItemService;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcPromotionAwardService;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.common.model.ResultModel;

/**
 * 积分拍卖业务逻辑类.
 * @author ze2200
 *
 */
public class DcExchangeBO {
	@Autowired
	private DcExchangeItemService dcExchangeItemService;
	@Autowired
	private DcItemService dcItemService;
	@Autowired
	private DcPromotionAwardService dcPromotionAwardService;
	@Autowired
	private DcUserIntegralOperateBO dcUserIntegralOperateBO ;
	@Autowired
	private DcItemBO dcItemBO;
	
	private static final Log logger = LogFactory.getLog(DcExchangeBO.class);
	
	/**
	 * 添加豆蔻商品到兑换.
	 * @param itemId
	 * @param newExModel
	 * @return
	 */
	public ResultModel<Long> addDcItemToExchange(Long itemId, DcNewExItemModel newExModel) {
		ResultModel<Long> result = new ResultModel<Long>();
		DcItemDTO item = dcItemService.getItemById(itemId);
		if (item == null) {
			result.setFail(JsonModel.CODE_FAIL, "invalid.param");
			return result;
		}
		newExModel = newExModel == null? new DcNewExItemModel(): newExModel;
		newExModel.setPublic(true);
		DcExchangeItemDTO exchangeItem = getExchangeItem(item, newExModel);
		Long exchangeId = dcExchangeItemService.createExchangeItem(exchangeItem.toDO());
		result.setSuccess(JsonModel.CODE_SUCCESS, exchangeId);
		return result;
	}
	
	/**
	 * 添加ugc兑换.
	 * @param nativeId
	 * @param userId
	 * @param newExModel
	 * @return
	 */
	public ResultModel<Long> addUgcToExchange(String nativeId, Long userId, DcNewExItemModel newExModel) {
		ResultModel<Long> result = new ResultModel<Long>();
		DcUgcItemModel ugcModel = new DcUgcItemModel();
		ugcModel.setItemUrl("http://detail.taobao.com/item?id=" + nativeId);
		ugcModel.setIsShare(false);
		ugcModel.setCreatorId(userId);
		ugcModel.setPromPrice(newExModel.getPrice());
		ResultModel<DcItemDTO> newResult = dcItemBO.createUgcItem(ugcModel);
		if (!newResult.isSuccess()) {
			result.setFail(JsonModel.CODE_FAIL, newResult.getDetail());
			return result;
		}
		DcItemDTO item = newResult.getData();
		newExModel = newExModel == null? new DcNewExItemModel(): newExModel;
		newExModel.setUserId(userId);
		newExModel.setPublic(false);
		newExModel.setExCount(1);
		DcExchangeItemDTO exchangeItem = getExchangeItem(item, newExModel);
		Long exchangeId = dcExchangeItemService.createExchangeItem(exchangeItem.toDO());
		result.setSuccess(JsonModel.CODE_SUCCESS, exchangeId);
		return result;
	}
	
	/**
	 * 
	 * @param item
	 * @param newExModel
	 * @return
	 */
	private DcExchangeItemDTO getExchangeItem(DcItemDTO item, DcNewExItemModel newExModel) {
		DcExchangeItemDTO exchangeItem = new DcExchangeItemDTO();
		exchangeItem.setItemId(item.getId());
		exchangeItem.setItemTitle(item.getItemTitle());
		exchangeItem.setItemPrice(item.getItemPrice());
		exchangeItem.setItemPictures(JacksonHelper.toJSON(new DcItemPicModel[] { item.getPicModel(0)}));
		exchangeItem.setExCount(newExModel.getExCount());
		exchangeItem.setExIntegral(newExModel.getExIntegral());
		exchangeItem.setUserId(newExModel.getUserId());
		exchangeItem.setItemType(newExModel.getItemType());
		return exchangeItem;
	}
	
	/**
	 * 获取当前积分兑换数据.
	 * @return
	 */
	public List<DcExchangeItemDTO> getCurExchangeItems(DcUserDTO user) {
		DcExchangeItemCondition condition = new DcExchangeItemCondition();
		condition.setUserId(user.getUserId());
		condition.setStatus(DcPromScheduleEnum.ONGOING.getValue());
		try {
			QueryResult<DcExchangeItemDTO> temps1 = dcExchangeItemService.queryExchangeItemsPage(condition, new Pagination(1, 2));
			List<DcExchangeItemDTO> exchangeItems = temps1.getItems() != null? temps1.getItems(): new ArrayList<DcExchangeItemDTO>();
			condition.setUserId(DcItemUtils.PGC_ITEM_CREATOR);
			condition.setOrder("exIntegral");
			List<DcExchangeItemDTO> temps2 = dcExchangeItemService.queryExchangeItems(condition);
			exchangeItems.addAll(temps2);
			return exchangeItems;
		} catch (Exception e) {
			logger.error(e);
			return new ArrayList<DcExchangeItemDTO>();
		}
	}
	
	/**
	 * 兑换商品.
	 * @param user
	 * @param Id
	 * @return
	 */
	public ResultModel<Integer> exchangeItem(DcUserDTO user, Long exchangeId, int exCount) {
		ResultModel<Integer> result = new ResultModel<Integer>();
		if (exCount <= 0) {
			result.setFail(JsonModel.CODE_FAIL, "invalid.excount");
			return result;
		}
		DcExchangeItemDTO exchangeItem = dcExchangeItemService.getExchangeItem(exchangeId);
		if (exchangeItem == null || exchangeItem.getSchedule() == DcPromScheduleEnum.FUTURE) {
			result.setFail(JsonModel.CODE_FAIL, "invalid.param");
			return result;
		}
		if (exchangeItem.getExCount() < exCount) {
			result.setFail(JsonModel.CODE_FAIL, "excount.more");
			result.setData(exchangeItem.getExCount());
			return result;
		}
		exchangeItem.setUserLevelEnum(user.getLevelEnum());
		//理论上不存在溢出的问题.
		int consumeIntegral = exchangeItem.getUserExIntegral() * exCount;
		if (consumeIntegral > user.getIntegralCount()) {
			result.setFail(JsonModel.CODE_FAIL, "integral.less");
			return result;
		}
		if (exchangeItem.getSchedule() == DcPromScheduleEnum.ENDED) {
			result.setFail(JsonModel.CODE_FAIL, "exchange.ended");
			return result;
		}
		
		int count = dcExchangeItemService.decExchangeNum(exchangeId, exCount);
		if (count > 0) {
			exchangeItem.setExCount(exchangeItem.getExCount() - exCount);
			//插入中奖记录.
			addExchangeAward(exchangeItem, user, exCount);
			result.setSuccess(JsonModel.CODE_SUCCESS, consumeIntegral);
		} else {
			exchangeItem = dcExchangeItemService.getExchangeItem(exchangeId);
			result.setFail(JsonModel.CODE_FAIL, "excount.more");
			result.setData(exchangeItem.getExCount());
		}
		
		return result;
	}
	
	public ResultModel<Integer> delExchangeItem(Long exchangeId) {
		ResultModel<Integer> result = new ResultModel<Integer>();
		DcExchangeItemDTO exchangeItem = dcExchangeItemService.getExchangeItem(exchangeId);
		if (exchangeItem == null || exchangeItem.getSchedule() != DcPromScheduleEnum.FUTURE) {
			result.setFail(JsonModel.CODE_FAIL, "invalid.param");
			return result;
		}
		int count = dcExchangeItemService.delExchangeItem(exchangeId);
		result.setSuccess(JsonModel.CODE_SUCCESS, count);
		return result;
	}
	
	/**
	 * 插入积分兑换开奖记录.
	 * @param exchangeId
	 * @return
	 */
	private Long addExchangeAward(DcExchangeItemDTO exchangeItem, DcUserDTO user, int exCount) {
		DcPromotionAwardDO awardDO = new DcPromotionAwardDO() ;
		awardDO.setItemId(exchangeItem.getItemId()) ;
		awardDO.setItemType(exchangeItem.getItemType());
		awardDO.setPromotionId(exchangeItem.getId()) ;
		awardDO.setPromotionItemId(DcAwardConstant.INVALID_ITEM_ID) ;
		awardDO.setPromotionType(DcPromotionTypeEnum.EXCHANGE.getType()) ;
		awardDO.setSendStatus(DcPromotionAwardSendStatusEnums.UNSEND.getStatus()) ;
		awardDO.setCheckCode(UUIDUtils.random20Num()) ;
		awardDO.setRateCount(exchangeItem.getUserExIntegral());
		awardDO.setItemNum(exCount);
		awardDO.setUserId(user.getUserId());
		awardDO.setUserNick(user.getNick());
		awardDO.setReviewStatus(DcPromotionAwardReviewStatusEnums.UNVIEW.getStatus());
		Long awardId = dcPromotionAwardService.createAward(awardDO);
		
		DcExchangeItemModel model = new DcExchangeItemModel();
		model.setUserId(user.getUserId());
		model.setUserNick(user.getNick());
		model.setExIntegral(exchangeItem.getUserExIntegral() * exCount);
		model.setExItemId(exchangeItem.getItemId());
		model.setExItemTitle(exchangeItem.getItemTitle());
		model.setOtherInfo(String.valueOf(exCount));
		model.setAwardId(awardId) ;
		//dcUserIntegralBO.doExchangeItem(model);
		
		dcUserIntegralOperateBO.doDcItemExchange(user, model.getExIntegral(), exchangeItem.toDO(), exCount , awardId) ;
		
		return awardId;
	}
	
	/**
	 * 
	 * @param condition
	 * @param page
	 * @return
	 */
	public QueryResult<DcExchangeItemDTO> getExchangeItemPage(DcExchangeItemCondition condition, int page) {
		QueryResult<DcExchangeItemDTO> exchangeItems = dcExchangeItemService.queryExchangeItemsPage(condition, new Pagination(page));
		fillItemInfo(exchangeItems.getItems());
		return exchangeItems;
	}
	
	/**
	 * 统计商品兑换情况.
	 * @param exchangeId
	 * @return
	 */
	public Map<String, Object> getExchangeDetail(Long exchangeId) {
		Map<String, Object> result = new HashMap<String, Object>();
		DcPromotionAwardCondition condition = new DcPromotionAwardCondition();
		condition.setPromotionId(exchangeId);
		condition.setPromotionType(DcPromotionTypeEnum.EXCHANGE.getType());
		List<DcPromotionAwardDTO> awards = dcPromotionAwardService.getAwardsNoPagination(condition, new Pagination(1, 50));
		int unReviewCount = 0, sendCount = 0;
		for (DcPromotionAwardDTO award: awards) {
			if (DcPromotionAwardReviewStatusEnums.UNVIEW.getStatus().equals(award.getReviewStatus())) {
				unReviewCount++;
			}
			if (DcPromotionAwardSendStatusEnums.SUCCESS.getStatus().equals(award.getSendStatus())) {
				sendCount++;
			}
		}
		result.put("unReviewCount", unReviewCount);
		result.put("sendCount", sendCount);
		return result;
	}
	
	/**
	 * 填充商品信息.
	 * @param exchangeItems
	 */
	private void fillItemInfo(List<DcExchangeItemDTO> exchangeItems) {
		if (CollectionUtils.isEmpty(exchangeItems)) {
			return ;
		}
		List<Long> itemIds = new ArrayList<Long>();
		for (DcExchangeItemDTO temp: exchangeItems) {
			itemIds.add(temp.getItemId());
		}
		List<DcItemDTO> items = dcItemService.getItemsByIds(itemIds);
		for (DcExchangeItemDTO temp: exchangeItems) {
			for (DcItemDTO item: items) {
				if (item.getId().equals(temp.getItemId())) {
					temp.setItemPromPriceFmt(item.getItemPromPriceFormat());
					if (item.getCommissionRate() == null) {
						temp.setItemCommissionFmt("0.00");
					} else if (item.getItemPromPrice() != null){
					    temp.setItemCommissionFmt(DecimalUtils.format(item.getItemPromPrice().multiply(item.getCommissionRate()), "#,##0.00"));
					} else {
						temp.setItemCommissionFmt(DecimalUtils.format(item.getItemPrice().multiply(item.getCommissionRate()), "#,##0.00"));
					}
					break;
				}
			}
		}
	}
}