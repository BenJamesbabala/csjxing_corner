package com.doucome.corner.task.zhe.syncDcItem;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dcome.business.DcSyncItemBO;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.task.zhe.TaskService;

/**
 * 同步过期的DcItem
 * @author langben 2012-10-31
 *
 */
public class SyncDcItemTaskService implements TaskService {
	
	private static final Log log = LogFactory.getLog(SyncDcItemTaskService.class) ;
	
	private static final int PAGE_SIZE = 20 ;

	@Autowired
	private DcSyncItemBO dcSyncItemBO ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Override
	public String executeInternal() {
		
		Calendar cal = Calendar.getInstance() ;
		cal.add(Calendar.DATE, -1) ;//更新3天前的数据
		
		//更新
		DcItemSearchCondition condition = new DcItemSearchCondition();
		
		condition.setGmtItemUpdateEnd(cal.getTime()) ;
		condition.setItemStatus(DcItemStatusEnum.NORMAL.getValue());
		condition.setGenWay(DcItemGenWayEnums.PROFESSIONAL.getValue()) ;
		List<DcItemDTO> itemList = null ;
		do{
			try {
				itemList = null ;
				itemList = dcItemService.getItemsNoPagination(condition, new Pagination(1, PAGE_SIZE)) ;
				
				dcSyncItemBO.updateItems(itemList) ;
			} catch (Exception e){
				log.error(e.getMessage() , e) ;
			}
			try {
				Thread.sleep(1000) ;
			} catch (InterruptedException e) {
				log.error(e.getMessage() , e) ;
			}
		} while (CollectionUtils.size(itemList) == PAGE_SIZE) ;
		return null ;
	}

}
