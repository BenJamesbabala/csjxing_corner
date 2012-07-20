package com.doucome.corner.biz.core.service.taobao;

import java.util.List;

import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoUserDTO;

/**
 * �Ա������ӿ�
 * @author shenjia.caosj 2012-3-2
 *
 */
public interface TaobaoServiceDecorator {

//	/**
//	 * �ӳ�Session����Чʱ�䣬�����Ա�Ŀǰ����Sessionˢ��һ�κ�ʧЧʱ��Ϊ
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
