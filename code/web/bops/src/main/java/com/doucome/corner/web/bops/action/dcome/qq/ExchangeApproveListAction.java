package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcExchangeItemCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcUserExchangeApproveSearchCondition;
import com.doucome.corner.biz.dcome.enums.DcExchangeTypeEnums;
import com.doucome.corner.biz.dcome.model.DcExchangeItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserExchangeApproveDTO;
import com.doucome.corner.biz.dcome.service.DcExchangeItemService;
import com.doucome.corner.biz.dcome.service.DcUserExchangeApproveService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ¶Ò»»¼ÇÂ¼
 * @author langben 2013-1-13
 *
 */
@SuppressWarnings("serial")
public class ExchangeApproveListAction  extends BopsBasicAction implements ModelDriven<DcUserExchangeApproveSearchCondition>{

	private DcUserExchangeApproveSearchCondition condition = new DcUserExchangeApproveSearchCondition() ;
	
	@Autowired
	private DcUserExchangeApproveService dcUserExchangeApproveService ;
	
	@Autowired
	private DcExchangeItemService dcExchangeItemService ;
	
	
	private int page ;
	
	private QueryResult<DcUserExchangeApproveDTO> queryResult ;
	
	private Map<Long,DcExchangeItemDTO> exItemMap ;
	
	@Override
	public String execute() throws Exception {
		
		queryResult = dcUserExchangeApproveService.getExchangeApproveWithPagination(condition, new Pagination(page)) ;
		
		DcExchangeItemCondition exCondition = new DcExchangeItemCondition() ;
		exCondition.setExType(DcExchangeTypeEnums.DEFAULT_EXCHANGE.getValue()) ;
		List<DcExchangeItemDTO> exItemList = dcExchangeItemService.queryExchangeItems(exCondition) ;
		
		exItemMap = convert2Map(exItemList) ;
		
		return SUCCESS ;
	}
	
	private Map<Long,DcExchangeItemDTO> convert2Map(List<DcExchangeItemDTO> exItemList){
		Map<Long,DcExchangeItemDTO> exItemMap = new HashMap<Long,DcExchangeItemDTO>() ;
		if(CollectionUtils.isNotEmpty(exItemList)) {
			for(DcExchangeItemDTO dto : exItemList){
				exItemMap.put(dto.getId(), dto) ;
			}
		}
		
		return exItemMap ;
	}
	
	@Override
	public DcUserExchangeApproveSearchCondition getModel() {
		return condition ;
	}

	public QueryResult<DcUserExchangeApproveDTO> getQueryResult() {
		return queryResult;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Map<Long, DcExchangeItemDTO> getExItemMap() {
		return exItemMap;
	}

}
