package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;

/**
 * �Ա���Ʒ����.����ʱ��1min.
 * @author ze2200
 *
 */
public interface DcTaobaoItemCache {
	/**
	 * �����Ա���Ʒ����.����ʱ��1min.
	 * @param tbItem
	 * @return
	 */
	boolean putItem(TaobaoItemDTO tbItem);
	/**
	 * �����Կ���Ʒ����.
	 * @param taokeItem
	 * @return
	 */
	boolean putTaokeItem(TaobaokeItemDTO taokeItem, String outCode);
	/**
	 * ��ȡ�Ա���Ʒ
	 * @param tbItemId
	 * @return
	 */
	TaobaoItemDTO getItem(String tbItemId);
	/**
	 * ��ȡ�Կ���Ʒ.
	 * @param tbItemId
	 * @return
	 */
	TaobaokeItemDTO getTaokeItem(String tbItemId, String outCode);
}
