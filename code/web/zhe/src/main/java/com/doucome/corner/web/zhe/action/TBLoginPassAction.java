package com.doucome.corner.web.zhe.action;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.EnvConstant;
import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.service.taobao.TaobaoServiceDecorator;
import com.doucome.corner.biz.core.taobao.constant.TopParameterConst;
import com.doucome.corner.biz.core.taobao.dto.TaobaoUserDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaoFields;
import com.doucome.corner.biz.core.utils.EnvPropertiesUtil;
import com.doucome.corner.biz.core.utils.TaobaoSignV2Utils;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.biz.zhe.service.DdzTaobaoRegisterService;
import com.doucome.corner.biz.zhe.service.DdzUserService;
import com.doucome.corner.web.zhe.authz.DdzSessionOperator;
import com.doucome.corner.web.zhe.model.TopCallbackParameter;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 接收淘宝回调的参数
 * 
 * @author shenjia.caosj 2012-3-21
 */
@SuppressWarnings("serial")
public class TBLoginPassAction extends DdzBasicAction implements ModelDriven<TopCallbackParameter> {

    private static final Log         log                  = LogFactory.getLog(TBLoginPassAction.class);

    private static final Log         taobao_log           = LogFactory.getLog(LogConstant.taobao_log);

    private static final Log         signin_log           = LogFactory.getLog(LogConstant.signin_log);

    private TopCallbackParameter     topCallbackParameter = new TopCallbackParameter();

    @Autowired
    private TaobaoServiceDecorator   taobaoServiceDecorator;

    @Autowired
    private DdzUserService           ddzUserService;

    @Autowired
    private DdzSessionOperator       ddzSessionOperator;

    @Autowired
    private DdzTaobaoRegisterService ddzTaobaoRegisterService;

    @Autowired
    private DdzAccountService        ddzAccountService;

    @Override
    public String execute() throws Exception {
        try {
            String appSecret = EnvPropertiesUtil.getProperty(EnvConstant.CORNER_API_TAOBAO_SECRET);
            String top_appkey = topCallbackParameter.getTop_appkey();
            String top_parameters = topCallbackParameter.getTop_parameters();
            String top_sign = topCallbackParameter.getTop_sign();
            String top_session = topCallbackParameter.getTop_session();
            //String parameter = top_appkey + top_parameters + top_session;
            boolean isValidate = TaobaoSignV2Utils.verifyTopResponse(top_parameters, top_sign ,appSecret);
            if (!isValidate) {
                taobao_log.error("taobao callback parameter error : " + topCallbackParameter);
            }

            Map<String, String> parameterMap = TaobaoSignV2Utils.convertBase64StringtoMap(top_parameters);
            // 获取淘宝登陆账号
            String nickname = parameterMap.get(TopParameterConst.nick);
            if (StringUtils.isNotBlank(nickname)) {
                // 查询数据库
                DdzUserDO user = ddzUserService.getByLoginId(nickname);
                TaobaoUserDTO tbUserDTO = taobaoServiceDecorator.getUser(nickname, TaobaoFields.taobao_user_fields,
                                                                         top_session);
                if (user == null) {
                    user = ddzTaobaoRegisterService.register(tbUserDTO);
                }

                if (user != null) {
                    if (tbUserDTO != null) {
                        ddzAccountService.insertOrUpdateAccount(user.getUid(), tbUserDTO.getAlipayAccount());
                    }
                    ddzSessionOperator.load(user.getUid());
                    doSomethingAfterLogin(user);
                    return SUCCESS;
                }
            }

            // 登录失败，跳转至DDZ首页
            redirectToUrlName(URIConstant.DDZ_SERVER);

        } finally{
        	
        }
        return SUCCESS;
    }

    /**
     * 登陆之后的后置事务
     * 
     * @param user
     */
    public void doSomethingAfterLogin(DdzUserDO user) {
        ddzUserService.updateLastLoginTime(user.getUid());
        signinLog(user, true);
    }

    public void signinLog(DdzUserDO user, boolean isSuccess) {
        StringBuilder builder = new StringBuilder();
        builder.append("{ip:[");
        builder.append(getRequest().getRemoteAddr());
        builder.append("],uid:");
        builder.append(user.getUid());
        builder.append(", loginId:");
        builder.append(user.getLoginId());
        if (isSuccess) {
            builder.append("  [success]");
        } else {
            builder.append("  [fail]");
        }
        builder.append("}");
        signin_log.info(builder.toString());
    }

    public String tbkLogin() {
        return SUCCESS;
    }

    /**
     * ------------------------------------------------
     */

    @Override
    public TopCallbackParameter getModel() {
        return topCallbackParameter;
    }

}
