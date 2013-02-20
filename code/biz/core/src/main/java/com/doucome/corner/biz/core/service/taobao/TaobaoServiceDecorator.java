package com.doucome.corner.biz.core.service.taobao;

import java.util.List;

import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;

/**
 * 淘宝基础接口
 * @author langben 2012-3-2
 *
 */
public interface TaobaoServiceDecorator {
	
	/**
	 * 得到单个商品信息 
	 * @param numIid
	 * @return
	 */
	TaobaoItemDTO getItem(Long numIid , String[] fields) ;
	
	/**
	 * 批量得到商品信息 
	 * @param numIids
	 * @param fields
	 * @return
	 */
	List<TaobaoItemDTO> getListItems(String[] numIids ,String[] fields) ;
}
