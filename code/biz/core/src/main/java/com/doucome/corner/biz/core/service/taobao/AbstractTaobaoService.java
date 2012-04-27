package com.doucome.corner.biz.core.service.taobao;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.TaobaoResponse;

public class AbstractTaobaoService implements BeanFactoryAware {

	private BeanFactory beanFactory ;
	
	protected String nickname ;
	
	protected String topSession ;
	
	protected TaobaoClient newTaobaoClient(){
		return ((TaobaoClient)beanFactory.getBean("taobaoClient")) ;
	}
	
	protected TaobaoClient newTaobaokeClient(){
		return ((TaobaoClient)beanFactory.getBean("taobaokeClient")) ;
	}
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setTopSession(String topSession) {
		this.topSession = topSession;
	}
		
	/**
	 * 对于调用taobao接口返回isSuccess=false的情况，统一调用该方法处理
	 * @param response
	 * @return
	 */
	protected Object throwTaobaoErrorResponse(TaobaoResponse response){
		if(!response.isSuccess()){
//			String errCode 	= response.getErrorCode() ;
//			String errMsg 	= response.getMsg() ;
//			String subCode 	= response.getSubCode() ;
//			String subMsg 	= response.getSubMsg() ;
			throw new TaobaoRemoteException(response.getBody()) ;
		}
		return null ;
	}
}
