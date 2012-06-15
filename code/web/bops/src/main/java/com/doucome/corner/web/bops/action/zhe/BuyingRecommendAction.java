package com.doucome.corner.web.bops.action.zhe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.core.cache.AbstractCacheSupport.InternalStoreItem;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.DdzRecommendSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendDO;
import com.doucome.corner.biz.zhe.cache.BuyingRecommendItemCache;
import com.doucome.corner.biz.zhe.service.DdzRecommendService;
import com.doucome.corner.biz.zhe.vo.BuyingRecommendVO;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 别人正在买 管理
 * @author shenjia.caosj 2012-5-28
 *
 */
@SuppressWarnings("serial")
public class BuyingRecommendAction extends  BopsBasicAction implements ModelDriven<DdzRecommendSearchCondition>{
	
	@Autowired
	private DdzRecommendService ddzRecommendService ;
	
	@Autowired
	private BuyingRecommendItemCache buyingRecommendItemCache ;
		
	private DdzRecommendSearchCondition condition = new DdzRecommendSearchCondition();

	private int page ;
	
	private InternalStoreItem<List<DdzRecommendDO>> cacheDate ;
	
	private QueryResult<BuyingRecommendVO> recommendResult ;
		
	@Override
	public String execute() throws Exception {
		if(StringUtils.isBlank(condition.getBatchNo())){
			DateFormat f = new SimpleDateFormat("yyyyMMdd") ;
			condition.setBatchNo(f.format(new Date())) ;
		}
		
		QueryResult<DdzRecommendDO> result = ddzRecommendService.getRecommendsWithPagination(condition, new Pagination(page , 56)) ;
		List<BuyingRecommendVO> list = new ArrayList<BuyingRecommendVO>() ;
		for(DdzRecommendDO dd : result.getItems()){
			if(dd != null){
				list.add(new BuyingRecommendVO(dd,null)) ;
			}
		}
		recommendResult = new QueryResult<BuyingRecommendVO>(list , result.getPagination() , result.getTotalRecords()) ;
		
		cacheDate = buyingRecommendItemCache.getInternalItems() ;
		
		return SUCCESS ;
	}

	
	
	public int getPage() {
		return page;
	}
		

	public InternalStoreItem<List<DdzRecommendDO>> getCacheDate() {
		return cacheDate;
	}



	public void setPage(int page) {
		this.page = page;
	}

	public QueryResult<BuyingRecommendVO> getRecommendResult() {
		return recommendResult;
	}

	

	@Override
	public DdzRecommendSearchCondition getModel() {
		return condition;
	}
}
