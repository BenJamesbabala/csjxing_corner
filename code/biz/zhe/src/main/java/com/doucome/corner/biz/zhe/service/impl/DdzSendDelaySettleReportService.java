package com.doucome.corner.biz.zhe.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.core.enums.ReportStatusEnums;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.sms.model.SmsBusinessEnums;
import com.doucome.corner.biz.core.sms.model.SmsMtDO;
import com.doucome.corner.biz.core.sms.model.SmsMtResult;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.service.DdzAbstractSendReportService;

/**
 * 类DdzSuccessSettleReportMailService.java的实现描述：发送延迟打款邮件
 * 
 * @author ib 2012-8-19 下午11:52:32
 */
public class DdzSendDelaySettleReportService extends DdzAbstractSendReportService {

    private static final Log logger = LogFactory.getLog(DdzSendDelaySettleReportService.class);

    @Override
    protected void updateStatus(List<Integer> mailSuccessList, List<Integer> smsSuccessList, List<Integer> failList) {
        if (mailSuccessList.size() > 0) {
            ddzTaokeReportSettleDAO.updateEmailStatus(mailSuccessList, ReportStatusEnums.SEND_DELAY.getValue());
        }
        if (smsSuccessList.size() > 0) {
            ddzTaokeReportSettleDAO.updateEmailStatus(smsSuccessList, ReportStatusEnums.SEND_DELAY.getValue());
        }
        if (failList.size() > 0) {
            ddzTaokeReportSettleDAO.updateEmailStatus(failList, ReportStatusEnums.SEND_DELAY_FAIL.getValue());
        }
    }

    @Override
    protected void addResult2List(ReportStatusEnums result, Integer id, List<Integer> mailSuccessList,
                                  List<Integer> smsSuccessList, List<Integer> failList) {
        if (result == ReportStatusEnums.SEND_DELAY) {
            mailSuccessList.add(id);
        } else if (result == ReportStatusEnums.SEND_DELAY_FAIL) {
            failList.add(id);
        } else {
            logger.error("send delay settle report result error: " + result +", id: " + id);
        }

    }

    @Override
    protected boolean isCanSend(DdzTaokeReportSettleDO settleDO, List<DdzTaokeReportDO> reportDOs) {
        if (reportDOs == null || reportDOs.size() < 1) {
            return false;
        }
        if (settleDO == null
            || !StringUtils.equals(settleDO.getSettleStatus(), SettleStatusEnums.DELAY_SETTLE.getValue())
            || StringUtils.isEmpty(settleDO.getSettleAlipay())) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean hasSended(DdzTaokeReportSettleDO settleReport) {
        if (settleReport.getEmailStatus() == null) {
            return false;
        }
        if (settleReport.getEmailStatus().equals(ReportStatusEnums.SEND_DELAY)) {
            return true;
        }
        return false;
    }

    @Override
    protected List<DdzTaokeReportDO> queryTaokeReport(Long settleId) {
        TaokeReportSearchCondition condition = new TaokeReportSearchCondition();
        condition.setSettleId(settleId);
        condition.setSettleStatus(SettleStatusEnums.DELAY_SETTLE.getValue());
        return ddzTaokeReportService.getReports(condition);
    }

    @Override
    protected ReportStatusEnums sendReportEmail(String emailAddress, Map<String, Object> context,
                                                DdzTaokeReportSettleDO settleDO) {
        if (sendMailService.send("email-delay-rebate-notice", emailAddress, context)) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                logger.error("sleep error", e);
            }
            // sendMailService.send("email-rebate-notice", "diandianzhetest@163.com", context);
            reportMailLog.info("[success] send delay mail to " + emailAddress + " ,alipayId: " + settleDO.getSettleAlipay()
                               + ", settleId: " + settleDO.getSettleId());
            return ReportStatusEnums.SEND_DELAY;
        } else {
            reportMailLog.info("[error] send delay mail to " + emailAddress + " ,alipayId: " + settleDO.getSettleAlipay()
                               + " ,settleId: " + settleDO.getSettleId());
            return ReportStatusEnums.NULL;
        }
    }

    @Override
    protected ReportStatusEnums sendReportSms(String mobile, Map<String, Object> context,
                                              DdzTaokeReportSettleDO settleDO) {
    	if(settleDO == null){
    		return ReportStatusEnums.SEND_FAIL ;
    	}
    	SmsMtDO mt = new SmsMtDO() ;    	
    	BigDecimal total = settleDO.getSettleFee() ;
    	if(total == null){
    		return ReportStatusEnums.SEND_FAIL ;
    	}
    	String settleFee = DecimalUtils.format(total , "0.00") ;
    	mt.setMsgParameter(new String[]{settleFee}) ;
    	mt.setToMobile(mobile) ;
    	mt.setBusinessId(SmsBusinessEnums.DDZ_DELAY_SMS) ;
    	SmsMtResult result = sendSmsService.sendMessage(mt) ;
    	
        return result.isSuccess() ? ReportStatusEnums.SEND_DELAY : ReportStatusEnums.SEND_FAIL;
    }

}
