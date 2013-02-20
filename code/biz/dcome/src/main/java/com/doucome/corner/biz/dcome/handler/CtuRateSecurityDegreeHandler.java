package com.doucome.corner.biz.dcome.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.concurrent.AbstractHandler;
import com.doucome.corner.biz.dal.dataobject.dcome.DcRateEventDO;
import com.doucome.corner.biz.dcome.cache.DcItemRateStatisticsCache;
import com.doucome.corner.biz.dcome.cache.DcItemRateTimeCache;
import com.doucome.corner.biz.dcome.constant.CtuItemRateConstats;
import com.doucome.corner.biz.dcome.ctu.CtuConfigService;
import com.doucome.corner.biz.dcome.model.DcItemRateStatisticsDTO;
import com.doucome.corner.biz.dcome.service.DcRateEventService;

/**
 * 类CtuCalculateRateHandler.java的实现描述：统计商品的投票事件情况
 * 
 * @author ib 2012-8-25 下午9:12:41
 */
public class CtuRateSecurityDegreeHandler extends AbstractHandler<Long> {

    @Autowired
    private DcRateEventService        dcRateEventService;
    @Autowired
    private DcItemRateTimeCache       dcItemRateTimeCache;
    @Autowired
    private CtuConfigService          ctuConfigService;
    @Autowired
    private DcItemRateStatisticsCache dcItemRateStatisticsCache;

    @Override
    public void handle(Long itemId) {
        if (itemId == null) return;
        int inMinutes = ctuConfigService.getCalculateRateInMinutes();
        List<DcRateEventDO> eventList = dcRateEventService.queryByItemId(itemId, inMinutes);
        Map<String, Integer> ipMap = new HashMap<String, Integer>();
        Map<String, Integer> ipShortTimeMap = new HashMap<String, Integer>();
        Map<String, Integer> agentMap = new HashMap<String, Integer>();
        Map<String, Integer> statusMap = new HashMap<String, Integer>();
        int successTimes = 0;
        Date nowDate = new Date();
        int ipLimitSeconds = ctuConfigService.getIpLimitSeconds();
        Date startTime = DateUtils.addSeconds(nowDate, 0 - ipLimitSeconds);
        for (DcRateEventDO dcRateEventDO : eventList) {
            put2Map(ipMap, dcRateEventDO.getIpAddress());
            if (dcRateEventDO.getGmtCreate() != null && dcRateEventDO.getGmtCreate().after(startTime)) {
                put2Map(ipShortTimeMap, dcRateEventDO.getIpAddress());
            }
            put2Map(statusMap, dcRateEventDO.getStatus());
            put2Map(agentMap, dcRateEventDO.getClientAgent());
            put2Map(statusMap, dcRateEventDO.getStatus());
            if (StringUtils.equalsIgnoreCase(dcRateEventDO.getStatus(), CtuItemRateConstats.SUCCESS)) {
                successTimes++;
            }
        }

        DcItemRateStatisticsDTO itemRateDTO = new DcItemRateStatisticsDTO();
        itemRateDTO.setItemId(itemId);
        itemRateDTO.setIpMap(ipMap);
        itemRateDTO.setIpShortTimeMap(ipShortTimeMap);
        itemRateDTO.setAgentMap(agentMap);
        itemRateDTO.setStatusMap(statusMap);
        itemRateDTO.setTimes(eventList.size());
        itemRateDTO.setSuccessTimes(successTimes);
        dcItemRateStatisticsCache.set(itemId, itemRateDTO);
    }

    private void put2Map(Map<String, Integer> map, String key) {
        if (StringUtils.isBlank(key)) {
            key = CtuItemRateConstats.NULL;
        }
        Integer ipCount = map.get(key);
        if (ipCount == null) {
            ipCount = 0;
        }
        map.put(key, ++ipCount);
    }

}
