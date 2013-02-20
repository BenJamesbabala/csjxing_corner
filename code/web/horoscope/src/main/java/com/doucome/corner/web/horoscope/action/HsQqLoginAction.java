package com.doucome.corner.web.horoscope.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.qq.QqQueryService;
import com.doucome.corner.biz.core.qq.model.QqUserInfoModel;
import com.doucome.corner.biz.dcome.business.DcQqUserRegisterBO;
import com.doucome.corner.biz.dcome.model.star.HsUserDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsUserService;
import com.doucome.corner.web.horoscope.context.HsAuthzContext;
import com.doucome.corner.web.horoscope.context.HsAuthzContextHolder;

/**
 * 星座贝贝登陆action
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class HsQqLoginAction extends HsBasicAction {
    
    private static final Log log = LogFactory.getLog(HsQqLoginAction.class) ;
    
    private QqQueryService hsQqQueryService;
    @Autowired
    private HsUserService hsUserService;
    @Autowired
    private DcQqUserRegisterBO dcQqUserRegisterBO;
    
    private String openid;
    private String openkey;
    private String pf;
    /**
     * 应用分享出去后链接中的应用自定义参数
     */
    private String             app_custom;

    @Override
    public String execute() throws Exception {
        QqUserInfoModel qqUserInfo = hsQqQueryService.queryUserInfo(pf, openid, openkey);
        boolean isNew = false;
        if (qqUserInfo != null) {
        	HsUserDTO hsUser = hsUserService.queryUserByExternalId(qqUserInfo.getOpenId());
            if (hsUser == null) {
                // 注册新会员
            	hsUser = dcQqUserRegisterBO.regiterStUser(qqUserInfo);
                isNew = true;
            }

            if (hsUser != null) {
            	hsUser.setUserNick(qqUserInfo.getNickName());
                refreshCookie(hsUser.getUserId(), hsUser.getUserNick());
                if (!isNew) {
                	hsUserService.updateLoginInfo(hsUser);
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
    	HsAuthzContext context = HsAuthzContextHolder.getContext();
    	context.setRewriteCookie(true);
    	context.setUserId(userId);
    	context.setUserNick(userNick);
    	context.setOpenId(openid);
    	context.setOpenKey(openkey);
    	context.setPf(pf);
    }
    
    public void setHsQqQueryService(QqQueryService hsQueryService) {
    	this.hsQqQueryService = hsQueryService;
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
