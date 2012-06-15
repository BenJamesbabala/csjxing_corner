package com.doucome.corner.biz.zhe.service;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;

/**
 * ��DdzReportMailService.java��ʵ�������������ʼ�
 * 
 * @author ib 2012-5-7 ����11:31:08
 */
public interface DdzReportMailService {

    /**
     * �������ʹ��ɹ��ʼ�
     * 
     * @param settleIdList
     * @return
     */
    public int sendTaokeReportMailsForIds(List<Integer> settleIdList);

    public int sendTaokeReportMails(List<DdzTaokeReportSettleDO> settleList);
    
    /**
     * ���ʹ��ɹ��ʼ�
     * 
     * @param settleId
     * @return
     */
    public boolean sendTaokeReportMail(Long settleId);
}
