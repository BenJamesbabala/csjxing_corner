package com.doucome.corner.web.dcome.action.frame.q;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dcome.enums.DcItemRecommTypeEnums;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;

/**
 * QQ ¿Õ¼äÊ×Ò³
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcIndexAction extends QBasicAction {
	
	private Integer size = 12;
	
	private List<DcItemDTO> hotItems;
	
	@Autowired
	private DcItemService dcItemService;
	
	private String recType;
	
	@Override
	public String execute() {
		DcItemSearchCondition itemCondition = new DcItemSearchCondition();
		DcItemRecommTypeEnums recEnum = DcItemRecommTypeEnums.toEnum(recType);
		if (recEnum == DcItemRecommTypeEnums.NONE) {
			recType = DcItemRecommTypeEnums.LIMIT_BUY.getValue();
		}
		itemCondition.setRecommType(recType);
		itemCondition.setItemStatus(DcItemStatusEnum.NORMAL.getValue());
		List<Long> idList = dcItemService.getItemIdsNoPagination(itemCondition, new Pagination(1, size)) ;
		hotItems = dcItemService.getItemsByIds(idList);
		return SUCCESS ;
	}

	public List<DcItemDTO> getHotItems() {
		return hotItems;
	}

	public String getRecType() {
		return recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}
}
