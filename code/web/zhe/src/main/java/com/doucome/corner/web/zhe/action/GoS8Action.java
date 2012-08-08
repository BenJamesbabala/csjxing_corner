package com.doucome.corner.web.zhe.action;



import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.core.utils.ValidateUtil;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.web.zhe.authz.DdzSessionOperator;

/**
 * ×ªµ½S8
 * @author shenjia.caosj 2012-6-1
 *
 */
@SuppressWarnings("serial")
public class GoS8Action extends DdzBasicAction  {

	private String keyword ;
	
	private String alipayId ;
	
	@Autowired
    private DdzAccountService         ddzAccountService;
	
	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator;
	
	@Override
	public String execute() throws Exception {
		
		if(StringUtils.isBlank(alipayId)){
			alipayId = ddzAuthz.getAlipayId() ;
		}
		
		String accountId = null ;
		if(StringUtils.isNotBlank(alipayId)){
			accountId = generateAccountId(alipayId);
		}
		
		String outCode = accountId == null ? null : OutCodeUtils.encodeOutCode(accountId, OutCodeEnums.DDZ_ACCOUNT_ID);
				
		String s8Url = taobaokeServiceDecorator.getListurl(keyword, outCode) ;
		
		redirect(s8Url) ;
		
		return SUCCESS ;
	}
	
	private String generateAccountId(String alipayId) {
        
        // validate alipayId
        if (!ValidateUtil.checkIsEmail(alipayId) && !ValidateUtil.checkIsMobile(alipayId)) {
            alipayId = null;
        }

        String accountId = null ;
        if (StringUtils.isNotBlank(alipayId)) {
            accountId = ddzAccountService.getAccountIdByAlipayId(alipayId);
        }
        
        return accountId;
    }

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}
	
	
}
