package com.doucome.corner.biz.core.service.taobao;

import java.util.List;

import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.taobao.constant.TaokeItemConst;
import com.doucome.corner.biz.core.taobao.constant.TaokeReportMembConst;
import com.doucome.corner.biz.core.taobao.constant.TaokeShopConst;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeReportMemberDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeShopDTO;
import com.doucome.corner.biz.core.taobao.model.TaobaokeDate;
import com.doucome.corner.biz.core.taobao.model.TaokeCaturlCondition;
import com.doucome.corner.biz.core.taobao.model.TaokeItemSearchCondition;


/**
 * �Ա�����ƽ̨�ӿڷ�װ
 * @author shenjia.caosj 2012-2-24
 *
 */
public interface TaobaokeServiceDecorator {
	
	/**
	 * ����ת��
	 * @param shorpIds ����ID
	 * @param outCode �Զ������
	 * @param fields��ʾ�ֶ�, see {@link TaokeShopConst}
	 * @return
	 */
	List<TaobaokeShopDTO> conventShops(String[] shorpIds , String outCode ,String[] fields) throws TaobaoRemoteException  ;
	
	/**
	 * ����ת��
	 * @param shopId ����ID
	 * @param outCode �Զ������
	 * @param fields��ʾ�ֶ�, see {@link TaokeShopConst}
	 * @return
	 */
	TaobaokeShopDTO conventShop(String shopId , String outCode , String[] fields) throws TaobaoRemoteException  ;
		
	/**
	 * �Ա���Ʒת��
	 * @param itemIds ��ƷID
	 * @param outCode �Զ������
	 * @param fields ��ʾ�ֶ�, see {@link TaokeItemConst}
	 * @return
	 */
	List<TaobaokeItemDTO> conventItems(String[] itemIds ,  String outCode , String[] fields) throws TaobaoRemoteException  ;
	
	/**
	 * �Ա���Ʒת��
	 * @param itemId ��ƷID
	 * @param outCode �Զ������
	 * @param fields ��ʾ�ֶ�, see {@link TaokeItemConst}
	 * @return
	 */
	TaobaokeItemDTO conventItem(String itemId ,  String outCode , String[] fields) throws TaobaoRemoteException  ;
	
	/**
	 * �Ա��ͱ����ѯ 
	 * @param date ��ѯʱ�䣨һ�죩��yyyyMMdd
	 *  @param fields ��ʾ�ֶ�, see {@link TaokeReportMembConst}
	 * @param pagination ��ҳ
	 * @return ���
	 */
	QueryResult<TaobaokeReportMemberDTO> getReport(TaobaokeDate date ,String[] fields , Pagination pagination) throws TaobaoRemoteException  ;
	
	
	/**
	 * �Ա��Ͳ�ѯ��Ʒ
	 * @param condition ��ѯ����
	 * @param fields ��ʾ�ֶ�, see {@link TaokeItemConst}
	 * @param pagination ��ҳ
	 * @return ���
	 * @throws TaobaoRemoteException
	 */
	QueryResult<TaobaokeItemDTO> getItems(TaokeItemSearchCondition condition , String[] fields , Pagination pagination) throws TaobaoRemoteException  ;
	
	/**
	 * 
	 * @return taobaoke_cat_click_url ,null if not exists
	 */
	String getCaturl(TaokeCaturlCondition condition ) ;
	
	/**
	 * ��ѯS8����
	 * @param keyword
	 * @param outCode
	 * @return
	 */
	String getListurl(String keyword , String outCode) ;
	
}
