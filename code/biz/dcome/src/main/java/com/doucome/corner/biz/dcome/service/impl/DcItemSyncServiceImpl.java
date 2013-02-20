package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dal.dcome.DcItemDAO;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.service.DcItemSyncService;
import com.doucome.corner.biz.dcome.service.DcTaobaoService;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;

/**
 * 商品信息同步类.
 * @author ze2200
 *
 */
public class DcItemSyncServiceImpl implements DcItemSyncService {
	
	@Autowired
	private DcItemDAO dcItemDAO;
	@Autowired
	private DcTaobaoService dcTaobaoService;
	
	private static final int PAGE_SIZE = 100;
	
	private Random DELAYER = new Random(System.currentTimeMillis());
	
	private static final Log logger = LogFactory.getLog(DcItemSyncServiceImpl.class);
	
	@SuppressWarnings("static-access")
	@Override
	public Integer syncItemDiscount(Date gmtSyncDateFrom, Date gmtSyncDateTo) {
		DcItemSearchCondition condition = new DcItemSearchCondition();
		condition.setGmtItemUpdateEnd(null) ;
		condition.setGmtItemUpdateStart(null) ;
		condition.setItemStatus(DcItemStatusEnum.NORMAL.getValue());
		int pageNum = 1, syncCount = 0;
		List<DcItemDO> items = null, syncItems = new ArrayList<DcItemDO>();
		String outCode = OutCodeUtils.encodeOutCode("QQ", OutCodeEnums.DOUCOME);
		TaobaokeItemDTO taokeItem = null;
		do {
			try {
				Thread.currentThread().sleep(DELAYER.nextInt(10) * 100);
			} catch(InterruptedException e) {
				System.out.println("-----InterruptedException");
			}
			Pagination page = new Pagination(pageNum, PAGE_SIZE);
			items = dcItemDAO.queryItemsWithPagination(condition, page.getStart(), page.getSize());
			for (DcItemDO temp: items) {	
				try {
					taokeItem = dcTaobaoService.conventItem(temp.getNativeId(), outCode);
				} catch(Exception e) {
					logger.error("sync item from source failed", e);
					continue;
				}
				if (taokeItem != null) {
					temp.setCommissionRate(DcItemUtils.convertTaokeItemDiscount(taokeItem.getCommissionRate()));
					syncItems.add(temp);
				}
			}
			dcItemDAO.batchUpdateSyncItemInfo(syncItems);
			syncItems.clear();
			pageNum++;
			syncCount = syncCount + items.size();
		} while(items.size() == PAGE_SIZE);
		
		return syncCount;
	}

}
