package com.doucome.corner.web.bops.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.encrypt.EncryptBean;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.dataobject.BopsAdminDO;
import com.doucome.corner.web.bops.authz.BopsAuthz;
import com.doucome.corner.web.bops.authz.model.BopsAuthzTemp;
import com.doucome.corner.web.bops.context.AuthzContext;
import com.doucome.corner.web.bops.context.AuthzContextHolder;
import com.doucome.corner.web.common.cookie.BopsCookieNameConstants;
import com.doucome.corner.web.common.cookie.CookieHelper;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

/**
 * @author shenjia.caosj 2012-3-19
 */
@SuppressWarnings("serial")
public class SetContextInterceptor extends AbstractInterceptor {

    private static final Log log                 = LogFactory.getLog(SetContextInterceptor.class);
    @Autowired
    private EncryptBean      cookieEncryptBean;
    @Autowired
    private BopsAuthz        bopsAuthz;
    private String           domain;
    private static final int EXPIRY_TIME_YEAR    = 3600 * 24 * 365;
    private static final int EXPIRY_TIME_SESSION = -1;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        // ActionContext context = invocation.getInvocationContext();
        prepare();
        try {
            invocation.addPreResultListener(new PreResultListener() {

                @Override
                public void beforeResult(ActionInvocation invocation, String resultCode) {
                    change(ServletActionContext.getResponse());
                }
            });
            return invocation.invoke();
        } finally {
            clean();
        }
    }

    public void prepare() {
        Cookie[] cookies = ServletActionContext.getRequest().getCookies();
        AuthzContext authzContext = AuthzContextHolder.getContext();

        String tempEntrypt = CookieHelper.readCookie(cookies, BopsCookieNameConstants.BOPS_TEMP);
        if (StringUtils.isNotBlank(tempEntrypt)) {
            String tempJson = cookieEncryptBean.decode(tempEntrypt);
            try {
                BopsAuthzTemp authzTemp = JacksonHelper.fromJSON(tempJson, BopsAuthzTemp.class);
                if (authzTemp != null) {
                    if (authzTemp.checkSignature()) {
                        authzContext.setAdminId(authzTemp.getAdminId());
                        authzContext.setAuthentication(true, false);
                    } else {
                        CookieHelper.writeCookie(ServletActionContext.getResponse(), domain,
                                                 BopsCookieNameConstants.BOPS_TEMP, null, EXPIRY_TIME_SESSION);
                    }
                }
            } catch (Exception e) {
                log.error("Analytic BopsTemp error ! " + e.getMessage(), e);
            }
        }
    }

    /**
     * 更新cookie
     * 
     * @param response
     */
    public void change(HttpServletResponse response) {
        AuthzContext authzContext = AuthzContextHolder.getContext();
        if (authzContext.isTouch()) {
            if (authzContext.isAuthentication()) {
                BopsAuthzTemp authzTemp = new BopsAuthzTemp();
                authzTemp.setLoginTime(System.currentTimeMillis());
                authzTemp.setAdminId(authzContext.getAdminId());
                BopsAdminDO adminDO = bopsAuthz.getAdminDO();
                if (adminDO != null) {
                    authzTemp.setPassword(adminDO.getPassword());
                }
                authzTemp.generateSignature();
                String ddzTemp = cookieEncryptBean.encode(authzTemp.toString());
                CookieHelper.writeCookie(response, domain, BopsCookieNameConstants.BOPS_TEMP, ddzTemp,
                                         EXPIRY_TIME_SESSION);
            } else {
                CookieHelper.writeCookie(response, domain, BopsCookieNameConstants.BOPS_TEMP, null, EXPIRY_TIME_SESSION);
            }
        }
    }

    /**
     * 线程数据清理
     */
    public void clean() {
        AuthzContextHolder.setContext(null);
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
