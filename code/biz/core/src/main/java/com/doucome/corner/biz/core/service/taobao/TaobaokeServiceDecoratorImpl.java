package com.doucome.corner.biz.core.service.taobao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeReportMemberDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeShopDTO;
import com.doucome.corner.biz.core.taobao.model.TaobaokeDate;
import com.doucome.corner.biz.core.taobao.model.TaokeCaturlCondition;
import com.doucome.corner.biz.core.taobao.model.TaokeItemSearchCondition;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.ReflectUtils;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.TaobaokeItem;
import com.taobao.api.domain.TaobaokeReport;
import com.taobao.api.domain.TaobaokeReportMember;
import com.taobao.api.domain.TaobaokeShop;
import com.taobao.api.request.TaobaokeCaturlGetRequest;
import com.taobao.api.request.TaobaokeItemsConvertRequest;
import com.taobao.api.request.TaobaokeItemsGetRequest;
import com.taobao.api.request.TaobaokeReportGetRequest;
import com.taobao.api.request.TaobaokeShopsConvertRequest;
import com.taobao.api.response.TaobaokeCaturlGetResponse;
import com.taobao.api.response.TaobaokeItemsConvertResponse;
import com.taobao.api.response.TaobaokeItemsGetResponse;
import com.taobao.api.response.TaobaokeReportGetResponse;
import com.taobao.api.response.TaobaokeShopsConvertResponse;

/**
 * 淘宝开放平台接口封装
 * @author shenjia.caosj 2012-2-24
 *
 */
public class TaobaokeServiceDecoratorImpl extends AbstractTaobaoService implements TaobaokeServiceDecorator  {
	
	private static final Log taobaoLog = LogFactory.getLog(LogConstant.taobao_log) ;
	
	@Override
	public List<TaobaokeShopDTO> conventShops(String[] shorpIds , String outCode , String[] fields) throws TaobaoRemoteException  {
		
		TaobaokeShopsConvertRequest req = new TaobaokeShopsConvertRequest();
		req.setFields(ArrayStringUtils.toString(fields)) ;
		req.setSids(ArrayStringUtils.toString(shorpIds));
		req.setNick(nickname);
		req.setOuterCode(outCode) ;
		//req.setPid(taokeId) ;
		req.setOuterCode(outCode);
		try{
			TaobaoClient taobaoClient = newTaobaokeClient() ;
			TaobaokeShopsConvertResponse response = taobaoClient.execute(req);
			boolean isSuccess = response.isSuccess() ;
			if(isSuccess){
				List<TaobaokeShopDTO> itemDTOs = new ArrayList<TaobaokeShopDTO>() ;
				List<TaobaokeShop> items =  response.getTaobaokeShops() ;
				if(!CollectionUtils.isEmpty(items)){
					for(TaobaokeShop item : items){
						TaobaokeShopDTO dto = new TaobaokeShopDTO() ;
						dto.fromTaobaokeShop(item) ;
						itemDTOs.add(dto) ;
					}
				}
				return itemDTOs ;
			}
			if(taobaoLog.isErrorEnabled()){
				taobaoLog.error("input [" + ArrayStringUtils.toString(shorpIds) + "|" + outCode +  "]  response : " + response.getBody()) ;
			} 
			throwTaobaoErrorResponse(response) ;
			return null ;
		}catch(ApiException e){
			throw new TaobaoRemoteException(e.getErrMsg() , e , e.getErrCode()) ;
		}
	}

	@Override
	public List<TaobaokeItemDTO> conventItems(String[] itemIds , String outCode ,  String[] fields) throws TaobaoRemoteException  {
		

		TaobaokeItemsConvertRequest req = new TaobaokeItemsConvertRequest();
		req.setFields(ArrayStringUtils.toString(fields)) ;
		req.setNick(nickname);
		if(StringUtils.isNotBlank(outCode)){
			req.setOuterCode(outCode);
		}
		req.setNumIids(ArrayStringUtils.toString(itemIds));
		//req.setPid(ni);
		//req.setIsMobile(true);
		try {
			TaobaoClient taobaoClient = newTaobaokeClient() ;
			TaobaokeItemsConvertResponse response = taobaoClient.execute(req);
			boolean isSuccess = response.isSuccess() ;
			if(isSuccess){
				List<TaobaokeItemDTO> itemDTOs = new ArrayList<TaobaokeItemDTO>() ;
				List<TaobaokeItem> items =  response.getTaobaokeItems() ;
				if(!CollectionUtils.isEmpty(items)){
					for(TaobaokeItem item : items){
						TaobaokeItemDTO dto = new TaobaokeItemDTO(item) ;
						itemDTOs.add(dto) ;
					}
				}
				return itemDTOs ;
				
			}
			if(taobaoLog.isErrorEnabled()){
				taobaoLog.error("input [" + ArrayStringUtils.toString(itemIds) + "|" + outCode +  "]  response : " + response.getBody()) ;
			} 
			throwTaobaoErrorResponse(response) ;
			return null ;
		} catch (ApiException e) {
			throw new TaobaoRemoteException(e.getErrMsg() , e , e.getErrCode()) ;
		}
		
	}

	@Override
	public TaobaokeItemDTO conventItem(String itemId,  String outCode , String[] fields) throws TaobaoRemoteException  {
		List<TaobaokeItemDTO> list = conventItems(new String[]{itemId}, outCode , fields) ;
		if(CollectionUtils.isEmpty(list)){
			return null ;
		}
		return list.iterator().next() ;
	}

	@Override
	public TaobaokeShopDTO conventShop(String shopId, String outCode, String[] fields) throws TaobaoRemoteException {
		List<TaobaokeShopDTO> list = conventShops(new String[]{shopId}, outCode , fields) ;
		if(CollectionUtils.isEmpty(list)){
			return null ;
		}
		return list.iterator().next() ;
	}

	@Override
	public QueryResult<TaobaokeReportMemberDTO> getReport(TaobaokeDate date, String[] fields ,Pagination pagination) {
		TaobaokeReportGetRequest req = new TaobaokeReportGetRequest();
		req.setFields(ArrayStringUtils.toString(fields));
		req.setDate(date.getDateFormat());
		req.setPageNo(Long.valueOf(pagination.getPage()));
		req.setPageSize(Long.valueOf(pagination.getSize()));
		
		TaobaoClient taobaoClient = newTaobaokeClient() ;
		try{
			TaobaokeReportGetResponse response = taobaoClient.execute(req , topSession);
			boolean isSuccess = response.isSuccess() ;
			if(isSuccess){
				
				TaobaokeReport report = response.getTaobaokeReport() ;
				List<TaobaokeReportMember> items = report.getTaobaokeReportMembers() ;
				List<TaobaokeReportMemberDTO> itemDTOs = new ArrayList<TaobaokeReportMemberDTO>() ;
				long totalResults = report.getTotalResults() ;
				if(!CollectionUtils.isEmpty(items)){
					for(TaobaokeReportMember item : items){
						TaobaokeReportMemberDTO dto = new TaobaokeReportMemberDTO(item) ;
						itemDTOs.add(dto) ;
					}
				}
				return new QueryResult<TaobaokeReportMemberDTO>(itemDTOs , pagination , totalResults) ;
			}
			if(taobaoLog.isErrorEnabled()){
				taobaoLog.error("input [" + date.getDateFormat() + "] response : " + response.getBody()) ;
			} 
			throwTaobaoErrorResponse(response) ;
			return null ;
		}catch(ApiException e){
			throw new TaobaoRemoteException(e.getErrMsg() , e , e.getErrCode()) ;
		}
	}

	@Override
	public QueryResult<TaobaokeItemDTO> getItems(TaokeItemSearchCondition condition , String[] fields, Pagination pagination) throws TaobaoRemoteException {

		TaobaokeItemsGetRequest req = new TaobaokeItemsGetRequest();
		ReflectUtils.reflectTo(condition , req) ;
		req.setFields(ArrayStringUtils.toString(fields)) ;
		req.setNick(nickname);
		req.setPageNo(Long.valueOf(pagination.getPage()));
		req.setPageSize(Long.valueOf(pagination.getSize()));
		TaobaoClient taobaoClient = newTaobaokeClient() ;
		try {
			TaobaokeItemsGetResponse response = taobaoClient.execute(req) ;
			boolean isSuccess = response.isSuccess() ;
			if(isSuccess){
				List<TaobaokeItem> items = response.getTaobaokeItems() ;
				
				List<TaobaokeItemDTO> itemDTOs = new ArrayList<TaobaokeItemDTO>() ;
				Long totalResults = response.getTotalResults() ;
				if(totalResults == null){
					totalResults = 0L ;
				}
				if(!CollectionUtils.isEmpty(items)){
					for(TaobaokeItem item : items){
						TaobaokeItemDTO dto = new TaobaokeItemDTO(item) ;
						dto.setTitleHighlight(dto.getTitle()) ;
						String title = StringUtils.replace(dto.getTitle(), "<span class=H>", "") ;
						title = StringUtils.replace(title, "</span>", "") ;
						dto.setTitle(title) ;
						itemDTOs.add(dto) ;
					}
				}
				return new QueryResult<TaobaokeItemDTO>(itemDTOs , pagination ,totalResults) ;
			}
			if(taobaoLog.isErrorEnabled()){
				taobaoLog.error("input [" + condition + "] response : " + response.getBody()) ;
			} 
			throwTaobaoErrorResponse(response) ;
			return null ;
		} catch (ApiException e) {
			throw new TaobaoRemoteException(e.getErrMsg() , e , e.getErrCode()) ;
		}
	
	}

	@Override
	public String getCaturl(TaokeCaturlCondition condition) {
		TaobaokeCaturlGetRequest req = new TaobaokeCaturlGetRequest();
		ReflectUtils.reflectTo(condition, req) ;
		req.setNick(nickname);
		TaobaoClient taobaoClient = newTaobaokeClient() ;
		try {
			TaobaokeCaturlGetResponse response = taobaoClient.execute(req);
			
			boolean isSuccess = response.isSuccess() ;
			if(isSuccess){
				TaobaokeItem item = response.getTaobaokeItem() ;
				if(item == null){
					return null ;
				}
				return item.getTaobaokeCatClickUrl() ;
			}
			if(taobaoLog.isErrorEnabled()){
				taobaoLog.error("input [" + condition + "] response : " + response.getBody()) ;
			} 
			throwTaobaoErrorResponse(response) ;
			return null ;
			
		} catch (ApiException e) {
			throw new TaobaoRemoteException(e.getErrMsg() , e , e.getErrCode()) ;
		}
	}

	
	
	
}
