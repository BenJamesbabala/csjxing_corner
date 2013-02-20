package com.doucome.corner.web.namefate.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.apps.namefate.business.NamefateUserRegisterBO;
import com.doucome.corner.biz.apps.namefate.model.NamefateUserDTO;
import com.doucome.corner.biz.apps.namefate.service.NamefateUserService;
import com.doucome.corner.biz.core.qq.QqQueryService;
import com.doucome.corner.biz.core.qq.model.QqUserInfoModel;
import com.doucome.corner.web.namefate.action.context.NamefateAuthzContext;
import com.doucome.corner.web.namefate.action.context.NamefateAuthzContextHolder;

/**
 * 星座贝贝登陆action
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class NamefateQqLoginAction extends NamefateBasicAction {
    
    private static final Log log = LogFactory.getLog(NamefateQqLoginAction.class) ;

    @Autowired
    private NamefateUserService namefateUserService ;
    
    @Autowired
    private QqQueryService namefateQqQueryService ;
    
    @Autowired
    private NamefateUserRegisterBO namefateUserRegisterBO;
    
    private String openid;
    private String openkey;
    private String pf;
    /**
     * 应用分享出去后链接中的应用自定义参数
     */
    private String             app_custom;

    @Override
    public String execute() throws Exception {
        QqUserInfoModel qqUserInfo = namefateQqQueryService.queryUserInfo(pf, openid, openkey);
        boolean isNew = false;
        if (qqUserInfo != null) {
        	NamefateUserDTO user = namefateUserService.getUserByExternalId(qqUserInfo.getOpenId());
            if (user == null) {
                // 注册新会员
            	user = (NamefateUserDTO)namefateUserRegisterBO.registerUser(qqUserInfo);
                isNew = true;
            }

            if (user != null) {
            	//user.setUserNick(qqUserInfo.getNickName());
                refreshCookie(user.getUserId(), user.getUserNick());
                if (!isNew) {
                	namefateUserService.updateLoginInfo(user.getUser());
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
    	NamefateAuthzContext context = NamefateAuthzContextHolder.getContext();
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
