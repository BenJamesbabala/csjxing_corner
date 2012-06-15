package com.doucome.corner.task.zhe.syncBuyingRecomm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.enums.RecommendTypeEnums;
import com.doucome.corner.biz.core.enums.TrueFalseEnums;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendDO;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendTaskDO;
import com.doucome.corner.biz.zhe.buyingRecomm.BuyingRecommendDanderousKeywords;
import com.doucome.corner.biz.zhe.model.RecommendBatchDate;
import com.doucome.corner.biz.zhe.service.DdzRecommendService;
import com.doucome.corner.biz.zhe.service.DdzRecommendTaskService;
import com.doucome.corner.task.zhe.TaskService;

public class SyncBuyingRecommendTaskService implements TaskService {
	
	private static final Log logger = LogFactory.getLog(SyncBuyingRecommendTaskService.class) ;
	
	private static final Log taskRecommendLog = LogFactory.getLog(LogConstant.task_syncRecommend_log) ;

	@Autowired
	private DdzRecommendService ddzRecommendService ;
		
	@Autowired
	private DdzRecommendTaskService ddzRecommendTaskService ;
	
	@Autowired
	private BuyingRecommendDanderousKeywords buyingRecommendDanderousKeywords ;
		
	@Override
	public String executeInternal() {
		taskRecommendLog.info("start execute syncBuyingRecommendTaskService .") ;
		Calendar c = Calendar.getInstance() ;
		c.add(Calendar.DATE, 1) ;
		RecommendBatchDate date = new RecommendBatchDate(c.getTime()) ;
		String str = syncDailyRecommend(date) ;
		taskRecommendLog.info(str) ;
		return null;
	}

	
	/**
	 * 同步一天的数据
	 * @param date
	 * @return
	 */
	public String syncDailyRecommend(RecommendBatchDate date) {
		
		//查询数据库，看是否已经同步过
		DdzRecommendTaskDO recommendTask = ddzRecommendTaskService.getRecommendTask(RecommendTypeEnums.BUYING.getValue()) ;
		
		if(recommendTask != null){
			String lastTaskBatch = recommendTask.getLastTaskBatch() ;
			if(StringUtils.equals(lastTaskBatch, date.getDateStr())) {
				taskRecommendLog.info("batch : " + lastTaskBatch + " has runned . exit .") ;
				return "";
			} 
		}
		
		List<TaobaokeItemDTO> itemList = ddzRecommendService.prepareBuyingRecommends(date) ;
		
		List<DdzRecommendDO> recommList = conventRecommendList(itemList, date) ;
		
		//过滤关键字
		if(recommList != null){
	 		for(Iterator<DdzRecommendDO> i = recommList.iterator() ; i.hasNext() ;){
	 			DdzRecommendDO rd = i.next() ; 			
	 			if(rd != null){
	 				String title = rd.getItemTitle() ;
	 				if(buyingRecommendDanderousKeywords.isDangerousKeywords(title)){
	 					i.remove() ;
	 				}
	 			}
	 		}
		}
		
		//插入数据库
		int insertCount = ddzRecommendService.createRecommend(recommList) ;
		
		ddzRecommendTaskService.updateRecommendTaskBatch(RecommendTypeEnums.BUYING.getValue(), date.getDateStr()) ;
		taskRecommendLog.info("syncBuyingRecommendTaskService count : " + insertCount) ;
		return "syncCount:" + insertCount ;
	}
	
	private List<DdzRecommendDO> conventRecommendList(List<TaobaokeItemDTO> list , RecommendBatchDate date){
		List<DdzRecommendDO> recommendList = new ArrayList<DdzRecommendDO>() ;
		for(TaobaokeItemDTO item : list){
			DdzRecommendDO recommItem = new DdzRecommendDO() ;
			recommItem.setBatchNo(date.getDateStr()) ;
			recommItem.setCommission(item.getCommission()) ;
			recommItem.setCommissionRate(item.getCommissionRate()) ;
			recommItem.setIsDisplay(TrueFalseEnums.TRUE.getValue()) ;
			recommItem.setItemPrice(item.getPrice()) ;
			recommItem.setItemTitle(item.getTitle()) ;
			recommItem.setItemPicUrl(item.getPicUrl()) ;
			recommItem.setNumIid(item.getNumIid()) ;
			recommItem.setRecommendType(RecommendTypeEnums.BUYING.getValue()) ;
			recommendList.add(recommItem) ;
 		}
		return recommendList ;
	}
}
