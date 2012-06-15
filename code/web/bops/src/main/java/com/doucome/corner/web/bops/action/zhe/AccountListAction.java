package com.doucome.corner.web.bops.action.zhe;


import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dal.condition.DdzAccountSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ≤È—Øaccount
 * 
 * @author shenjia.caosj 2012-5-20
 * 
 */
@SuppressWarnings("serial")
public class AccountListAction extends BopsBasicAction implements
		ModelDriven<DdzAccountSearchCondition> {

	@Autowired
	private DdzAccountService ddzAccountService;

	private DdzAccountSearchCondition condition = new DdzAccountSearchCondition();

	private int page;

	private QueryResult<DdzAccountDO> accountResult;

	@Override
	public String execute() throws Exception {
		condition.setGmtCreateStart(DateUtils.setTime(condition.getGmtCreateStart(), 0 , 0 , 0)) ;
		condition.setGmtCreateEnd(DateUtils.setTime(condition.getGmtCreateEnd(), 23 , 59 , 59)) ;
		accountResult = ddzAccountService.getAccountsWithPagination(condition,new Pagination(page));

		return SUCCESS;
	}

	public QueryResult<DdzAccountDO> getAccountResult() {
		return accountResult;
	}

	public void setCondition(DdzAccountSearchCondition condition) {
		this.condition = condition;
	}

	@Override
	public DdzAccountSearchCondition getModel() {
		return condition;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
