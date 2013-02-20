package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcExchangeItemCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcExchangeItemDO;

/**
 * ��ޢ��Ʒ
 * @author langben 2012-7-21
 *
 */
public interface DcExchangeItemDAO {
	/**
	 * �������ֶһ���Ʒ.
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
	 * ��ʼ���һ���Ϣ.
	 * @param exchangeItem
	 * @return
	 */
	Integer initExchangeInfo(DcExchangeItemDO exchangeItem);
	/**
	 * ���¶һ���Ϣ.sql�ﺬ���Ƿ����㹻����Ʒ�ɶһ���ҵ���߼�.
	 * @param id
	 * @param exNum �һ�����.
	 * @param userNick
	 * @return �ɶһ�����Ʒ����С��exNum��0; ������1
	 */
	int decExchangeNum(Long id, int exNum);
	/**
	 * 
	 * @param exchangeId
	 * @return
	 */
	int delExchangeItem(Long exchangeId);
}