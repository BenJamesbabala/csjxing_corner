package com.doucome.corner.web.dcome.action.frame.q;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IPUtils;
import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.qq.QqQueryService;
import com.doucome.corner.biz.core.qq.model.QqUserInfoModel;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserLoginTraceDO;
import com.doucome.corner.biz.dcome.business.DcQqUserRegisterBO;
import com.doucome.corner.biz.dcome.enums.DcUserExternalPfEnum;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.service.DcUserFilterService;
import com.doucome.corner.biz.dcome.service.DcUserLoginTraceService;
import com.doucome.corner.biz.dcome.service.impl.DcUserServiceImpl;
import com.doucome.corner.web.dcome.authz.DcSessionOperator;
import com.doucome.corner.web.dcome.authz.constants.DcPromotypeConstants;

/**
 * 类QqLoginAction.java的实现描述： QQ 登录
 * 
 * @author ib 2012-7-28 下午09:34:43
 */
@SuppressWarnings("serial")
public class QqLoginAction extends QBasicAction {

    private static final Log   signin_log = LogFactory.getLog(LogConstant.dcome_signin_log);
    
    private static final Log log = LogFactory.getLog(QqLoginAction.class) ;

    @Autowired
    private QqQueryService     qqQueryService;
    @Autowired
    private DcUserServiceImpl  dcUserService;
    @Autowired
    private DcQqUserRegisterBO dcQqUserRegisterBO;
    @Autowired
    private DcSessionOperator  dcSessionOperator;
    
    @Autowired
    private DcUserLoginTraceService dcUserLoginTraceService ;
    @Autowired
    private DcUserFilterService dcUserFilterService;
    
    private String             openid;
    private String             openkey;
    private String             pf;
    /**
     * 应用分享出去后链接中的应用自定义参数
     */
    private String             app_custom;

    @Override
    public String execute() throws Exception {
        dcAuthz.removePromotype(DcPromotypeConstants.INDEX_ITEM_FACTOR);
        QqUserInfoModel qqUserInfo = qqQueryService.queryUserInfo(pf, openid, openkey);
        boolean isNew = false;
        DcUserDO dcUserDO = null;
        if (qqUserInfo != null) {
        	DcUserDTO dto = dcUserService.queryUserByExternalId(qqUserInfo.getOpenId(),DcUserExternalPfEnum.QQ.getValue());                                                                     
            if (dto == null) {
                // 注册新会员
                dcUserDO = dcQqUserRegisterBO.registerUser(qqUserInfo);
                isNew = true;
            } else {
            	dcUserDO = dto.getUser() ;
            }

            if (dcUserDO != null) {
                dcQqUserRegisterBO.doAppCustomParamActAsyn(app_custom, dcUserDO, isNew);
                //白名单用户不更新nick
                String tempNick = getFilteredNick(qqUserInfo, dcUserDO);
                if (dcSessionOperator.load(dcUserDO.getUserId(), tempNick, openkey, pf)) {
                    doSomethingAfterLogin(dcUserDO, pf);
                    return SUCCESS;
                }
            }
        }
        return DCOME_QQ_ERROR;
    }
    
    /**
     * 登陆之后的后置事务
     * 
     * @param user
     */
    public void doSomethingAfterLogin(DcUserDO user, String from) {
    	 HttpServletRequest request = getRequest() ;
	        
    	try {
	    	//更新最后登陆
    		DcUserLoginTraceDO loginTrace = new DcUserLoginTraceDO();
            loginTrace.setClientAgent(request.getHeader(HTTP.USER_AGENT)) ;
            loginTrace.setLoginIp(IPUtils.toAddrLong(request.getRemoteAddr())) ;
            loginTrace.setUserId(user.getUserId()) ;
            loginTrace.setUbid(dcAuthz.getUbid());
            loginTrace.setNick(user.getNick());
            loginTrace.setGmtRegister(user.getGmtCreate());
	        dcUserService.updateLastLoginTimeAndTrace(user.getUserId(), JacksonHelper.toJSON(loginTrace));
	        dcUserService.updateLastLoginTime(user.getUserId()) ;
	        //日志
	        signinLog(user, from, true);
//	        dcUserLoginTraceService.createLoginTrace(loginTrace) ;
    	}catch(Exception e){
    		log.error(e.getMessage() , e) ;
    	}
    }

    public void signinLog(DcUserDO user, String from, boolean isSuccess) {
        StringBuilder builder = new StringBuilder();
        builder.append("{ip:[");
        builder.append(getRequest().getRemoteAddr());
        builder.append("],uid:");
        builder.append(user.getUserId());
        builder.append(", nick:");
        builder.append(user.getNick());
        builder.append(", from:");
        builder.append(from);
        if (isSuccess) {
            builder.append("  [success]");
        } else {
            builder.append("  [fail]");
        }
        builder.append("}");
        signin_log.info(builder.toString());
    }
    
    /**
     * 白名单中的用户，不更新nick.
     * @param qqUserInfo
     * @param user
     * @return
     */
    private String getFilteredNick(QqUserInfoModel qqUserInfo, DcUserDO user) {
    	if (dcUserFilterService.isWhiteListUser(user.getUserId())) {
    		return user.getNick();
    	}
    	return qqUserInfo.getNickName();
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
