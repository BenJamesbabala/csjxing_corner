package com.doucome.corner.web.bops.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;
import com.doucome.corner.web.bops.authz.BopsAuthz;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 类AuthzInterceptor.java的实现描述：认证拦截
 * 
 * @author ib 2012-4-22 上午12:42:36
 */
@SuppressWarnings("serial")
public class AuthzInterceptor extends AbstractInterceptor {

    @Autowired
    private BopsAuthz        bopsAuthz;
    
    private String ERROR_RESULT = "adminLogin";

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if (!bopsAuthz.isLogin()) {
            return ERROR_RESULT;
        }
        return invocation.invoke();
    }

}
