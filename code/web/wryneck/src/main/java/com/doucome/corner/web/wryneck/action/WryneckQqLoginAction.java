package com.doucome.corner.web.wryneck.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.qq.QqQueryService;
import com.doucome.corner.biz.core.qq.model.QqUserInfoModel;
import com.doucome.corner.biz.dcome.business.wryneck.WryneckUserRegisterBO;
import com.doucome.corner.biz.dcome.model.wryneck.WryneckUserDTO;
import com.doucome.corner.biz.dcome.service.wryneck.WryneckUserService;
import com.doucome.corner.web.wryneck.context.WryneckAuthzContext;
import com.doucome.corner.web.wryneck.context.WryneckAuthzContextHolder;

/**
 * 星座贝贝登陆action
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class WryneckQqLoginAction extends WryneckBasicAction {
    
    private static final Log log = LogFactory.getLog(WryneckQqLoginAction.class) ;

    @Autowired
    private WryneckUserService wryneckUserService;
    
    @Autowired
    private QqQueryService wryneckQqQueryService ;
    
    @Autowired
    private WryneckUserRegisterBO wryneckUserRegisterBO;
    
    private String openid;
    private String openkey;
    private String pf;
    /**
     * 应用分享出去后链接中的应用自定义参数
     */
    private String             app_custom;

    @Override
    public String execute() throws Exception {
        QqUserInfoModel qqUserInfo = wryneckQqQueryService.queryUserInfo(pf, openid, openkey);
        boolean isNew = false;
        if (qqUserInfo != null) {
        	WryneckUserDTO user = wryneckUserService.queryUserByExternalId(qqUserInfo.getOpenId());
            if (user == null) {
                // 注册新会员
            	user = wryneckUserRegisterBO.registerWryneckUser(qqUserInfo);
                isNew = true;
            }

            if (user != null) {
            	//user.setUserNick(qqUserInfo.getNickName());
                refreshCookie(user.getUserId(), user.getUserNick());
                if (!isNew) {
                	wryneckUserService.updateLoginInfo(user.getUser());
                }
            	return SUCCESS;
            }
        	return "no.login";
        } else {
        	log.error("qq invoke error");
        }
        return "dcomeQQError";
    }
    
    private void refreshCookie(Long userId, String userNick) {
    	WryneckAuthzContext context = WryneckAuthzContextHolder.getContext();
    	context.setRewriteCookie(true);
    	context.setUserId(userId);
    	context.setUserNick(userNick);
    	context.setOpenId(openid);
    	context.setOpenKey(openkey);
    	context.setPf(pf);
    }
    
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setOpenkey(String openkey) {
        this.openkey = openkey;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }
    
    public void setApp_custom(String appCustom) {
    	this.app_custom = appCustom;
    }
}
