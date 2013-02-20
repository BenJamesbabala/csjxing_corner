package com.doucome.corner.biz.dcome.service.horoscope.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.dal.condition.dcome.hs.HsMonthFateCondition;
import com.doucome.corner.biz.dal.horoscope.HsMonthFateDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsMonthFateDO;
import com.doucome.corner.biz.dcome.enums.hs.HoroscopeEnum;
import com.doucome.corner.biz.dcome.model.star.HsMonthFateDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsMonthFateService;

public class HsMonthFateServiceImpl implements HsMonthFateService {

	@Autowired
	private HsMonthFateDAO hsMonthFateDAO;

	private static final Log logger = LogFactory.getLog(HsMonthFateServiceImpl.class);
	
	@Override
	public Long createHsMonthFate(HsMonthFateDTO hsFate) {
		try {
			return hsMonthFateDAO.insertHsMonthFate(hsFate.toDO());
		} catch (Exception e) {
			logger.error(e);
			return -1l;
		}
	}

	@Override
	public HsMonthFateDTO getHsMonthFate(Long id) {
		if (IDUtils.isNotCorrect(id)) {
			return null;
		}
		HsMonthFateDO hsFate = hsMonthFateDAO.queryHsMonthFate(id);
		if (hsFate == null) {
			return null;
		}
		return new HsMonthFateDTO(hsFate);
	}

	@Override
	public HsMonthFateDTO getNowMonthHsFate(HoroscopeEnum hsEnum, Date date) {
        Date[] monthDates = DateUtils.getMonthStartEnd(date);
        HsMonthFateDO hsFate = hsMonthFateDAO.queryHsMonthFate(hsEnum.getId(), monthDates[0], monthDates[1]);
		if (hsFate == null) {
			return null;
		}
		return new HsMonthFateDTO(hsFate);
	}

	@Override
	public QueryResult<HsMonthFateDTO> getHsFatePage(HsMonthFateCondition condition, Pagination page) {
		Integer count = hsMonthFateDAO.countHsMonthFates(condition);
		if (count == 0) {
			return new QueryResult<HsMonthFateDTO>(new ArrayList<HsMonthFateDTO>(), page, 0);
		}
		List<HsMonthFateDO> hsFates = hsMonthFateDAO.queryHsMonthFates(condition, page.getStart() - 1, page.getSize());
		List<HsMonthFateDTO> temps = new ArrayList<HsMonthFateDTO>();
		for (HsMonthFateDO hsFate: hsFates) {
			temps.add(new HsMonthFateDTO(hsFate));
		}
		return new QueryResult<HsMonthFateDTO>(temps, page, count);
	}
	
	@Override
	public Integer updateHsMonthFate(HsMonthFateDTO hsFate) {
		return hsMonthFateDAO.updateHsMonthFate(hsFate.toDO());
	}
}
