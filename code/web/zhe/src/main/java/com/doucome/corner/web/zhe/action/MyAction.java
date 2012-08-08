package com.doucome.corner.web.zhe.action;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;

/**
 * 我的点点折
 * 
 * @author shenjia.caosj 2012-3-20
 */
@SuppressWarnings("serial")
public class MyAction extends DdzBasicAction {

    private static final Log              log  = LogFactory.getLog(MyAction.class);

    @Autowired
    private DdzTaokeReportService         ddzTaokeReportService;

    private QueryResult<DdzTaokeReportDO> itemList;

    private int                           page = 1;

    private DdzUserDO                     user;

    private String                        alipayId;

    private String                        statuses;

    @Override
    public String execute() throws Exception {

        user = ddzAuthz.getUser();
        DdzAccountDO accountDO = ddzAuthz.getAccount();
        if (accountDO != null) {
            alipayId = accountDO.getAlipayId();
        }

        // 如果是内部用户，可以直接访问，无需登陆
        if (user == null && !ddzAuthz.isPrivateUser()) {
            return DDZ_INDEX;
        }

        if (StringUtils.isBlank(alipayId)) {
            return SUCCESS;
        }

        Pagination pagination = new Pagination(page, 10);
        TaokeReportSearchCondition searchCondition = new TaokeReportSearchCondition();
        searchCondition.setSettleAlipay(alipayId);
        searchCondition.setSettleStatusList(ArrayStringUtils.toArray(statuses));
        itemList = ddzTaokeReportService.getReportsWithPagination(searchCondition, pagination);
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

    public String getStatuses() {
        return statuses;
    }

    public void setStatuses(String statuses) {
        this.statuses = statuses;
    }

    public void setAlipayId(String alipayId) {
        if (ddzAuthz == null) {
            log.error("ddzAuthz is null!");
            return;
        }
        if (ddzAuthz.isPrivateUser()) {
            this.alipayId = alipayId;
        }
    }

}
