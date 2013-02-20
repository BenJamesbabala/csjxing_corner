package com.doucome.corner.web.bops.action.dcome.prom;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcAuctionItemSearchCondition;
import com.doucome.corner.biz.dcome.model.DcAuctionItemDTO;
import com.doucome.corner.biz.dcome.service.DcAuctionItemService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

public class AuctionItemListAction extends BopsBasicAction implements
		ModelDriven<DcAuctionItemSearchCondition> {

	private DcAuctionItemSearchCondition condition = new DcAuctionItemSearchCondition();

	private int page;

	private QueryResult<DcAuctionItemDTO> auctionQuery;

	@Autowired
	private DcAuctionItemService dcAuctionItemService;

	@Override
	public String execute() throws Exception {
		auctionQuery = dcAuctionItemService.queryAuctionItemWithPagination(condition,
				new Pagination(page));
		return SUCCESS;
	}

	@Override
	public DcAuctionItemSearchCondition getModel() {
		return condition;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<DcAuctionItemDTO> getAuctionQuery() {
		return auctionQuery;
	}

}
