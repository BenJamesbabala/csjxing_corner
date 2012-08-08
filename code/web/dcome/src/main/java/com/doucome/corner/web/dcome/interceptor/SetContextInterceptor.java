package com.doucome.corner.web.dcome.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.doucome.corner.biz.core.encrypt.EncryptBean;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dcome.enums.DcLoginSourceEnums;
import com.doucome.corner.web.common.constant.CookieConstants;
import com.doucome.corner.web.common.cookie.CookieHelper;
import com.doucome.corner.web.common.cookie.DcCookieNameConstant;
import com.doucome.corner.web.dcome.authz.DcAuthz;
import com.doucome.corner.web.dcome.authz.model.DcAuthzTemp;
import com.doucome.corner.web.dcome.context.AuthzContext;
import com.doucome.corner.web.dcome.context.AuthzContextHolder;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

/**
 * @author ib 2012-7-29 上午12:35:14
 */
@SuppressWarnings("serial")
public class SetContextInterceptor extends AbstractInterceptor {

    private static final Log log = LogFactory.getLog(SetContextInterceptor.class);
    private EncryptBean      cookieEncryptBean;
    private DcAuthz          dcAuthz;
    private String           domain;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        try {
            prepare();
        } catch (Exception e) {
            log.error("prepare authz fail.", e);
        }
        try {
            invocation.addPreResultListener(new PreResultListener() {

                @Override
                public void beforeResult(ActionInvocation invocation, String resultCode) {
                    try {
                        change(ServletActionContext.getResponse());
                    } catch (Exception e) {
                        log.error("change cookie fail.", e);
                    }
                }
            });
            return invocation.invoke();
        } finally {
            try {
                clean();
            } catch (Exception e) {
                log.error("clean context fail.", e);
            }
        }
    }

    public void prepare() {
        Cookie[] cookies = ServletActionContext.getRequest().getCookies();
        AuthzContext authzContext = AuthzContextHolder.getContext();

        // 处理DC_IS_PRIVATE
        String isPrivateStr = CookieHelper.readCookie(cookies, DcCookieNameConstant.DC_IS_PRIVATE);
        if (StringUtils.endsWithIgnoreCase(isPrivateStr, "true")) {
            authzContext.setPrivateUser(true);
        }

        // 处理DC_LOGIN_SOURCE
        String loginSource = CookieHelper.readCookie(cookies, DcCookieNameConstant.DC_LOGIN_SOURCE);
        authzContext.setLoginSource(DcLoginSourceEnums.get(loginSource));

        // 处理DC_PF_NICK
        String pfNickEntrypt = CookieHelper.readCookie(cookies, DcCookieNameConstant.DC_PF_NICK);
        if (StringUtils.isNotBlank(pfNickEntrypt)) {
            String pfNick = cookieEncryptBean.decode(pfNickEntrypt);
            if (StringUtils.isNotBlank(pfNick)) {
                authzContext.setPfNick(pfNick);
            }
        }

        // 处理DC_TEMP
        String dcTempEntrypt = CookieHelper.readCookie(cookies, DcCookieNameConstant.DC_TEMP);
        if (StringUtils.isNotBlank(dcTempEntrypt)) {
            try {
                String dcTempJson = cookieEncryptBean.decode(dcTempEntrypt);
                DcAuthzTemp dcTemp = JacksonHelper.fromJSON(dcTempJson, DcAuthzTemp.class);
                if (dcTemp != null) {
                    if (dcTemp.checkSignature()) {
                        authzContext.setUserId(dcTemp.getUserId());
                        authzContext.setExternalId(dcTemp.getExternalId());
                        authzContext.setOpenKey(dcTemp.getOpenKey());
                        authzContext.setAuthentication(true, false);
                    } else {
                        CookieHelper.writeCookie(ServletActionContext.getResponse(), domain,
                                                 DcCookieNameConstant.DC_TEMP, null,
                                                 CookieConstants.EXPIRY_TIME_SESSION);
                    }
                }
            } catch (Exception e) {
                log.error("Analytic DcTemp error ! " + e.getMessage(), e);
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
            try {
                if (authzContext.isAuthentication()) {
                    String pfNick = authzContext.getPfNick();
                    String pfNickEntrypt = cookieEncryptBean.encode(pfNick);
                    if (StringUtils.isNotBlank(pfNickEntrypt)) {
                        writeSessionCookie(DcCookieNameConstant.DC_PF_NICK, pfNickEntrypt);
                    }

                    DcAuthzTemp dcAuthzTemp = new DcAuthzTemp();
                    dcAuthzTemp.setLoginTime(System.currentTimeMillis());
                    dcAuthzTemp.setUserId(authzContext.getUserId());
                    dcAuthzTemp.setExternalId(authzContext.getExternalId());
                    dcAuthzTemp.setOpenKey(authzContext.getOpenKey());
                    dcAuthzTemp.generateSignature();
                    String dcTemp = cookieEncryptBean.encode(dcAuthzTemp.toString());
                    writeSessionCookie(DcCookieNameConstant.DC_TEMP, dcTemp);
                    writeSessionCookie(DcCookieNameConstant.DC_USER_ID, String.valueOf(authzContext.getUserId()));
                    writeSessionCookie(DcCookieNameConstant.DC_LOGIN_SOURCE, authzContext.getLoginSource().getValue());
                } else {
                    writeSessionCookie(DcCookieNameConstant.DC_TEMP, null);
                    writeSessionCookie(DcCookieNameConstant.DC_PF_NICK, null);
                    writeSessionCookie(DcCookieNameConstant.DC_LOGIN_SOURCE, null);
                    writeSessionCookie(DcCookieNameConstant.DC_USER_ID, null);
                }
            } catch (Exception e) {
                log.error("change cookie error ! " + e.getMessage(), e);
            }
        }
    }

    private void writeSessionCookie(String name, String value) {
        CookieHelper.writeCookie(ServletActionContext.getResponse(), domain, name, value,
                                 CookieConstants.EXPIRY_TIME_SESSION);
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

    public void setDcAuthz(DcAuthz dcAuthz) {
        this.dcAuthz = dcAuthz;
    }

}
