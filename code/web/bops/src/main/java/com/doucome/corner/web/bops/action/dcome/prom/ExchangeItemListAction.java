package com.doucome.corner.web.bops.action.dcome.prom;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcExchangeItemCondition;
import com.doucome.corner.biz.dcome.business.DcExchangeBO;
import com.doucome.corner.biz.dcome.model.DcExchangeItemDTO;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * »ý·Ö¶Ò»»action.
 * @author ze2200
 *
 */
public class ExchangeItemListAction extends BopsBasicAction implements ModelDriven<DcExchangeItemCondition> {
	
	private static final long serialVersionUID = 1L;

	private DcExchangeItemCondition condition = new DcExchangeItemCondition();

	private int page;
	
	private QueryResult<DcExchangeItemDTO> exchangeItems;
	
	@Autowired
	private DcExchangeBO dcExchangeBO;

	@Override
	public String execute() throws Exception {
		page = page <= 0? 1: page;
		condition.setOrder("exCount");
		exchangeItems = dcExchangeBO.getExchangeItemPage(condition, page);
		return SUCCESS;
	}

	@Override
	public DcExchangeItemCondition getModel() {
		return condition;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<DcExchangeItemDTO> getExchangeItems() {
		return exchangeItems;
	}

}
