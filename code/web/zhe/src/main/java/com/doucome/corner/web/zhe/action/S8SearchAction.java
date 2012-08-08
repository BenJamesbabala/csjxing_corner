package com.doucome.corner.web.zhe.action;

import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.Constant;
import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.model.TaokeItemSearchCondition;
import com.doucome.corner.biz.core.utils.MD5Util;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.core.utils.ValidateUtil;
import com.doucome.corner.biz.dal.dataobject.DdzSearchLogDO;
import com.doucome.corner.biz.zhe.enums.SearchWayEnums;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.biz.zhe.service.DdzSearchLogService;
import com.doucome.corner.biz.zhe.service.KeywordsFilterService;
import com.doucome.corner.web.zhe.authz.DdzSessionOperator;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ¹Ø¼ü×Ö²éÑ¯
 * @author shenjia.caosj 2012-3-17
 * 
 */
@SuppressWarnings("serial")
public class S8SearchAction extends DdzBasicAction implements ModelDriven<TaokeItemSearchCondition> {
	
	private TaokeItemSearchCondition condition = new TaokeItemSearchCondition();
	
	private String mark = "m";
	
	private String s8Url ;
	
	private String alipayId ;

	@Autowired
    private DdzSessionOperator        ddzSessionOperator;
	
	@Autowired
    private DdzAccountService         ddzAccountService;
	
	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator;


	@Autowired
	private DdzSearchLogService ddzSearchLogService;
	
	@Autowired
	private KeywordsFilterService keywordsFilterService;

	@Override
	public String execute() throws Exception {

		alipayId = ddzAuthz.getAlipayId() ;
		
		String keyword = URLDecoder.decode(condition.getKeyword(),Constant.ENCODING) ;
		if (keywordsFilterService.isForbbiden(keyword)) {
			return SUCCESS;
		}
		
		if(StringUtils.isNotBlank(alipayId)){
			String accountId = generateAccountId();
			
			String outCode = accountId == null ? null : OutCodeUtils.encodeOutCode(accountId, OutCodeEnums.DDZ_ACCOUNT_ID);
			
			if(StringUtils.isNotBlank(keyword)){
				
				s8Url = taobaokeServiceDecorator.getListurl(keyword, outCode) ;
				
				DdzSearchLogDO searchLog = new DdzSearchLogDO() ;
				searchLog.setAlipayId(alipayId) ;
				searchLog.setSearchBrief(keyword) ;
				searchLog.setSearchWay(SearchWayEnums.KEYWORD.getValue()) ;
				
				ddzSearchLogService.createLog(searchLog) ;
			}
		}
		generateMark(keyword);
		return SUCCESS;
	}
	
	private void generateMark(String keyword){
	    if(StringUtils.isBlank(mark)){
	        String md5 = MD5Util.getMD5(keyword);
            mark = StringUtils.substring(md5, 35).toLowerCase();
	    }
	}

	
	private String generateAccountId() {
        
        String alipayId = ddzAuthz.getAlipayId();
        
        // validate alipayId
        if (!ValidateUtil.checkIsEmail(alipayId) && !ValidateUtil.checkIsMobile(alipayId)) {
            alipayId = null;
        }

        // update to cookie
        if (!StringUtils.equals(alipayId, ddzAuthz.getAlipayId())) {
            ddzSessionOperator.setAlipayId(alipayId);
        }
        String accountId = null ;
        if (StringUtils.isNotBlank(alipayId)) {
            accountId = ddzAccountService.getAccountIdByAlipayId(alipayId);
        }
        return accountId;
    }
	
	@Override
	public TaokeItemSearchCondition getModel() {
		return condition;
	}

	/**
	 * -----------------------------------------
	 */

	public TaokeItemSearchCondition getCondition() {
		return condition;
	}

	public String getS8Url() {
		return s8Url;
	}

	public void setCondition(TaokeItemSearchCondition condition) {
		this.condition = condition;
	}

	public String getMark() {
        return mark;
    }

	public String getAlipayId() {
		return alipayId;
	}
   
}
