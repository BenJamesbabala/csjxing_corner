package com.doucome.corner.web.zhe.action;

import org.apache.commons.lang.StringUtils;
import com.doucome.corner.web.common.constant.CookieConstants;
import com.doucome.corner.web.common.cookie.CookieHelper;

/**
 * 类SetCookieAction.java的实现描述：设置cookie值的action，内部使用
 * 
 * @author ib 2012-5-12 下午04:06:36
 */
public class SetCookieAction extends DdzBasicAction {

    private String key;
    private String value;

    @Override
    public String execute() throws Exception {
        if (StringUtils.isNotBlank(key)) {
            CookieHelper.writeCookie(getResponse(), CookieConstants.DDZ_DEFAULT_DOMAIN, key, value,
                                     CookieConstants.EXPIRY_TIME_YEAR);
        }
        return SUCCESS;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
