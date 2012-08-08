package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.taobao.constant.TaokeItemConst;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;

public interface DcTaobaokeService {

	/**
	 * 淘宝商品转换
	 * @param itemId 商品ID
	 * @param outCode 自定义参数
	 * @param fields 显示字段, see {@link TaokeItemConst}
	 * @return
	 */
	TaobaokeItemDTO conventItem(String itemId ,  String outCode) throws TaobaoRemoteException ;
	/**
	 * 
	 * @param itemId
	 * @return
	 * @throws TaobaoRemoteException
	 */
	TaobaoItemDTO getTaobaoItem(String itemId) throws TaobaoRemoteException ;
	/**
	 * 
	 * @param itemId
	 * @param fields
	 * @return
	 * @throws TaobaoRemoteException
	 */
	TaobaoItemDTO getTaobaoItem(String itemId, String[] fields) throws TaobaoRemoteException;
}
