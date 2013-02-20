package com.doucome.corner.biz.core.service.taobao;

import java.util.List;

import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;

/**
 * �Ա������ӿ�
 * @author langben 2012-3-2
 *
 */
public interface TaobaoServiceDecorator {
	
	/**
	 * �õ�������Ʒ��Ϣ 
	 * @param numIid
	 * @return
	 */
	TaobaoItemDTO getItem(Long numIid , String[] fields) ;
	
	/**
	 * �����õ���Ʒ��Ϣ 
	 * @param numIids
	 * @param fields
	 * @return
	 */
	List<TaobaoItemDTO> getListItems(String[] numIids ,String[] fields) ;
}
