package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcExchangeItemCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcExchangeItemDO;

/**
 * 豆蔻商品
 * @author langben 2012-7-21
 *
 */
public interface DcExchangeItemDAO {
	/**
	 * 创建积分兑换商品.
	 * @param exItem
	 * @return
	 */
	Long insertExchangeItem(DcExchangeItemDO exItem) ;
	/**
	 * 
	 * @param id
	 * @return
	 */
	DcExchangeItemDO getExchangeItem(Long id);
	/**
	 * 
	 * @param condition
	 */
	List<DcExchangeItemDO> queryExchangeItemsNoPage(DcExchangeItemCondition condition);
	/**
	 * 
	 * @param condition
	 * @param page
	 * @return
	 */
	List<DcExchangeItemDO> queryExchangeItemsPage(DcExchangeItemCondition condition, int start, int size);
	/**
	 * 
	 * @param condition
	 * @return
	 */
	Integer countExchangeItems(DcExchangeItemCondition condition);
	/**
	 * 初始化兑换信息.
	 * @param exchangeItem
	 * @return
	 */
	Integer initExchangeInfo(DcExchangeItemDO exchangeItem);
	/**
	 * 更新兑换信息.sql里含有是否有足够的商品可兑换的业务逻辑.
	 * @param id
	 * @param exNum 兑换数量.
	 * @param userNick
	 * @return 可兑换的商品数量小于exNum：0; 其他：1
	 */
	int decExchangeNum(Long id, int exNum);
	/**
	 * 
	 * @param exchangeId
	 * @return
	 */
	int delExchangeItem(Long exchangeId);
}