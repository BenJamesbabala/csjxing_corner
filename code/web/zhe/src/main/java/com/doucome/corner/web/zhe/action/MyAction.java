package com.doucome.corner.web.zhe.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.taobao.model.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;

/**
 * 我的点点折
 * 
 * @author shenjia.caosj 2012-3-20
 * 
 */
@SuppressWarnings("serial")
public class MyAction extends DdzBasicAction {

	@Autowired
	private DdzTaokeReportService ddzTaokeReportService;

	private QueryResult<DdzTaokeReportDO> itemList;

	private int page = 1;

	private DdzUserDO user;

	private String alipayId;

	@Override
	public String execute() throws Exception {

		user = ddzAuthz.getUser();
		alipayId = ddzAuthz.getAlipayId();
		
		if(user == null){
			return INPUT ;
		}
		
		Pagination pagination = new Pagination(page , 10);
		TaokeReportSearchCondition searchCondition = new TaokeReportSearchCondition();
		searchCondition.setSettleAlipay(alipayId);
		itemList = ddzTaokeReportService.getReportsWithPagination(
				searchCondition, pagination);
		return SUCCESS;
	}

	public DdzUserDO getUser() {
		return user;
	}

	public String getAlipayId() {
		return alipayId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<DdzTaokeReportDO> getItemList() {
		return itemList;
	}

}
