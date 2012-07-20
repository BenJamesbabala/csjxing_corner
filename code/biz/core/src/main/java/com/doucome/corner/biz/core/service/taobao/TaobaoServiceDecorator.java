package com.doucome.corner.biz.core.service.taobao;

import java.util.List;

import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoUserDTO;

/**
 * 淘宝基础接口
 * @author shenjia.caosj 2012-3-2
 *
 */
public interface TaobaoServiceDecorator {

//	/**
//	 * 延长Session的有效时间，根据淘宝目前规则，Session刷新一次后失效时间为
//	 */
//	void refreshSession() ;
	
	/**
	 * 
	 * @param nick
	 * @param fields
	 * @return
	 */
	TaobaoUserDTO getUser(String nick , String[] fields ,String sessionKey ) ;
	
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
