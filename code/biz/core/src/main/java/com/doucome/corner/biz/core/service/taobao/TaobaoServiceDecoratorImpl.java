package com.doucome.corner.biz.core.service.taobao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoUserDTO;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.User;
import com.taobao.api.request.ItemGetRequest;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.response.ItemGetResponse;
import com.taobao.api.response.UserGetResponse;

public class TaobaoServiceDecoratorImpl extends AbstractTaobaoService implements TaobaoServiceDecorator {

	private static final Log taobaoLog = LogFactory.getLog(LogConstant.taobao_log) ;

	@Override
	public TaobaoUserDTO getUser(String nick, String[] fields , String sessionKey) {

		UserGetRequest req = new UserGetRequest();
		req.setFields(ArrayStringUtils.toString(fields));
		req.setNick(nick);
		try{
			TaobaoClient client = taobaoClientWrapper.newClient() ;
			UserGetResponse response = client.execute(req , sessionKey);
			boolean isSuccess = response.isSuccess() ;
			if(isSuccess){
				User user = response.getUser() ;
				TaobaoUserDTO dto = new TaobaoUserDTO(user) ;
				return dto ;
			}
			if(taobaoLog.isErrorEnabled()){
				taobaoLog.error("input [" + nick + "|" + sessionKey +  "]  response : " + response.getBody()) ;
			} 
			throwTaobaoErrorResponse(response) ;
			return null ;
		}catch(ApiException e){
			throw new TaobaoRemoteException(e.getErrMsg() , e , e.getErrCode()) ;
		}
		
	}

	@Override
	public TaobaoItemDTO getItem(Long numIid , String[] fields) {
		
		ItemGetRequest req=new ItemGetRequest();
		req.setFields(ArrayStringUtils.toString(fields));
		req.setNumIid(numIid);
		try {
			TaobaoClient client= taobaoClientWrapper.newClient() ;
			ItemGetResponse response = client.execute(req);
			boolean isSuccess = response.isSuccess() ;
			if(isSuccess){
				Item item = response.getItem() ;
				TaobaoItemDTO  itemDTO = new TaobaoItemDTO(item) ;
				return itemDTO ;
			}
			
			if(taobaoLog.isErrorEnabled()){
				taobaoLog.error("input [" + numIid + "]  response : " + response.getMsg()) ;
			} 
			throwTaobaoErrorResponse(response) ;
			return null ;
		} catch (ApiException e){
			throw new TaobaoRemoteException(e.getErrMsg() , e , e.getErrCode()) ;
		}
		
	}

}
