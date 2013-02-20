package com.doucome.corner.web.zhe.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.doucome.corner.biz.core.constant.EnvConstant;
import com.doucome.corner.biz.core.encrypt.EncryptBean;
import com.doucome.corner.biz.core.exception.EncryptException;
import com.doucome.corner.biz.core.utils.EnvPropertiesUtil;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.core.utils.TaobaoWidgetUtils;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.web.common.constant.CookieConstants;
import com.doucome.corner.web.common.cookie.CookieHelper;
import com.doucome.corner.web.common.cookie.DdzCookieNameConstants;
import com.doucome.corner.web.common.cookie.TaobaoCoolieNameConstant;
import com.doucome.corner.web.zhe.authz.DdzAuthz;
import com.doucome.corner.web.zhe.authz.model.DdzAuthzTemp;
import com.doucome.corner.web.zhe.context.AuthzContext;
import com.doucome.corner.web.zhe.context.AuthzContextHolder;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;
import com.taobao.api.request.TaobaokeWidgetItemsConvertRequest;

/**
 * @author langben 2012-3-19
 */
@SuppressWarnings("serial")
public class SetContextInterceptor extends AbstractInterceptor {

    private static final Log log = LogFactory.getLog(SetContextInterceptor.class);
    private EncryptBean      cookieEncryptBean;
    private DdzAuthz         ddzAuthz;
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

        // 处理ALIPAY_ID
        String alipayIdEntrypt = CookieHelper.readCookie(cookies, DdzCookieNameConstants.ALIPAY_ID);
        if (StringUtils.isNotBlank(alipayIdEntrypt)) {
            String alipayId = cookieEncryptBean.decode(alipayIdEntrypt);
            if (StringUtils.isNotBlank(alipayId)) {
                authzContext.setAlipayId(alipayId);
            }
        }

        // 处理LAST_LOGIN_ID
        String lastLoginId = CookieHelper.readCookie(cookies, DdzCookieNameConstants.LAST_LOGIN_ID);
        if (StringUtils.isNotBlank(lastLoginId)) {
            try {
                lastLoginId = URLDecoder.decode(lastLoginId, "GBK");
            } catch (UnsupportedEncodingException e) {
                log.error("decode error:" + lastLoginId, e);
            } catch (Exception e) {
                log.error("decode error:" + lastLoginId, e);
            }
            authzContext.setLoginId(lastLoginId);
        }

        // 处理DDZ_TEMP
        String isPrivateStr = CookieHelper.readCookie(cookies, DdzCookieNameConstants.DDZ_IS_PRIVATE);
        if (StringUtils.endsWithIgnoreCase(isPrivateStr, "true")) {
            authzContext.setPrivateUser(true);
        }

        // 处理DDZ_TEMP
        String ddzTempEntrypt = CookieHelper.readCookie(cookies, DdzCookieNameConstants.DDZ_TEMP);
        if (StringUtils.isNotBlank(ddzTempEntrypt)) {
            try {
                String ddzTempJson = cookieEncryptBean.decode(ddzTempEntrypt);
                DdzAuthzTemp ddzTemp = JacksonHelper.fromJSON(ddzTempJson, DdzAuthzTemp.class);
                if (ddzTemp != null) {
                    if (ddzTemp.checkSignature()) {
                        authzContext.setUid(ddzTemp.getUid());
                        authzContext.setAuthentication(true, false);
                    } else {
                        CookieHelper.writeCookie(ServletActionContext.getResponse(), domain,
                                                 DdzCookieNameConstants.DDZ_TEMP, null,
                                                 CookieConstants.EXPIRY_TIME_SESSION);
                    }
                }
            } catch (Exception e) {
                log.error("Analytic DdzTemp error ! " + e.getMessage(), e);
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
     * 更新cookie
     * 
     * @param response
     */
    public void change(HttpServletResponse response) {
        AuthzContext authzContext = AuthzContextHolder.getContext();
        if (authzContext.isTouch()) {
            try {

                String alipayId = authzContext.getAlipayId();
                String alipayIdEntrypt = cookieEncryptBean.encode(alipayId);
                if (StringUtils.isNotBlank(alipayIdEntrypt)) {
                    CookieHelper.writeCookie(response, domain, DdzCookieNameConstants.ALIPAY_ID, alipayIdEntrypt,
                                             CookieConstants.EXPIRY_TIME_YEAR);
                }
                
                if (authzContext.isAuthentication()) {
                    DdzAuthzTemp ddzAuthzTemp = new DdzAuthzTemp();
                    ddzAuthzTemp.setLoginTime(System.currentTimeMillis());
                    ddzAuthzTemp.setUid(authzContext.getUid());
                    DdzUserDO userDO = ddzAuthz.getUser();
                    if (userDO != null) {
                        CookieHelper.writeCookie(response, domain, DdzCookieNameConstants.LAST_LOGIN_ID,
                                                 URLEncoder.encode(userDO.getLoginId(), "GBK"),
                                                 CookieConstants.EXPIRY_TIME_YEAR);
                        ddzAuthzTemp.setPassword(userDO.getMd5Password());
                    }
                    ddzAuthzTemp.generateSignature();
                    String ddzTemp = cookieEncryptBean.encode(ddzAuthzTemp.toString());
                    CookieHelper.writeCookie(response, domain, DdzCookieNameConstants.DDZ_TEMP, ddzTemp,
                                             CookieConstants.EXPIRY_TIME_SESSION);
                } else {
                    CookieHelper.writeCookie(response, domain, DdzCookieNameConstants.DDZ_TEMP, null,
                                             CookieConstants.EXPIRY_TIME_SESSION);
                }
            } catch (Exception e) {
                log.error("change cookie error ! " + e.getMessage(), e);
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
