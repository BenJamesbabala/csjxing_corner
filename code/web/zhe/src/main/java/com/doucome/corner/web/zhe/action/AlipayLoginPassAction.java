package com.doucome.corner.web.zhe.action;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.model.AlipayLoginDTO;
import com.doucome.corner.biz.zhe.service.DdzUserRegisterService;
import com.doucome.corner.biz.zhe.service.DdzUserService;
import com.doucome.corner.web.zhe.authz.DdzSessionOperator;
import com.doucome.corner.web.zhe.context.AuthzContext;
import com.doucome.corner.web.zhe.context.AuthzContextHolder;
import com.doucome.corner.web.zhe.model.AlipayCallback;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 支付宝登陆回调
 * @author langben 2012-9-17
 *
 */
@SuppressWarnings("serial")
public class AlipayLoginPassAction extends DdzBasicAction implements ModelDriven<AlipayCallback> {

	private static final Log         signin_log           = LogFactory.getLog(LogConstant.signin_log);
	
	private AlipayCallback callback = new AlipayCallback() ;
	
	@Autowired
	private DdzUserService ddzUserService ;
	
	@Autowired
	private DdzUserRegisterService ddzUserRegisterService ;
	
    @Autowired
    private DdzSessionOperator       ddzSessionOperator;
	
	@Override
	public String execute() throws Exception {
		
		String realName = callback.getReal_name() ;
				
		String nativeAlipayId = callback.getUser_id() ;
		
		if(StringUtils.isBlank(nativeAlipayId)){
			return INPUT ;
		}
		
		DdzUserDO user = ddzUserService.getByLoginId(nativeAlipayId);
		
		if (user == null) {
			AlipayLoginDTO dto = new AlipayLoginDTO() ;
			dto.setNativeAlipayId(nativeAlipayId) ;
			dto.setRealName(realName) ;
            user = ddzUserRegisterService.registerByAlipay(dto);
        }
		
		if (user != null) {
            ddzSessionOperator.load(user.getUid());
            doSomethingAfterLogin(user);
            
            AuthzContext authzContext = AuthzContextHolder.getContext();
            String validAlipayId = authzContext.getValidAlipayId() ;
            
//            if(StringUtils.isBlank(validAlipayId)){
//            	return "confirmAlipay" ;
//            }
//            
            return SUCCESS;
            
        }
				
		return INPUT ;
	}
	
	@Override
	public AlipayCallback getModel() {
		return callback ;
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

}
