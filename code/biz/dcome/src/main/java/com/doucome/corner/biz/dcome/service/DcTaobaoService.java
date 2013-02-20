package com.doucome.corner.biz.dcome.service;

import java.math.BigDecimal;
import java.util.List;

import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.taobao.constant.TaokeItemConst;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;

public interface DcTaobaoService {

	/**
	 * 淘客商品转换
	 * @param tbItemId 商品ID
	 * @param outCode 自定义参数
	 * @param fields 显示字段, see {@link TaokeItemConst}
	 * @return
	 */
	TaobaokeItemDTO conventItem(String tbItemId ,  String outCode) throws TaobaoRemoteException ;
	
	
	/**
	 * 批量转换淘客商品
	 * @param tbItemIds 
	 * @param outCode
	 * @return
	 * @throws TaobaoRemoteException
	 */
	List<TaobaokeItemDTO> convertItems(String[] tbItemIds , String outCode) throws TaobaoRemoteException ;
	
	/**
	 * 获取淘宝商品
	 * @param tbItemId
	 * @return
	 * @throws TaobaoRemoteException
	 */
	TaobaoItemDTO getTaobaoItem(String tbItemId) throws TaobaoRemoteException ;
	
	/**
	 * 获取淘宝商品
	 * @param tbItemId
	 * @return
	 * @throws TaobaoRemoteException
	 */
	List<TaobaoItemDTO> getTaobaoItems(String[] tbItemIds) throws TaobaoRemoteException ;
	
	/**
	 * 获取淘宝促销价格
	 * @param tbItemId
	 * @return
	 * @throws TaobaoRemoteException
	 */
	BigDecimal getItemPromoPrice(Long tbItemId) throws TaobaoRemoteException ;
	
}
