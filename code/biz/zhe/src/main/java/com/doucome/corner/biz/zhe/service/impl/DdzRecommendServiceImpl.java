package com.doucome.corner.biz.zhe.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.core.enums.RecommendTypeEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.service.taobao.TaobaoRecommandDecorator;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaoFavoriteItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.taobao.model.TaobaoRecommendItemCondition;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dal.DdzRecommendDAO;
import com.doucome.corner.biz.dal.DdzRecommendTaskDAO;
import com.doucome.corner.biz.dal.condition.DdzRecommendSearchCondition;
import com.doucome.corner.biz.dal.condition.DdzSearchLogCondition;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendDO;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendTaskDO;
import com.doucome.corner.biz.dal.dataobject.DdzSearchLogDO;
import com.doucome.corner.biz.zhe.enums.SearchWayEnums;
import com.doucome.corner.biz.zhe.model.RecommendBatchDate;
import com.doucome.corner.biz.zhe.service.DdzRecommendService;
import com.doucome.corner.biz.zhe.service.DdzSearchLogService;

public class DdzRecommendServiceImpl implements DdzRecommendService {
	
	private static final int BUYING_RECOMMEND_POOL_COUNT = 300 ; //池子里放200
	
	@Autowired
	private DdzRecommendDAO ddzRecommendDAO ;
	
	@Autowired
	private DdzRecommendTaskDAO ddzRecommendTaskDAO ;
	
	@Autowired
	private TaobaoRecommandDecorator taobaoRecommandDecorator ;
	
	@Autowired
	private DdzSearchLogService ddzSearchLogService ;
	
	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator ;

	@Override
	public int createRecommend(List<DdzRecommendDO> recommendList) {
		return ddzRecommendDAO.insertRecommend(recommendList) ;
	}

	@Override
	public int updateRecommendDisplayById(int id, String isDisplay) {
		return ddzRecommendDAO.updateRecommendDisplayById(id, isDisplay) ;
	}

	@Override
	public List<TaobaokeItemDTO> prepareBuyingRecommends(RecommendBatchDate date) {
		
		int count = (int)(BUYING_RECOMMEND_POOL_COUNT * 1.5) ;
		
		List<TaobaokeItemDTO> returnList = new ArrayList<TaobaokeItemDTO>() ;
		
		List<String> idArrayList = new ArrayList<String>() ;
		
		Date d = date.getDate() ;
		//获取昨天的数据
		Calendar cd = DateUtils.getCalendar(d) ;
		cd.add(Calendar.DATE, -1) ;
		d = cd.getTime() ;
		DdzSearchLogCondition searchCondition = new DdzSearchLogCondition() ;
		searchCondition.setSearchWay(SearchWayEnums.ITEM.getValue()) ;
		searchCondition.setUniqueBrief(true) ;
		searchCondition.setGmtCreateStart(DateUtils.setTime(d, 0, 0, 0)) ;
		searchCondition.setGmtCreateEnd(DateUtils.setTime(d, 23, 59, 59)) ;
		QueryResult<DdzSearchLogDO> logResult = ddzSearchLogService.getSearchLogWithPagination(searchCondition, new Pagination(1, 200)) ;
		String[] arr1 = conventItemIdsForLog(logResult.getItems()) ;
		idArrayList.addAll(Arrays.asList(arr1)) ;	
		
		//不足的从类目取
		DdzRecommendTaskDO category = ddzRecommendTaskDAO.queryRecommendTask(RecommendTypeEnums.BUYING.getValue()) ;
		if(category != null){
			String categoryStr = category.getCategoryIds() ;
			String[] categoryArr = ArrayStringUtils.toArray(categoryStr) ;
			if(ArrayUtils.isEmpty(categoryArr)){
				return null ;
			}
			long  c = count / categoryArr.length ;
			if(c > 40){
				c = 40L ;
			}
			for(String categoryId : categoryArr){
				TaobaoRecommendItemCondition condition = new TaobaoRecommendItemCondition() ;
				
				condition.setCount(c) ;
				
				List<TaobaoFavoriteItemDTO> recommendList = taobaoRecommandDecorator.getRecommendItemsByCategory(Long.valueOf(categoryId), condition) ;
				if(!CollectionUtils.isEmpty(recommendList)){
					String[] idArr = conventItemIds(recommendList) ;
					idArrayList.addAll(Arrays.asList(idArr)) ;
				}
				
				if(idArrayList.size() > count){
					break ;
				}
			}
		}
		
		String[] idArray = listToArray(idArrayList) ;
		
		for(int i=0 ;i<idArrayList.size() ;i+=40){
			//计算佣金
			int end = i + 40 ;
			if(end > idArrayList.size()){
				end = idArrayList.size() ;
			}
			String[] subArr = (String[])ArrayUtils.subarray(idArray, i, end) ;
			List<TaobaokeItemDTO> itemList = taobaokeServiceDecorator.conventItems(subArr, null , TaobaokeFields.taoke_item_fields) ;
			if(!CollectionUtils.isEmpty(itemList)){
				returnList.addAll(itemList) ;
			}
		}
		
		return returnList ;
	}
	
	private String[] listToArray(List<String> list){
		if(list == null){
			return new String[0] ;
		}
		String[] array = new String[list.size()] ;
		for(int i=0 ;i<list.size();i++){
			array[i] = list.get(i) ;
		}
		return array ;
	}
	
	private String[] conventItemIdsForLog(List<DdzSearchLogDO> list){
		if(!CollectionUtils.isEmpty(list)){
			String[] idArr = new String[list.size()];
			for(int i = 0 ; i < list.size() ; i++){
				idArr[i] = String.valueOf(list.get(i).getSearchBrief()) ;
			}
			return idArr ;
		}
		return new String[0] ;
	}
	
	private String[] conventItemIds(List<TaobaoFavoriteItemDTO> list){
		if(!CollectionUtils.isEmpty(list)){
			String[] idArr = new String[list.size()];
			for(int i = 0 ; i < list.size() ; i++){
				idArr[i] = String.valueOf(list.get(i).getItemId()) ;
			}
			return idArr ;
		}
		return new String[0] ;
	}

	@Override
	public List<DdzRecommendDO> getRecommends(RecommendBatchDate date , RecommendTypeEnums recommType) {
		return ddzRecommendDAO.queryRecommends(date.getDateStr(), recommType.getValue()) ;
	}

	@Override
	public QueryResult<DdzRecommendDO> getRecommendsWithPagination(DdzRecommendSearchCondition condition, Pagination pagination) {
		int totalRecords = ddzRecommendDAO.countRecommendsWithPagination(condition) ;
        if (totalRecords <= 0) {
            return new QueryResult<DdzRecommendDO>(new ArrayList<DdzRecommendDO>(), pagination, totalRecords);
        }
        List<DdzRecommendDO> items = ddzRecommendDAO.queryRecommendsWithPagination(condition, pagination.getStart(), pagination.getSize()) ;
        
        return new QueryResult<DdzRecommendDO>(items, pagination, totalRecords);
		
	}

}
