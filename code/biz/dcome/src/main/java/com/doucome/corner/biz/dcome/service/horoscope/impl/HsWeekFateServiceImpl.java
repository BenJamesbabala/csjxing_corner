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
import com.doucome.corner.biz.dal.condition.dcome.hs.HsWeekFateCondition;
import com.doucome.corner.biz.dal.horoscope.HsWeekFateDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsWeekFateDO;
import com.doucome.corner.biz.dcome.enums.hs.HoroscopeEnum;
import com.doucome.corner.biz.dcome.model.star.HsWeekFateDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsWeekFateService;

/**
 * 
 * @author ze2200
 *
 */
public class HsWeekFateServiceImpl implements HsWeekFateService {

	@Autowired
	private HsWeekFateDAO hsWeekFateDAO;
	
	private static final Log logger = LogFactory.getLog(HsWeekFateServiceImpl.class);

	@Override
	public long createHsWeekFate(HsWeekFateDTO hsFate) {
		try {
			return hsWeekFateDAO.insertHsWeekFate(hsFate.toDO());
		} catch (Exception e) {
			logger.error(e);
			return -1l;
		}
	}

	@Override
	public HsWeekFateDTO getHsWeekFate(Long id) {
		if (IDUtils.isNotCorrect(id)) {
			return null;
		}
		HsWeekFateDO hsFate = hsWeekFateDAO.queryHsWeekFate(id);
		if (hsFate == null) {
			return null;
		}
		return new HsWeekFateDTO(hsFate);
	}

	@Override
	public HsWeekFateDTO getNowWeekHsFate(HoroscopeEnum hsEnum, Date date) {
		Date[] weekDates = DateUtils.getWeekStartEnd(date);
		HsWeekFateDO hsFate = hsWeekFateDAO.queryHsWeekFate(hsEnum.getId(), weekDates[0], weekDates[1]);
		if (hsFate == null) {
			return null;
		}
		return new HsWeekFateDTO(hsFate);
	}

	@Override
	public QueryResult<HsWeekFateDTO> getHsFatePage(HsWeekFateCondition condition, Pagination page) {
		Integer count = hsWeekFateDAO.countHsWeekFates(condition);
		if (count == 0) {
			return new QueryResult<HsWeekFateDTO>(new ArrayList<HsWeekFateDTO>(), page, 0);
		}
		List<HsWeekFateDO> hsFates = hsWeekFateDAO.queryHsWeekFates(condition, page.getStart() - 1, page.getSize());
		List<HsWeekFateDTO> temps = new ArrayList<HsWeekFateDTO>();
		for (HsWeekFateDO hsFate: hsFates) {
			temps.add(new HsWeekFateDTO(hsFate));
		}
		return new QueryResult<HsWeekFateDTO>(temps, page, count);
	}
	
	@Override
	public Integer updateHsWeekFate(HsWeekFateDTO hsfate) {
		return hsWeekFateDAO.updateHsWeekFate(hsfate.toDO());
	}
}
