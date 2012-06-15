package com.doucome.corner.biz.core.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.taobao.constant.TaokeErrCodeConstant;

public class TaokeApiLimitRetryInterceptor implements MethodInterceptor {
	
	private int retryCount = 1 ;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		try {
			return invocation.proceed();
		}catch(TaobaoRemoteException e){
			if(StringUtils.equals(e.getErrCode() , TaokeErrCodeConstant.app_call_limited)){
				//÷ÿ ‘1¥Œ
				return retry(invocation) ;
			}else{
				throw e ;
			}
		}
	}

	private Object retry(MethodInvocation invocation) throws Throwable {
		return invocation.proceed() ;
	}
	
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}
 
	
	
}
