package com.doucome.corner.task.zhe.spider;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dal.SpiderItemDAO;
import com.doucome.corner.biz.dal.dataobject.SpiderItemDO;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public abstract class AbstractItemSpider implements ItemSpider {

	protected static final Log spiderItemLog = LogFactory.getLog(LogConstant.spiderItem_log);
	private static final Log logger = LogFactory.getLog(AbstractItemSpider.class);

	protected SpiderItemDAO spiderItemDAO;
	protected TaobaokeServiceDecorator taobaokeServiceDecorator;

	@Override
	public boolean insertSpiderItemDO(SpiderItemDO spiderItemDO) {
		if (spiderItemDO == null || spiderItemDO.getItemId() <= 0) {
			return false;
		}
		SpiderItemDO oldItemDO = spiderItemDAO.queryItem(spiderItemDO.getItemId());
		if (oldItemDO != null) {
			return false;
		}
		try {
			TaobaokeItemDTO itemDTO = taobaokeServiceDecorator.widgetConventItem(
					String.valueOf(spiderItemDO.getItemId()), "", TaobaokeFields.taoke_item_fields);
			if (itemDTO != null) {
				spiderItemDO.setPrice(itemDTO.getPrice());
				spiderItemDO.setTitle(itemDTO.getTitle());
				spiderItemDO.setCommission(itemDTO.getCommission());
				if (itemDTO.getCommissionRate() != null) {
					spiderItemDO.setRate(DecimalUtils.divide(itemDTO.getCommissionRate(), BigDecimal.valueOf(10000l)));
				}
				spiderItemDO.setVolume(itemDTO.getVolume());
				spiderItemDO.setCreditScore(itemDTO.getSellerCreditScore());
				spiderItemDAO.insertItem(spiderItemDO);
				return true;
			}
		} catch (Exception e) {
			logger.error("insertSpiderItemDO error, tbItemID: " + spiderItemDO.getItemId(), e);
		}
		return false;
	}

	public SpiderItemDAO getSpiderItemDAO() {
		return spiderItemDAO;
	}

	public void setSpiderItemDAO(SpiderItemDAO spiderItemDAO) {
		this.spiderItemDAO = spiderItemDAO;
	}

	public TaobaokeServiceDecorator getTaobaokeServiceDecorator() {
		return taobaokeServiceDecorator;
	}

	public void setTaobaokeServiceDecorator(TaobaokeServiceDecorator taobaokeServiceDecorator) {
		this.taobaokeServiceDecorator = taobaokeServiceDecorator;
	}

}
