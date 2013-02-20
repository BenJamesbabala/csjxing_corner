package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * ugc item查询接口
 * @author langben 2012-8-25
 *
 */
@SuppressWarnings("serial")
public class QueryUgcsAjaxAction  extends DComeBasicAction {
	
	private static final Log logger = LogFactory.getLog(QueryUgcsAjaxAction.class);

	private JsonModel<List<DcItemDTO>> json = new JsonModel<List<DcItemDTO>>() ;
	
	private int page = 1;
	/**
	 * 时间戳参数,用于对gmtCreate过滤后分页取数.
	 */
	private Long timeStamp;
	
	@Autowired
	private DcItemService dcItemService;
	
	@Override
	public String execute() throws Exception {
		try {
			DcItemSearchCondition itemCondition = new DcItemSearchCondition() ;
			itemCondition.setGenWay(DcItemGenWayEnums.USER.getValue()) ;
			itemCondition.setItemStatus(DcItemStatusEnum.NORMAL.getValue());
			itemCondition.setTimeStamp(timeStamp);
			List<Long> idList = dcItemService.getItemIdsNoPagination(itemCondition, new Pagination(page, 15)) ;
			List<DcItemDTO> itemList = dcItemService.getItemsByIds(idList);
			Collections.sort(itemList, new Comparator<DcItemDTO>() {
				@Override
				public int compare(DcItemDTO o1, DcItemDTO o2) {
					return o2.getGmtCreate().compareTo(o1.getGmtCreate());
				}
			});
			fillOtherInfo(itemList);
			if(timeStamp == null) {
				timeStamp = System.currentTimeMillis();
				//已数据库时间为准，防止时间不一致导致查询不到数据.
				if (!CollectionUtils.isEmpty(itemList)) {
					timeStamp = itemList.get(0).getGmtCreate().getTime();
				}
			}
			json.setData(itemList) ;
			json.setCode(JsonModel.CODE_SUCCESS);
			return SUCCESS;
		} catch (Exception e) {
			logger.error(e);
			json.setFail(JsonModel.CODE_FAIL, "internal.error");
		}
		return SUCCESS;
	}
	
	private void fillOtherInfo(List<DcItemDTO> items) {
		Long curUserId = getUserId();
		if (CollectionUtils.isEmpty(items) || IDUtils.isNotCorrect(curUserId)) {
			return;
		}
		for (DcItemDTO temp: items) {
			temp.setOwnerSign(DcItemUtils.getItemOwerSign(temp.getCreatorUserId(), curUserId));
		}
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public void setTimeStamp(String timeStamp) {
		if (!StringUtils.isEmpty(timeStamp)) {
			try {
				this.timeStamp = Long.valueOf(timeStamp);
			} catch (Exception e) {
			}
		}
	}

	public JsonModel<List<DcItemDTO>> getJson() {
		return json;
	}
	
	public Long getTimeStamp() {
		return this.timeStamp;
	}
}
