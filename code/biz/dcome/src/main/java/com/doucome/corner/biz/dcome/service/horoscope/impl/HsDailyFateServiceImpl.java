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
import com.doucome.corner.biz.dal.condition.dcome.hs.HsDailyFateCondition;
import com.doucome.corner.biz.dal.horoscope.HsDailyFateDAO;
import com.doucome.corner.biz.dal.horoscope.dataobject.HsDailyFateDO;
import com.doucome.corner.biz.dcome.enums.hs.HoroscopeEnum;
import com.doucome.corner.biz.dcome.model.star.HsDailyFateDTO;
import com.doucome.corner.biz.dcome.service.horoscope.HsDailyFateService;

/**
 * 
 * @author ze2200
 *
 */
public class HsDailyFateServiceImpl implements HsDailyFateService {

	@Autowired
	private HsDailyFateDAO hsDailyFateDAO;

    private static final Log logger = LogFactory.getLog(HsDailyFateServiceImpl.class);
	
	@Override
	public Long createHsDailyFate(HsDailyFateDTO hsFate) {
		try {
			return hsDailyFateDAO.insertHsDailyFate(hsFate.toDO());
		} catch(Exception e) {
			logger.error(e);
			return -1l;
		}
	}

	@Override
	public HsDailyFateDTO getHsDailyFate(Long id) {
		if (IDUtils.isNotCorrect(id)) {
			return null;
		}
		HsDailyFateDO hsFate = hsDailyFateDAO.queryHsDailyFate(id);
		if (hsFate == null) {
			return null;
		}
		return new HsDailyFateDTO(hsFate);
	}

	@Override
	public HsDailyFateDTO getNowHsFate(HoroscopeEnum hsEnum, Date date) {
		if (date == null) {
			return null;
		}
		Date[] dates = DateUtils.getDayStartEnd(date);
		HsDailyFateDO hsFate = hsDailyFateDAO.queryHsDailyFate(hsEnum.getId(), dates[0], dates[1]);
		if (hsFate == null) {
			return null;
		}
		return new HsDailyFateDTO(hsFate);
	}

	@Override
	public QueryResult<HsDailyFateDTO> getHsFatePage(HsDailyFateCondition condition, Pagination page) {
		Integer count = hsDailyFateDAO.countHsDailyFates(condition);
		if (count == 0) {
			return new QueryResult<HsDailyFateDTO>(new ArrayList<HsDailyFateDTO>(), page, 0);
		}
		List<HsDailyFateDO> hsFates = hsDailyFateDAO.queryHsDailyFates(condition, page.getStart() - 1, page.getSize());
		List<HsDailyFateDTO> temps = new ArrayList<HsDailyFateDTO>();
		for (HsDailyFateDO hsFate: hsFates) {
			temps.add(new HsDailyFateDTO(hsFate));
		}
		return new QueryResult<HsDailyFateDTO>(temps, page, count);
	}
	
	@Override
	public Integer updateHsDailyFate(HsDailyFateDTO hsFate) {
		return hsDailyFateDAO.updateHsDailyFate(hsFate.toDO());
	}
}
