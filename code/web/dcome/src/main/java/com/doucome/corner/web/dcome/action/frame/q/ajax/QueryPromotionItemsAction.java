package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionItemSearchCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionItemSearchCondition.OrderEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.model.util.DcPromotionItemUtils;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcPromotionItemService;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 
 * @author langben 2012-8-26
 * 
 */
@SuppressWarnings("serial")
public class QueryPromotionItemsAction extends DComeBasicAction {

	private JsonModel<List<DcPromotionItemDTO>> json = new JsonModel<List<DcPromotionItemDTO>>();
	
	private int size = 8 ;
	
	private int start = 1 ;
	
	private String order ;
	
	private Long promotionId ;

	@Autowired
	private DcItemService dcItemService;
	
	@Autowired
	private DcPromotionItemService dcPromotionItemService ;
	
	@Override
	public String execute() throws Exception {
		try {
			if(IDUtils.isNotCorrect(promotionId)){
				json.setCode(JsonModel.CODE_ILL_ARGS) ;
				json.setDetail("promotionId is require.") ;
				return SUCCESS ;
			}
			if(size < 1 || size > 50 || start < 1){
				json.setCode(JsonModel.CODE_ILL_ARGS) ;
				json.setDetail("size must great than 0.") ;
				return SUCCESS ;
			}
			//活动商品
			DcPromotionItemSearchCondition promotionCondition = new DcPromotionItemSearchCondition();
			promotionCondition.setPromotionId(promotionId) ;
			if(StringUtils.isNotBlank(order)) {
				promotionCondition.setOrder(OrderEnum.fromName(order)) ;
			} else {
				Calendar cal = Calendar.getInstance() ;
				int hour = cal.get(Calendar.HOUR_OF_DAY) ;
				if(DcPromotionItemUtils.isDrawHour(hour)){
					promotionCondition.setOrder(OrderEnum.can_draw) ;
					promotionCondition.setDrawHour(hour) ;
				}
			}
			/**
			 * 最近刷新
			 */
			List<DcPromotionItemDTO> promItemList = dcPromotionItemService.getPromotionItemsNoPagination(promotionCondition, start , size) ;
			if(CollectionUtils.isNotEmpty(promItemList)) {
				List<Long> idList = mergeIds(promItemList) ;
				List<DcItemDTO> itemList = dcItemService.getItemsByIds(idList) ;
				
				Map<Long , DcItemDTO> map = DcItemUtils.conventToMap(itemList) ;
				
				for(DcPromotionItemDTO pitem : promItemList){
					pitem.setItem(map.get(pitem.getItemId())) ;
				}
			}
			json.setData(promItemList) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
		} catch(Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
			return SUCCESS ;
		}
		return SUCCESS ;
	}

	private List<Long> mergeIds(List<DcPromotionItemDTO> promItemList){
		List<Long> mergeIdList = new ArrayList<Long>() ;
		
		if(CollectionUtils.isNotEmpty(promItemList)){
			for(DcPromotionItemDTO pi : promItemList){
				mergeIdList.add(pi.getItemId()) ; 
			}
		}
		
		return mergeIdList ;
	}

	public JsonModel<List<DcPromotionItemDTO>> getJson() {
		return json;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
	
}
