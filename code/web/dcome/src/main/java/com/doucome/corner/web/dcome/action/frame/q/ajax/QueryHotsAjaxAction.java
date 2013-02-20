package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dcome.enums.DcItemRecommTypeEnums;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * 热卖item查询接口.
 * @author langben 2012-8-25
 *
 */
@SuppressWarnings("serial")
public class QueryHotsAjaxAction  extends DComeBasicAction {

	private JsonModel<List<DcItemDTO>> json = new JsonModel<List<DcItemDTO>>();
	
	@Autowired
	private DcItemService dcItemService ;
	
	private Integer size;
	
	@Override
	public String execute() throws Exception {
		try {
			//默认10款产品
			if (size == null || size < 10) {
				size = 10;
			}
			DcItemSearchCondition itemCondition = new DcItemSearchCondition();
			itemCondition.setRecommType(DcItemRecommTypeEnums.LIMIT_BUY.getValue());
			itemCondition.setItemStatus(DcItemStatusEnum.NORMAL.getValue());
			List<Long> idList = dcItemService.getItemIdsNoPagination(itemCondition, new Pagination(1, size)) ;
			List<DcItemDTO> itemList = dcItemService.getItemsByIds(idList);
			json.setData(itemList) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
			return SUCCESS;
		} catch(Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
			return SUCCESS ;
		}
		
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public JsonModel<List<DcItemDTO>> getJson() {
		return json;
	}
}
