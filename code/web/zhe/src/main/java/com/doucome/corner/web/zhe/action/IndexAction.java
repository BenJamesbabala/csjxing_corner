package com.doucome.corner.web.zhe.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.zhe.service.DdzAccountService;

/**
 * Ê×Ò³
 * 
 * @author shenjia.caosj 2012-2-24
 */
@SuppressWarnings("serial")
public class IndexAction extends DdzBasicAction {

//	@Autowired
//    private DdzAccountService        ddzAccountService;

    @Override
    public String execute() throws Exception {
        //ddzAccountService.getAccountIdByAlipayId("abc@163.com");
    
        return SUCCESS;
    }

}
