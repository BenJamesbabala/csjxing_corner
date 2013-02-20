package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcExchangeItemCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcExchangeItemDO;
import com.doucome.corner.biz.dal.dcome.DcExchangeItemDAO;
import com.doucome.corner.biz.dcome.model.DcExchangeItemDTO;
import com.doucome.corner.biz.dcome.service.DcExchangeItemService;

/**
 * 
 * @author ze2200
 *
 */
public class DcExchangeItemServiceImpl implements DcExchangeItemService {
	@Autowired
	private DcExchangeItemDAO dcExchangeItemDAO;
	
	@Override
	public Long createExchangeItem(DcExchangeItemDO exchangeItem) {
		return dcExchangeItemDAO.insertExchangeItem(exchangeItem);
	}

	@Override
	public DcExchangeItemDTO getExchangeItem(Long id) {
		DcExchangeItemDO exchangeItem = dcExchangeItemDAO.getExchangeItem(id);
		if (exchangeItem == null) {
			return null;
		}
		return new DcExchangeItemDTO(exchangeItem);
	}
	
	@Override
	public List<DcExchangeItemDTO> queryExchangeItems(DcExchangeItemCondition condition) {
		List<DcExchangeItemDO> exchangeItems = dcExchangeItemDAO.queryExchangeItemsNoPage(condition);
		return convert(exchangeItems);
	}
	
	@Override
	public QueryResult<DcExchangeItemDTO> queryExchangeItemsPage(DcExchangeItemCondition condition, Pagination page) {
		int count = dcExchangeItemDAO.countExchangeItems(condition);
		if (count == 0) {
			return new QueryResult<DcExchangeItemDTO>(new ArrayList<DcExchangeItemDTO>(), page, 0);
		}
		List<DcExchangeItemDO> temps =
			dcExchangeItemDAO.queryExchangeItemsPage(condition, page.getStart() - 1, page.getSize());
		List<DcExchangeItemDTO> exchangeItems = convert(temps);
		return new QueryResult<DcExchangeItemDTO>(exchangeItems, page, count);
	}
	
	@Override
	public Integer initExchangeInfo(DcExchangeItemDTO exchangeItem) {
		DcExchangeItemDO exchange = new DcExchangeItemDO();
		exchange.setId(exchangeItem.getId());
		exchange.setExIntegral(exchangeItem.getExIntegral());
		exchange.setExCount(exchangeItem.getExCount());
		exchange.setItemType(exchangeItem.getItemType());
		return dcExchangeItemDAO.initExchangeInfo(exchange);
	}
	
	@Override
	public Integer decExchangeNum(Long id, int exCount) {
		return this.dcExchangeItemDAO.decExchangeNum(id, exCount);
	}
	
	@Override
	public int delExchangeItem(Long exchangeId) {
		return dcExchangeItemDAO.delExchangeItem(exchangeId);
	}
	
	private List<DcExchangeItemDTO> convert(List<DcExchangeItemDO> exchangeItems) {
		List<DcExchangeItemDTO> result = new ArrayList<DcExchangeItemDTO>();
		if (CollectionUtils.isEmpty(exchangeItems)) {
			return result;
		}
		for (DcExchangeItemDO temp: exchangeItems) {
			result.add(new DcExchangeItemDTO(temp));
		}
		return result;
	}
}