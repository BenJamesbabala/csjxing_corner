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
 * 淘宝开放平台接口封装
 * @author shenjia.caosj 2012-2-24
 *
 */
public interface TaobaokeServiceDecorator {
	
	/**
	 * 店铺转换
	 * @param shorpIds 店铺ID
	 * @param outCode 自定义参数
	 * @param fields显示字段, see {@link TaokeShopConst}
	 * @return
	 */
	List<TaobaokeShopDTO> conventShops(String[] shorpIds , String outCode ,String[] fields) throws TaobaoRemoteException  ;
	
	/**
	 * 店铺转换
	 * @param shopId 店铺ID
	 * @param outCode 自定义参数
	 * @param fields显示字段, see {@link TaokeShopConst}
	 * @return
	 */
	TaobaokeShopDTO conventShop(String shopId , String outCode , String[] fields) throws TaobaoRemoteException  ;
		
	/**
	 * 淘宝商品转换
	 * @param itemIds 商品ID
	 * @param outCode 自定义参数
	 * @param fields 显示字段, see {@link TaokeItemConst}
	 * @return
	 */
	List<TaobaokeItemDTO> conventItems(String[] itemIds ,  String outCode , String[] fields) throws TaobaoRemoteException  ;
	
	/**
	 * 淘宝商品转换
	 * @param itemId 商品ID
	 * @param outCode 自定义参数
	 * @param fields 显示字段, see {@link TaokeItemConst}
	 * @return
	 */
	TaobaokeItemDTO conventItem(String itemId ,  String outCode , String[] fields) throws TaobaoRemoteException  ;
	
	/**
	 * 淘宝客报表查询 
	 * @param date 查询时间（一天），yyyyMMdd
	 *  @param fields 显示字段, see {@link TaokeReportMembConst}
	 * @param pagination 分页
	 * @return 结果
	 */
	QueryResult<TaobaokeReportMemberDTO> getReport(TaobaokeDate date ,String[] fields , Pagination pagination) throws TaobaoRemoteException  ;
	
	
	/**
	 * 淘宝客查询商品
	 * @param condition 查询条件
	 * @param fields 显示字段, see {@link TaokeItemConst}
	 * @param pagination 分页
	 * @return 结果
	 * @throws TaobaoRemoteException
	 */
	QueryResult<TaobaokeItemDTO> getItems(TaokeItemSearchCondition condition , String[] fields , Pagination pagination) throws TaobaoRemoteException  ;
	
	/**
	 * 
	 * @return taobaoke_cat_click_url ,null if not exists
	 */
	String getCaturl(TaokeCaturlCondition condition ) ;
	
	/**
	 * 查询S8链接
	 * @param keyword
	 * @param outCode
	 * @return
	 */
	String getListurl(String keyword , String outCode) ;
	
}
