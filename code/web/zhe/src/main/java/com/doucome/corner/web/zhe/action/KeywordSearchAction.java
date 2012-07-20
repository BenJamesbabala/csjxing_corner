package com.doucome.corner.web.zhe.action;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.model.TaokeItemSearchCondition;
import com.doucome.corner.biz.core.utils.MD5Util;
import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;
import com.doucome.corner.biz.zhe.service.DdzSearchLogService;
import com.doucome.corner.biz.zhe.service.DdzTaobaokeService;
import com.doucome.corner.biz.zhe.service.KeywordsFilterService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 关键字查询
 * @author shenjia.caosj 2012-3-17
 * 
 */
@SuppressWarnings("serial")
public class KeywordSearchAction extends DdzBasicAction implements ModelDriven<TaokeItemSearchCondition> {
	
	private static final int TAOBAO_MAX_PAGES = 100 ;//淘宝最多支持100页

	private TaokeItemSearchCondition condition = new TaokeItemSearchCondition();

	private QueryResult<TaobaokeItemFacade> itemList;

	private int page = 1;
	
	private String mark = "m";

	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator;

	@Autowired
	private DdzTaobaokeService ddzTaobaokeService;

	@Autowired
	private DdzSearchLogService ddzSearchLogService;
	
	@Autowired
	private KeywordsFilterService keywordsFilterService;

	@Override
	public String execute() throws Exception {

//		String alipayId = ddzAuthz.getAlipayId() ;
//		
//		Pagination pagination = new Pagination(page);
//		pagination.setMaxPages(TAOBAO_MAX_PAGES) ;
//		String keyword = URLDecoder.decode(condition.getKeyword(),Constant.ENCODING) ;
//		if (keywordsFilterService.isForbbiden(keyword)) {
//			return SUCCESS;
//		}
//		if(StringUtils.isBlank(condition.getSort())){
//			condition.setSort(TaokeItemSearchSortEnums.commissionRate_desc.getValue()) ;
//		}
//		if(StringUtils.isNotBlank(keyword)){
//			QueryResult<TaobaokeItemDTO> internalItems = taobaokeServiceDecorator.getItems(condition, TaobaokeFields.taoke_item_fields , pagination);
//			itemList = ddzEatDiscountService.batchEatDiscount(internalItems , new EatDiscountCondition());
//			getValueStack().push(pagination);
//			DdzSearchLogDO searchLog = new DdzSearchLogDO() ;
//			searchLog.setAlipayId(alipayId) ;
//			searchLog.setSearchBrief(keyword) ;
//			searchLog.setSearchWay(SearchWayEnums.KEYWORD.getValue()) ;
//			
//			ddzSearchLogService.createLog(searchLog) ;
//		}
//		
//		generateMark(keyword);
		return SUCCESS;
	}
	
	private void generateMark(String keyword){
	    if(StringUtils.isBlank(mark)){
	        String md5 = MD5Util.getMD5(keyword);
            mark = StringUtils.substring(md5, 35).toLowerCase();
	    }
	}

	public QueryResult<TaobaokeItemFacade> getItemList() {
		return itemList;
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

	public void setCondition(TaokeItemSearchCondition condition) {
		this.condition = condition;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
    
    public String getMark() {
        return mark;
    }
    
    public void setMark(String mark) {
        this.mark = mark;
    }

}
