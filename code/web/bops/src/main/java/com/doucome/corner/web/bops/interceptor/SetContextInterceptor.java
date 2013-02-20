package com.doucome.corner.web.bops.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.EnvConstant;
import com.doucome.corner.biz.core.encrypt.EncryptBean;
import com.doucome.corner.biz.core.exception.EncryptException;
import com.doucome.corner.biz.core.utils.EnvPropertiesUtil;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.core.utils.TaobaoWidgetUtils;
import com.doucome.corner.biz.dal.dataobject.BopsAdminDO;
import com.doucome.corner.web.bops.authz.BopsAuthz;
import com.doucome.corner.web.bops.authz.model.BopsAuthzTemp;
import com.doucome.corner.web.bops.context.AuthzContext;
import com.doucome.corner.web.bops.context.AuthzContextHolder;
import com.doucome.corner.web.common.constant.CookieConstants;
import com.doucome.corner.web.common.cookie.BopsCookieNameConstants;
import com.doucome.corner.web.common.cookie.CookieHelper;
import com.doucome.corner.web.common.cookie.TaobaoCoolieNameConstant;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

/**
 * @author langben 2012-3-19
 */
@SuppressWarnings("serial")
public class SetContextInterceptor extends AbstractInterceptor {

    private static final Log log = LogFactory.getLog(SetContextInterceptor.class);
    @Autowired
    private EncryptBean      cookieEncryptBean;
    @Autowired
    private BopsAuthz        bopsAuthz;
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

        String tempEntrypt = CookieHelper.readCookie(cookies, BopsCookieNameConstants.BOPS_TEMP);
        if (StringUtils.isNotBlank(tempEntrypt)) {
            try {
                String tempJson = cookieEncryptBean.decode(tempEntrypt);
                BopsAuthzTemp authzTemp = JacksonHelper.fromJSON(tempJson, BopsAuthzTemp.class);
                if (authzTemp != null) {
                    if (authzTemp.checkSignature()) {
                        authzContext.setAdminId(authzTemp.getAdminId());
                        authzContext.setAuthentication(true, false);
                    } else {
                        CookieHelper.writeCookie(ServletActionContext.getResponse(), domain,
                                                 BopsCookieNameConstants.BOPS_TEMP, null,
                                                 CookieConstants.EXPIRY_TIME_SESSION);
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
                                         CookieConstants.EXPIRY_TIME_SESSION);
            } else {
                CookieHelper.writeCookie(response, domain, BopsCookieNameConstants.BOPS_TEMP, null,
                                         CookieConstants.EXPIRY_TIME_SESSION);
            }
        }
        
        //每次都植入 淘宝开放平台的相关参数
//        String timestamp = TaobaoWidgetUtils.timestamp() ;
//        String appKey = EnvPropertiesUtil.getProperty(EnvConstant.CORNER_API_TAOBAO_APPKEY) ;
//        String secret = EnvPropertiesUtil.getProperty(EnvConstant.CORNER_API_TAOBAO_SECRET) ;
//        String sign;
//		try {
//			sign = TaobaoWidgetUtils.sign(secret, appKey, timestamp);
//		} catch (EncryptException e) {
//			sign = null ;
//		}
//        CookieHelper.writeCookie(ServletActionContext.getResponse(), domain, TaobaoCoolieNameConstant.SIGN, sign, CookieConstants.EXPIRY_TIME_SESSION) ;
//        CookieHelper.writeCookie(ServletActionContext.getResponse(), domain, TaobaoCoolieNameConstant.TIMESTAMP, timestamp, CookieConstants.EXPIRY_TIME_SESSION) ;
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
