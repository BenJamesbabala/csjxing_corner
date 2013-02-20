package com.doucome.corner.web.dcome.action.frame.q;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.condition.dcome.DcExchangeItemCondition;
import com.doucome.corner.biz.dcome.business.DcExchangeBO;
import com.doucome.corner.biz.dcome.enums.DcPromScheduleEnum;
import com.doucome.corner.biz.dcome.model.DcExchangeItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;

/**
 * 积分兑换商品
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class DcExchangeItemAction extends QBasicAction {
	
	List<DcExchangeItemDTO> exchangeItems;
	@Autowired
	private DcExchangeBO dcExchangeBO;
	
	@Override
	public String execute() {
		DcUserDTO user = getUser();
		if (user != null) {
			exchangeItems = dcExchangeBO.getCurExchangeItems(user);
			for (DcExchangeItemDTO temp: exchangeItems) {
				temp.setUserLevelEnum(user.getLevelEnum());
			}
		} else {
			exchangeItems = new ArrayList<DcExchangeItemDTO>();
		}
		
		return SUCCESS;
	}

	public List<DcExchangeItemDTO> getExchangeItems() {
		return exchangeItems;
	}
}
