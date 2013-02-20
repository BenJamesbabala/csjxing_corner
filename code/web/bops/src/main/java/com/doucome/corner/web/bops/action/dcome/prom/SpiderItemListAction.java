package com.doucome.corner.web.bops.action.dcome.prom;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.service.SpiderItemService;
import com.doucome.corner.biz.dal.condition.SpiderItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.SpiderItemDO;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.action.utils.SpiderItemCategoryUtil;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class SpiderItemListAction extends BopsBasicAction implements ModelDriven<SpiderItemSearchCondition> {

    private SpiderItemSearchCondition condition   = new SpiderItemSearchCondition();

    @Autowired
    private SpiderItemService         spiderItemService;

    private int                       page;

    private Set<String>               categorySet = SpiderItemCategoryUtil.getCategoryset();

    private QueryResult<SpiderItemDO> itemQuery;

    @Override
    public String execute() throws Exception {

        itemQuery = spiderItemService.queryListWithPagination(condition, new Pagination(page));

        return SUCCESS;
    }

    @Override
    public SpiderItemSearchCondition getModel() {
        return condition;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public QueryResult<SpiderItemDO> getItemQuery() {
        return itemQuery;
    }

    public Set<String> getCategorySet() {
        return categorySet;
    }

}
