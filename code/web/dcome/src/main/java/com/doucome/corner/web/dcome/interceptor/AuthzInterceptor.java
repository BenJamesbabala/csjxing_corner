package com.doucome.corner.web.dcome.interceptor;

import com.doucome.corner.web.dcome.authz.DcAuthz;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 类AuthzInterceptor.java的实现描述：认证拦截
 * 
 * @author ib 2012-4-22 上午12:42:36
 */
@SuppressWarnings("serial")
public class AuthzInterceptor extends AbstractInterceptor {

    private DcAuthz dcAuthz;

    private String  errorResult;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if (!dcAuthz.isLogin()) {
            return errorResult;
        }
        return invocation.invoke();
    }

    public void setDcAuthz(DcAuthz dcAuthz) {
        this.dcAuthz = dcAuthz;
    }

    public void setErrorResult(String errorResult) {
        this.errorResult = errorResult;
    }

}
