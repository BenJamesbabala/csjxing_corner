package com.doucome.corner.biz.dcome.service;

import java.math.BigDecimal;
import java.util.List;

import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.taobao.constant.TaokeItemConst;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;

public interface DcTaobaoService {

	/**
	 * �Կ���Ʒת��
	 * @param tbItemId ��ƷID
	 * @param outCode �Զ������
	 * @param fields ��ʾ�ֶ�, see {@link TaokeItemConst}
	 * @return
	 */
	TaobaokeItemDTO conventItem(String tbItemId ,  String outCode) throws TaobaoRemoteException ;
	
	
	/**
	 * ����ת���Կ���Ʒ
	 * @param tbItemIds 
	 * @param outCode
	 * @return
	 * @throws TaobaoRemoteException
	 */
	List<TaobaokeItemDTO> convertItems(String[] tbItemIds , String outCode) throws TaobaoRemoteException ;
	
	/**
	 * ��ȡ�Ա���Ʒ
	 * @param tbItemId
	 * @return
	 * @throws TaobaoRemoteException
	 */
	TaobaoItemDTO getTaobaoItem(String tbItemId) throws TaobaoRemoteException ;
	
	/**
	 * ��ȡ�Ա���Ʒ
	 * @param tbItemId
	 * @return
	 * @throws TaobaoRemoteException
	 */
	List<TaobaoItemDTO> getTaobaoItems(String[] tbItemIds) throws TaobaoRemoteException ;
	
	/**
	 * ��ȡ�Ա������۸�
	 * @param tbItemId
	 * @return
	 * @throws TaobaoRemoteException
	 */
	BigDecimal getItemPromoPrice(Long tbItemId) throws TaobaoRemoteException ;
	
}
