package com.doucome.corner.biz.zhe.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.enums.ReportStatusEnums;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.sms.model.SmsBusinessEnums;
import com.doucome.corner.biz.core.sms.model.SmsMtDO;
import com.doucome.corner.biz.core.sms.model.SmsMtResult;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.core.utils.MobileUtil;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.service.DdzAbstractSendReportService;

/**
 * 类DdzSuccessSettleReportMailService.java的实现描述：发送成功打款邮件
 * 
 * @author ib 2012-8-19 下午11:52:32
 */
public class DdzSendSuccessSettleReportService extends DdzAbstractSendReportService {

    private static final Log logger = LogFactory.getLog(DdzSendSuccessSettleReportService.class);

    @Override
    protected void updateStatus(List<Integer> mailSuccessList, List<Integer> smsSuccessList, List<Integer> failList) {
        if (mailSuccessList.size() > 0) {
            ddzTaokeReportSettleDAO.updateEmailStatus(mailSuccessList, ReportStatusEnums.SEND_EMAIL_SUCCESS.getValue());
        }
        if (smsSuccessList.size() > 0) {
            ddzTaokeReportSettleDAO.updateEmailStatus(smsSuccessList, ReportStatusEnums.SEND_SMS_SUCCESS.getValue());
        }
        if (failList.size() > 0) {
            ddzTaokeReportSettleDAO.updateEmailStatus(failList, ReportStatusEnums.SEND_FAIL.getValue());
        }
    }

    @Override
    protected void addResult2List(ReportStatusEnums result, Integer id, List<Integer> mailSuccessList,
                                  List<Integer> smsSuccessList, List<Integer> failList) {
        if (result == ReportStatusEnums.SEND_EMAIL_SUCCESS) {
            mailSuccessList.add(id);
        } else if (result == ReportStatusEnums.SEND_SMS_SUCCESS) {
            smsSuccessList.add(id);
        } else if (result == ReportStatusEnums.SEND_FAIL) {
            failList.add(id);
        } else {
            logger.error("send settle report result error: " + result +", id: " + id);
        }

    }

    @Override
    protected boolean isCanSend(DdzTaokeReportSettleDO settleDO, List<DdzTaokeReportDO> reportDOs) {
        if (reportDOs == null || reportDOs.size() < 1) {
            return false;
        }
        if (settleDO == null
            || !StringUtils.equals(settleDO.getSettleStatus(), SettleStatusEnums.SETTLE_SUCCESS.getValue())
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
        if (settleReport.getEmailStatus().equals(ReportStatusEnums.SEND_EMAIL_SUCCESS)) {
            return true;
        }
        if (settleReport.getEmailStatus().equals(ReportStatusEnums.SEND_SMS_SUCCESS)) {
            return true;
        }
        return false;
    }

    @Override
    protected List<DdzTaokeReportDO> queryTaokeReport(Long settleId) {
        TaokeReportSearchCondition condition = new TaokeReportSearchCondition();
        condition.setSettleId(settleId);
        condition.setSettleStatus(SettleStatusEnums.SETTLE_SUCCESS.getValue());
        return ddzTaokeReportService.getReports(condition);
    }

    @Override
    protected ReportStatusEnums sendReportEmail(String emailAddress, Map<String, Object> context,
                                                DdzTaokeReportSettleDO settleDO) {
        if (sendMailService.send("email-rebate-notice", emailAddress, context)) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                logger.error("sleep error", e);
            }
            // sendMailService.send("email-rebate-notice", "diandianzhetest@163.com", context);
            reportMailLog.info("[success] send mail to " + emailAddress + " ,alipayId: " + settleDO.getSettleAlipay()
                               + ", settleId: " + settleDO.getSettleId());
            return ReportStatusEnums.SEND_EMAIL_SUCCESS;
        } else {
            reportMailLog.info("[error] send mail to " + emailAddress + " ,alipayId: " + settleDO.getSettleAlipay()
                               + " ,settleId: " + settleDO.getSettleId());
            return ReportStatusEnums.NULL;
        }
    }

    @Override
    protected ReportStatusEnums sendReportSms(String mobile, Map<String, Object> context, DdzTaokeReportSettleDO settleDO) {
    	if(settleDO == null){
    		return ReportStatusEnums.SEND_FAIL ;
    	}
    	SmsMtDO mt = new SmsMtDO() ;
    	
    	BigDecimal total = settleDO.getSettleFee() ;
    	
    	if(total == null){
    		return ReportStatusEnums.SEND_FAIL ;
    	}
    	
    	String privateMobile = MobileUtil.getPrivateMobile(mobile) ;
    	
    	if(OutCodeEnums.toOutCodeEnums(settleDO.getOutcodeType()) == OutCodeEnums.DDZ_ACCOUNT_ID_JFB){
    		Integer settleJfb = settleDO.getSettleJfb() ;
    		mt.setMsgParameter(new String[]{String.valueOf(settleJfb) , privateMobile}) ;
	    	mt.setBusinessId(SmsBusinessEnums.DDZ_JFB_SETTLE_SMS) ;
	    	mt.setToMobile(mobile) ;
    	} else {
    		//现金类返现
    		String settleFee = DecimalUtils.format(total , "0.00") ;
	    	mt.setMsgParameter(new String[]{settleFee , privateMobile}) ;
	    	mt.setBusinessId(SmsBusinessEnums.DDZ_SETTLE_SMS) ;
	    	mt.setToMobile(mobile) ;
    	}
    	SmsMtResult result = sendSmsService.sendMessage(mt) ;
    	
        return result.isSuccess() ? ReportStatusEnums.SEND_SMS_SUCCESS : ReportStatusEnums.SEND_FAIL;
    }

}
