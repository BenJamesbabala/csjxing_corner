package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.taobao.constant.TaokeItemConst;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;

public interface DcTaobaokeService {

	/**
	 * �Ա���Ʒת��
	 * @param itemId ��ƷID
	 * @param outCode �Զ������
	 * @param fields ��ʾ�ֶ�, see {@link TaokeItemConst}
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
