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
 * 类SendReportEmailTask.java的实现描述：打款后邮件发送任务
 * 
 * @author ib 2012-5-12 下午09:44:29
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
    	// 已经直接打款成功。
        List<DdzTaokeReportSettleDO> settleSuccessList = ddzTaokeReportService.getSettleReports(normalCondition);
        // delay后打款成功
        normalCondition.setEmailStatus(ReportStatusEnums.SEND_DELAY.getValue());
        List<DdzTaokeReportSettleDO> settleDelaySuccessList = ddzTaokeReportService.getSettleReports(normalCondition);
        settleSuccessList.addAll(settleDelaySuccessList);
        
//        //用于通知自己的
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
        // delay，未打款
        List<DdzTaokeReportSettleDO> delaySettleList = ddzTaokeReportService.getSettleReports(delayCondition);
        ddzSendDelaySettleReportService.sendTaokeReportMails(delaySettleList);
    }

    /**
     * 根据输入参数，得到查询条件<br/>
     * U : 发送标志为U的 <br/>
     * 数字n ：发送N天内打款的 <br/>
     * 其他： 发送3天内打款的
     * 
     * @param args
     * @return 查询条件
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
        // 默认三天内打款成功
        condition.setEmailStatus(ReportStatusEnums.NULL.getValue());
        condition.setSettleInDays(3);
        return condition;
    }

    /**
     * @param args
     * @return 延迟查询条件
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
        // 默认三天内打款成功
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
