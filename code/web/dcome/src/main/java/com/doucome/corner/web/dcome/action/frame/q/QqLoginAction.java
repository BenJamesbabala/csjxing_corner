package com.doucome.corner.web.dcome.action.frame.q;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.qq.QqQueryService;
import com.doucome.corner.biz.core.qq.model.QqUserInfoModel;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dcome.business.DcQqUserRegisterBO;
import com.doucome.corner.biz.dcome.enums.DcUserExternalPfEnum;
import com.doucome.corner.biz.dcome.service.impl.DcUserServiceImpl;
import com.doucome.corner.web.dcome.action.DComeBasicAction;
import com.doucome.corner.web.dcome.authz.DcSessionOperator;

/**
 * 类QqLoginAction.java的实现描述： QQ 登录
 * 
 * @author ib 2012-7-28 下午09:34:43
 */
@SuppressWarnings("serial")
public class QqLoginAction extends DComeBasicAction {

    private static final Log   signin_log = LogFactory.getLog(LogConstant.dcome_signin_log);

    @Autowired
    private QqQueryService     qqQueryService;
    @Autowired
    private DcUserServiceImpl  dcUserService;
    @Autowired
    private DcQqUserRegisterBO dcQqUserRegisterBO;
    @Autowired
    private DcSessionOperator  dcSessionOperator;
    private String             openid;
    private String             openkey;
    private String             pf;

    @Override
    public String execute() throws Exception {
        QqUserInfoModel qqUserInfo = qqQueryService.queryUserInfo(pf, openid, openkey);
        if (qqUserInfo != null) {
            DcUserDO dcUserDO = dcUserService.queryUserByExternalId(qqUserInfo.getOpenId(),
                                                                    DcUserExternalPfEnum.QQ.getValue());
            if (dcUserDO == null) {
                // 注册新会员
                dcUserDO = dcQqUserRegisterBO.registerUser(qqUserInfo);
            }

            if (dcUserDO != null) {
                if (dcSessionOperator.load(dcUserDO.getUserId(), qqUserInfo.getNickName(), openkey, pf)) {
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
        dcUserService.updateLastLoginTime(user.getUserId());
        signinLog(user, from, true);
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

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public void setOpenkey(String openkey) {
        this.openkey = openkey;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

}
