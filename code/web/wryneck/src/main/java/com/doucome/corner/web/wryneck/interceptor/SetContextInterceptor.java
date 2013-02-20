package com.doucome.corner.web.wryneck.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.doucome.corner.biz.core.encrypt.EncryptBean;
import com.doucome.corner.web.common.constant.CookieConstants;
import com.doucome.corner.web.common.cookie.CookieHelper;
import com.doucome.corner.web.wryneck.constant.WryneckConstant;
import com.doucome.corner.web.wryneck.context.WryneckAuthzContext;
import com.doucome.corner.web.wryneck.context.WryneckAuthzContextHolder;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.PreResultListener;

/**
 * 
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class SetContextInterceptor extends AbstractInterceptor {

    private static final Log log = LogFactory.getLog(SetContextInterceptor.class);
    
    private EncryptBean      cookieEncryptBean;
    
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
                    change(ServletActionContext.getResponse());
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
    
    /**
     * 
     */
    public void prepare() {
        Cookie[] cookies = ServletActionContext.getRequest().getCookies();
        WryneckAuthzContext authzContext = WryneckAuthzContextHolder.getContext();
        try {
	        String temp = CookieHelper.readCookie(cookies, WryneckConstant.USER_ID_COO_NAME);
	        if (temp != null) {
		        try {
		        	Long userId = Long.valueOf(cookieEncryptBean.decode(temp));
		        	authzContext.setUserId(userId);
		        } catch (Exception e) {
					log.error("userId error[" + temp + "]" + e.getMessage(), e);
				}
	        }
	        temp = CookieHelper.readCookie(cookies, WryneckConstant.USER_NICK_COO_NAME);
	        if (temp != null) {
	        	authzContext.setUserNick(cookieEncryptBean.decode(temp));
	        }
	        temp = CookieHelper.readCookie(cookies, WryneckConstant.OPEN_ID_COO_NAME);
	        if (temp != null) {
	        	authzContext.setOpenId(cookieEncryptBean.decode(temp));
	        }
	        temp = CookieHelper.readCookie(cookies, WryneckConstant.OPEN_KEY_COO_NAME);
	        if (temp != null) {
	        	authzContext.setOpenKey(cookieEncryptBean.decode(temp));
	        }
	        temp = CookieHelper.readCookie(cookies, WryneckConstant.PF_COO_NAME);
	        if (temp != null) {
	        	authzContext.setPf(cookieEncryptBean.decode(temp));
	        }
        } catch (Exception e) {
        	log.error("read cookie error. " + e.getMessage(), e);
        }
        
    }

    /**
     * 更新cookie
     * 
     * @param response
     */
    public void change(HttpServletResponse response) {
        WryneckAuthzContext authzContext = WryneckAuthzContextHolder.getContext();
        if (authzContext.isRewriteCookie()) {
        	try {
        		String tempCookie = authzContext.getUserNick();
        		tempCookie = cookieEncryptBean.encode(tempCookie);
        		if (StringUtils.isNotEmpty(tempCookie)) {
        			writeSessionCookie(WryneckConstant.USER_NICK_COO_NAME, tempCookie);
        		}
        		Long userId = authzContext.getUserId();
        		tempCookie = cookieEncryptBean.encode(String.valueOf(userId));
        		writeSessionCookie(WryneckConstant.USER_ID_COO_NAME, tempCookie);
        		tempCookie = cookieEncryptBean.encode(authzContext.getOpenId());
        		writeSessionCookie(WryneckConstant.OPEN_ID_COO_NAME, tempCookie);
        		tempCookie = cookieEncryptBean.encode(authzContext.getOpenKey());
        		writeSessionCookie(WryneckConstant.OPEN_KEY_COO_NAME, tempCookie);
        		tempCookie = cookieEncryptBean.encode(authzContext.getPf());
        		writeSessionCookie(WryneckConstant.PF_COO_NAME, tempCookie);
        	} catch (Exception e) {
        		log.error("rewrite cookie error. " + e.getMessage(), e);
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
        WryneckAuthzContextHolder.setContext(null);
    }

    public void setCookieEncryptBean(EncryptBean cookieEncryptBean) {
        this.cookieEncryptBean = cookieEncryptBean;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
