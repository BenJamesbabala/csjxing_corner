package com.doucome.corner.biz.core.service.taobao;

import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.taobao.api.TaobaoResponse;

public class AbstractTaobaoService {

	protected String nickname ;
	
	protected TaobaoClientWrapper taobaoClientWrapper ;
	

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 对于调用taobao接口返回isSuccess=false的情况，统一调用该方法处理
	 * @param response
	 * @return
	 */
	protected Object throwTaobaoErrorResponse(TaobaoResponse response){
		if(!response.isSuccess()){
			String errCode 	= response.getErrorCode() ;
//			String errMsg 	= response.getMsg() ;
//			String subCode 	= response.getSubCode() ;
//			String subMsg 	= response.getSubMsg() ;
			
			throw new TaobaoRemoteException(response.getMsg() , errCode) ;
		}
		return null ;
	}

	public TaobaoClientWrapper getTaobaoClientWrapper() {
		return taobaoClientWrapper;
	}

	public void setTaobaoClientWrapper(TaobaoClientWrapper taobaoClientWrapper) {
		this.taobaoClientWrapper = taobaoClientWrapper;
	}
	
	
}
