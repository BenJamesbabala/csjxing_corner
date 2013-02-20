package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * pgc item查询接口
 * @author langben 2012-8-25
 *
 */
@SuppressWarnings("serial")
public class QueryItemsAjaxAction  extends DComeBasicAction {
	
	private static final BigDecimal FREE_PRICE = new BigDecimal(50) ;

	private JsonModel<List<DcItemDTO>> json = new JsonModel<List<DcItemDTO>>() ;
	
	private DcItemSearchCondition condition = new DcItemSearchCondition();
	
	private int page = 1;
	
	private int size = 15;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Override
	public String execute() throws Exception {
		try {
			condition.setGenWay(DcItemGenWayEnums.PROFESSIONAL.getValue()) ;
			condition.setItemStatus(DcItemStatusEnum.NORMAL.getValue()) ;
			condition.setRecommType("NULL") ;
			List<Long> idList = dcItemService.getItemIdsNoPagination(condition, new Pagination(page,size)) ;
			List<DcItemDTO> itemList = dcItemService.getItemsByIds(idList) ;
			//this.sortItemsByPrice(itemList) ;
			json.setData(itemList) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
			return SUCCESS ;
			
		} catch(Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
			return SUCCESS ;
		}
		
	}
	
	/**
	 * 为了使免费领取按钮提前，这里做一个排序
	 * @param itemList
	 */
	private void sortItemsByPrice(List<DcItemDTO> itemList){
		Collections.sort(itemList, new Comparator<DcItemDTO>() {
			@Override
			public int compare(DcItemDTO o1, DcItemDTO o2) {
				if(o1 == null){
					return -1 ;
				}
				if(o2 == null){
					return 1 ;
				}
				BigDecimal itemPrice1 = o1.getItemPrice() ;
				BigDecimal itemPromPrice1 = o1.getItemPromPrice() ;
				BigDecimal price1 = itemPromPrice1 == null ? itemPrice1 : itemPromPrice1 ;
				
				BigDecimal itemPrice2 = o2.getItemPrice() ;
				BigDecimal itemPromPrice2 = o2.getItemPromPrice() ;
				BigDecimal price2 = itemPromPrice2 == null ? itemPrice2 : itemPromPrice2 ;
				
				
				boolean isPrice1Less = DecimalUtils.lessEqualThan(price1, FREE_PRICE) ;
				boolean isPrice2Less = DecimalUtils.lessEqualThan(price2, FREE_PRICE) ;
				//都小于50
				if(isPrice1Less && isPrice2Less){
					return 0 ;
				}
				//price1 小于50
				if(isPrice1Less){
					return -1 ;
				}
				//price2 小于50
				if(isPrice2Less){
					return 1 ;
				}
				
				return 0 ;
			}
		}) ;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public JsonModel<List<DcItemDTO>> getJson() {
		return json;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public void setCategoryId(Long categoryId) {
		condition.setCategoryId(categoryId);
	}
}
