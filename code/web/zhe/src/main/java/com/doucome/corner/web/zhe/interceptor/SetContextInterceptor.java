package com.doucome.corner.web.zhe.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.doucome.corner.biz.core.encrypt.EncryptBean;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.web.common.cookie.CookieHelper;
import com.doucome.corner.web.common.cookie.DdzCookieNameConstants;
import com.doucome.corner.web.zhe.action.TBLoginPassAction;
import com.doucome.corner.web.zhe.authz.DdzAuthz;
import com.doucome.corner.web.zhe.authz.model.DdzAuthzTemp;
import com.doucome.corner.web.zhe.context.AuthzContext;
import com.doucome.corner.web.zhe.context.AuthzContextHolder;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

/**
 * @author shenjia.caosj 2012-3-19
 */
@SuppressWarnings("serial")
public class SetContextInterceptor extends AbstractInterceptor {

    private static final Log log                 = LogFactory.getLog(SetContextInterceptor.class);
    private EncryptBean      cookieEncryptBean;
    private DdzAuthz         ddzAuthz;
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

        // 处理Cookie
        String alipayIdEntrypt = CookieHelper.readCookie(cookies, DdzCookieNameConstants.ALIPAY_ID);
        if (StringUtils.isNotBlank(alipayIdEntrypt)) {
            String alipayId = cookieEncryptBean.decode(alipayIdEntrypt);
            if (StringUtils.isNotBlank(alipayId)) {
                authzContext.setAlipayId(alipayId);
            }
        }

        String ddzTempEntrypt = CookieHelper.readCookie(cookies, DdzCookieNameConstants.DDZ_TEMP);
        if (StringUtils.isNotBlank(ddzTempEntrypt)) {
            String ddzTempJson = cookieEncryptBean.decode(ddzTempEntrypt);
            try {
                DdzAuthzTemp ddzTemp = JacksonHelper.fromJSON(ddzTempJson, DdzAuthzTemp.class);
                if (ddzTemp != null) {
                    if (ddzTemp.checkSignature()) {
                        authzContext.setUid(ddzTemp.getUid());
                        authzContext.setAuthentication(true, false);
                    } else {
                        CookieHelper.writeCookie(ServletActionContext.getResponse(), domain,
                                                 DdzCookieNameConstants.DDZ_TEMP, null, EXPIRY_TIME_SESSION);
                    }
                }
            } catch (Exception e) {
                log.error("Analytic DdzTemp error ! " + e.getMessage(), e);
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
            String alipayId = authzContext.getAlipayId();
            String alipayIdEntrypt = cookieEncryptBean.encode(alipayId);
            if (StringUtils.isNotBlank(alipayIdEntrypt)) {
                CookieHelper.writeCookie(response, domain, DdzCookieNameConstants.ALIPAY_ID, alipayIdEntrypt,
                                         EXPIRY_TIME_YEAR);
            }

            if (authzContext.isAuthentication()) {
                DdzAuthzTemp ddzAuthzTemp = new DdzAuthzTemp();
                ddzAuthzTemp.setLoginTime(System.currentTimeMillis());
                ddzAuthzTemp.setUid(authzContext.getUid());

                DdzUserDO userDO = ddzAuthz.getUser();
                if (userDO != null) {
                    ddzAuthzTemp.setPassword(userDO.getMd5Password());
                }
                ddzAuthzTemp.generateSignature();
                String ddzTemp = cookieEncryptBean.encode(ddzAuthzTemp.toString());
                CookieHelper.writeCookie(response, domain, DdzCookieNameConstants.DDZ_TEMP, ddzTemp,
                                         EXPIRY_TIME_SESSION);
            } else {
                CookieHelper.writeCookie(response, domain, DdzCookieNameConstants.DDZ_TEMP, null, EXPIRY_TIME_SESSION);
            }
        }
    }

    /**
     * 线程数据清理
     */
    public void clean() {
        AuthzContextHolder.setContext(null);
    }

    public void setCookieEncryptBean(EncryptBean cookieEncryptBean) {
        this.cookieEncryptBean = cookieEncryptBean;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setDdzAuthz(DdzAuthz ddzAuthz) {
        this.ddzAuthz = ddzAuthz;
    }

}
