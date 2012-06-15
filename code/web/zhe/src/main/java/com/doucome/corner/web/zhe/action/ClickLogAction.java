package com.doucome.corner.web.zhe.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.web.common.action.BasicAction;

/**
 * 类ClickLogAction.java的实现描述：接受点击事件
 * 
 * @author ib 2012-5-19 下午11:00:21
 */
public class ClickLogAction extends DdzBasicAction {

    private static final Log reportMailLog = LogFactory.getLog(LogConstant.click_log);

    public String execute() throws Exception {
        if (!ddzAuthz.isPrivateUser()) {
            reportMailLog.info(getRequest().getQueryString());
        }
        return SUCCESS;
    }
}
