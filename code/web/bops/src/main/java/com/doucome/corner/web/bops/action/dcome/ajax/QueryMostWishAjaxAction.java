package com.doucome.corner.web.bops.action.dcome.ajax;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dcome.business.DcItemBO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionSnapInfoDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionSnapInfoService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * 许愿最多的商品
 * @author langben 2012-11-26
 *
 */
@SuppressWarnings("serial")
public class QueryMostWishAjaxAction extends BopsBasicAction {

	private Long promotionId ;
	
	private int page ;
	
	private JsonModel<List<DcPromotionSnapInfoDTO>> json = new JsonModel<List<DcPromotionSnapInfoDTO>>() ;
	
	
	@Autowired
	private DcItemBO dcItemBO ;
	
	@Autowired
	private DcPromotionSnapInfoService dcPromotionSnapInfoService ;
	
	@Override
	public String execute() throws Exception {
		
		if(IDUtils.isNotCorrect(promotionId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.promotionId.required") ;
			return SUCCESS ;
		}
		try {
			List<DcPromotionSnapInfoDTO> snapInfoList = dcPromotionSnapInfoService.getPromotionSnapsFromDb(promotionId, new Pagination(page , 5)) ;
			
			if(CollectionUtils.isNotEmpty(snapInfoList)) {
				List<Long> idList = getItemIdList(snapInfoList) ;
				
				Map<Long,DcItemDTO> itemMap = dcItemBO.getItemsMapByIds(idList) ;
				
				for(DcPromotionSnapInfoDTO dto : snapInfoList){
					DcItemDTO item = itemMap.get(dto.getItemId()) ;
					if(item != null) {
						dto.setItemTitle(item.getItemTitle()) ;
						BigDecimal itemPrice = item.getItemPromPrice() ;
						if(itemPrice == null){
							itemPrice = item.getItemPrice() ;
						}
						dto.setItemPrice(DecimalUtils.format(itemPrice , "0.00")) ;
					}
				}
			}
			
			json.setData(snapInfoList) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
		} catch (Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
		}
		
		return SUCCESS ;
	}
	
	private List<Long> getItemIdList(List<DcPromotionSnapInfoDTO> snapInfoList ){
		List<Long> itemIdList = new ArrayList<Long>() ;
		for(DcPromotionSnapInfoDTO snap : snapInfoList) {
			itemIdList.add(snap.getItemId()) ;
		}
		return itemIdList ;
	}

	public JsonModel<List<DcPromotionSnapInfoDTO>> getJson() {
		return json;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
