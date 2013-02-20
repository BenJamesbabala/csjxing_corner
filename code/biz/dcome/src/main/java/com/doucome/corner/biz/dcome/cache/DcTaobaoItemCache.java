package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;

/**
 * 淘宝商品缓存.缓存时间1min.
 * @author ze2200
 *
 */
public interface DcTaobaoItemCache {
	/**
	 * 设置淘宝商品缓存.缓存时间1min.
	 * @param tbItem
	 * @return
	 */
	boolean putItem(TaobaoItemDTO tbItem);
	/**
	 * 设置淘客商品缓存.
	 * @param taokeItem
	 * @return
	 */
	boolean putTaokeItem(TaobaokeItemDTO taokeItem, String outCode);
	/**
	 * 获取淘宝商品
	 * @param tbItemId
	 * @return
	 */
	TaobaoItemDTO getItem(String tbItemId);
	/**
	 * 获取淘客商品.
	 * @param tbItemId
	 * @return
	 */
	TaobaokeItemDTO getTaokeItem(String tbItemId, String outCode);
}
