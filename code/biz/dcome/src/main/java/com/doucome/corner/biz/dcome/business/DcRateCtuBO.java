package com.doucome.corner.biz.dcome.business;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.doucome.corner.biz.core.concurrent.ThreadWorker;
import com.doucome.corner.biz.dal.dataobject.dcome.DcRateEventDO;
import com.doucome.corner.biz.dcome.cache.DcItemRateStatisticsCache;
import com.doucome.corner.biz.dcome.cache.DcItemRateTimeCache;
import com.doucome.corner.biz.dcome.ctu.CtuConfigService;
import com.doucome.corner.biz.dcome.enums.DcBlockDegreeEnums;
import com.doucome.corner.biz.dcome.handler.CtuRateSecurityDegreeHandler;
import com.doucome.corner.biz.dcome.model.DcItemRateStatisticsDTO;
import com.doucome.corner.biz.dcome.model.DcRiskDegreeDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.service.DcRateEventService;
import com.doucome.corner.biz.dcome.service.DcUserService;

/**
 * @author ib 2012-8-13 ÉÏÎç12:52:57
 */
public class DcRateCtuBO {

    private final static Log             log = LogFactory.getLog(DcRateCtuBO.class);
    @Autowired
    private DcRateEventService           dcRateEventService;
    @Autowired
    private DcItemRateTimeCache          dcItemRateTimeCache;
    @Autowired
    private CtuConfigService             ctuConfigService;
    @Autowired
    private ThreadPoolTaskExecutor       calculateRatethreadPool;
    @Autowired
    private CtuRateSecurityDegreeHandler ctuRateSecurityDegreeHandler;
    @Autowired
    private DcItemRateStatisticsCache    dcItemRateStatisticsCache;
    @Autowired
    private DcUserService                dcUserService;

    public DcRiskDegreeDTO checkSecurityDegree(HttpServletRequest request, Long userId, Long itemId) {
        DcRiskDegreeDTO degreeDTO = new DcRiskDegreeDTO();
        degreeDTO.setDegree(DcBlockDegreeEnums.Undo);
        if (itemId != null) {
            DcItemRateStatisticsDTO statisticsDTO = dcItemRateStatisticsCache.get(itemId);
            if (statisticsDTO != null) {
                Integer ipShortTimes = statisticsDTO.getIpShortTimeMap().get(itemId);
                if (ipShortTimes != null && ipShortTimes > ctuConfigService.getRateLimitForIP()) {
                    degreeDTO.setDegree(DcBlockDegreeEnums.CheckCode);
                }
                Integer successTimes = statisticsDTO.getSuccessTimes();
                if (successTimes != null && successTimes > ctuConfigService.getSuccessTimeCheckCode()) {
                    degreeDTO.setDegree(DcBlockDegreeEnums.CheckCode);
                }

            }
        }
        return degreeDTO;
    }

    public void logRateEvent(HttpServletRequest request, Long userId, Long itemId, boolean isSuccess) {
        try {
            if (request == null || userId == null || itemId == null) {
                log.error("param is null, userId: " + userId + "; itemId: " + itemId);
                return;
            }

            DcRateEventDO eventDO = new DcRateEventDO();
            eventDO.setUserId(userId);
            eventDO.setItemId(itemId);
            eventDO.setStatus(String.valueOf(isSuccess));
            if (request != null) {
                eventDO.setClientAgent(request.getHeader("User-Agent"));
                eventDO.setIpAddress(request.getRemoteAddr());
                eventDO.setHttpMethod(request.getMethod());
                eventDO.setQueryString(request.getQueryString());
            }
            DcUserDTO user = dcUserService.getUser(userId);
            if (user != null) {
                eventDO.setGmtLogin(user.getGmtLastLogin());
                eventDO.setNick(user.getNick());
            }
            dcRateEventService.insert(eventDO);
        } catch (Exception e) {
            log.error("save rate event fail, userId: " + userId + "; itemId: " + itemId, e);
        }
        int times = dcItemRateTimeCache.getRateTimes(itemId);
        dcItemRateTimeCache.incTime(itemId);
        if ((times++) % ctuConfigService.getCalculateFrequency() == 0) {
            calculateRatethreadPool.execute(new ThreadWorker<Long>(ctuRateSecurityDegreeHandler, itemId));
        }
    }

}
