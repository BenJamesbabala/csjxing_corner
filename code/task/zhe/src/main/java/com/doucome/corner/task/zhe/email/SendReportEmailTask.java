package com.doucome.corner.task.zhe.email;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.enums.ReportStatusEnums;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.service.DdzSendReportService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;

/**
 * ��SendReportEmailTask.java��ʵ�������������ʼ���������
 * 
 * @author ib 2012-5-12 ����09:44:29
 */
public class SendReportEmailTask {

    private static final Log            reportMailLog = LogFactory.getLog(LogConstant.reportMail_log);
    private static final Log            logger        = LogFactory.getLog(SendReportEmailTask.class);

    DdzSendReportService                ddzSendSuccessSettleReportService;
    DdzSendReportService                ddzSendDelaySettleReportService;
    DdzTaokeReportService               ddzTaokeReportService;
    DdzTaokeReportSettleSearchCondition normalCondition;
    DdzTaokeReportSettleSearchCondition delayCondition;

    public static void main(String[] args) {
        reportMailLog.info("start to send report email.");
        try {

            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            DdzSendReportService ddzSendSuccessSettleReportService = (DdzSendReportService) ctx.getBean("ddzSendSuccessSettleReportService");
            DdzSendReportService ddzSendDelaySettleReportService = (DdzSendReportService) ctx.getBean("ddzSendDelaySettleReportService");
            DdzTaokeReportService ddzTaokeReportService = (DdzTaokeReportService) ctx.getBean("ddzTaokeReportService");

            SendReportEmailTask task = new SendReportEmailTask();
            task.setDdzTaokeReportService(ddzTaokeReportService);
            task.setDdzSendSuccessSettleReportService(ddzSendSuccessSettleReportService);
            task.setNormalCondition(getNormalSearchCondition(args));
            task.setDdzSendDelaySettleReportService(ddzSendDelaySettleReportService);
            task.setDelayCondition(getDelaySearchCondition(args));
            task.run();
            reportMailLog.info("send report email end.");
        } catch (Exception e) {
            logger.error("send report email error", e);
        }
        System.exit(0);
    }

    public void run() {
    	// �Ѿ�ֱ�Ӵ��ɹ���
        List<DdzTaokeReportSettleDO> settleSuccessList = ddzTaokeReportService.getSettleReports(normalCondition);
        // delay����ɹ�
        normalCondition.setEmailStatus(ReportStatusEnums.SEND_DELAY.getValue());
        List<DdzTaokeReportSettleDO> settleDelaySuccessList = ddzTaokeReportService.getSettleReports(normalCondition);
        settleSuccessList.addAll(settleDelaySuccessList);
        
//        //����֪ͨ�Լ���
//        DdzTaokeReportSettleDO settleMock = new DdzTaokeReportSettleDO() ;
//        
//        settleMock.setAlipayStatus(SettleStatusEnums.SETTLE_SUCCESS.getValue()) ;
//        settleMock.setSettleAlipay("13777840845") ;
//        settleMock.setSettleFee(new BigDecimal(0));
//        settleMock.setSettleStatus(SettleStatusEnums.SETTLE_SUCCESS.getValue()) ;
//        settleMock.setEmailStatus(SettleStatusEnums.UNSETTLE.getValue()) ;
//        
//        settleSuccessList.add(settleMock) ;
        ddzSendSuccessSettleReportService.sendTaokeReportMails(settleSuccessList);
        // delay��δ���
        List<DdzTaokeReportSettleDO> delaySettleList = ddzTaokeReportService.getSettleReports(delayCondition);
        ddzSendDelaySettleReportService.sendTaokeReportMails(delaySettleList);
    }

    /**
     * ��������������õ���ѯ����<br/>
     * U : ���ͱ�־ΪU�� <br/>
     * ����n ������N���ڴ��� <br/>
     * ������ ����3���ڴ���
     * 
     * @param args
     * @return ��ѯ����
     */
    public static DdzTaokeReportSettleSearchCondition getNormalSearchCondition(String[] args) {
        DdzTaokeReportSettleSearchCondition condition = new DdzTaokeReportSettleSearchCondition();
        condition.setSettleStatus(SettleStatusEnums.SETTLE_SUCCESS.getValue());
        if (args.length > 0) {
            String type = args[0];
            if (StringUtils.equalsIgnoreCase(type, "U")) {
                condition.setEmailStatus(ReportStatusEnums.UNSEND.getValue());
                return condition;
            } else if (StringUtils.isNumeric(type)) {
                int inDays = Integer.valueOf(type);
                condition.setEmailStatus(ReportStatusEnums.NULL.getValue());
                condition.setSettleInDays(inDays);
                return condition;
            }
        }
        // Ĭ�������ڴ��ɹ�
        condition.setEmailStatus(ReportStatusEnums.NULL.getValue());
        condition.setSettleInDays(3);
        return condition;
    }

    /**
     * @param args
     * @return �ӳٲ�ѯ����
     */
    public static DdzTaokeReportSettleSearchCondition getDelaySearchCondition(String[] args) {
        DdzTaokeReportSettleSearchCondition condition = new DdzTaokeReportSettleSearchCondition();
        condition.setSettleStatus(SettleStatusEnums.DELAY_SETTLE.getValue());
        if (args.length > 0) {
            String type = args[0];
            if (StringUtils.equalsIgnoreCase(type, "U")) {
                condition.setEmailStatus(ReportStatusEnums.UNSEND.getValue());
                return condition;
            } else if (StringUtils.isNumeric(type)) {
                int inDays = Integer.valueOf(type);
                condition.setEmailStatus(ReportStatusEnums.NULL.getValue());
                condition.setSettleInDays(inDays);
                return condition;
            }
        }
        // Ĭ�������ڴ��ɹ�
        condition.setEmailStatus(ReportStatusEnums.NULL.getValue());
        condition.setSettleInDays(3);
        return condition;
    }

    public void setDdzSendSuccessSettleReportService(DdzSendReportService ddzSendSuccessSettleReportService) {
        this.ddzSendSuccessSettleReportService = ddzSendSuccessSettleReportService;
    }

    public void setDdzTaokeReportService(DdzTaokeReportService ddzTaokeReportService) {
        this.ddzTaokeReportService = ddzTaokeReportService;
    }

    public void setNormalCondition(DdzTaokeReportSettleSearchCondition normalCondition) {
        this.normalCondition = normalCondition;
    }

    public void setDelayCondition(DdzTaokeReportSettleSearchCondition delayCondition) {
        this.delayCondition = delayCondition;
    }

    public void setDdzSendDelaySettleReportService(DdzSendReportService ddzSendDelaySettleReportService) {
        this.ddzSendDelaySettleReportService = ddzSendDelaySettleReportService;
    }

}
