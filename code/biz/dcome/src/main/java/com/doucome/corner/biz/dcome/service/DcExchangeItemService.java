package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcExchangeItemCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcExchangeItemDO;
import com.doucome.corner.biz.dcome.model.DcExchangeItemDTO;

/**
 * 积分兑换商品服务
 * @author ze2200
 *
 */
public interface DcExchangeItemService {
	/**
	 * 
	 * @return
	 */
	Long createExchangeItem(DcExchangeItemDO exchangeItem);
	/**
	 * 
	 * @param id
	 * @return
	 */
	DcExchangeItemDTO getExchangeItem(Long id);
	/**
	 * 
	 * @param condition
	 * @return
	 */
	List<DcExchangeItemDTO> queryExchangeItems(DcExchangeItemCondition condition);
	/**
	 * 
	 * @return
	 */
	QueryResult<DcExchangeItemDTO> queryExchangeItemsPage(DcExchangeItemCondition condition, Pagination page);
	/**
	 * 
	 * @param exchangeItem
	 * @return
	 */
	Integer initExchangeInfo(DcExchangeItemDTO exchangeItem);
	/**
	 * 更新兑换商品的用户信息.sql里含有业务逻辑，防止并发问题.
	 * @param id
	 * @param userId
	 * @param userNick
	 * @return
	 */
	Integer decExchangeNum(Long id, int exCount);
	/**
	 * 
	 * @param exchangeId
	 * @return
	 */
	int delExchangeItem(Long exchangeId);
}