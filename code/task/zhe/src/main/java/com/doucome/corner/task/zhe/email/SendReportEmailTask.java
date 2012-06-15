package com.doucome.corner.task.zhe.email;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.enums.EmailStatusEnums;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.service.DdzReportMailService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;

/**
 * 类SendReportEmailTask.java的实现描述：打款后邮件发送任务
 * 
 * @author ib 2012-5-12 下午09:44:29
 */
public class SendReportEmailTask {

    private static final Log            reportMailLog = LogFactory.getLog(LogConstant.reportMail_log);
    private static final Log            logger        = LogFactory.getLog(SendReportEmailTask.class);

    DdzReportMailService                ddzReportMailService;
    DdzTaokeReportService               ddzTaokeReportService;
    DdzTaokeReportSettleSearchCondition condition;

    public static void main(String[] args) {
        reportMailLog.info("start to send report email.");
        try {

            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            DdzReportMailService ddzReportMailService = (DdzReportMailService) ctx.getBean("ddzReportMailService");
            DdzTaokeReportService ddzTaokeReportService = (DdzTaokeReportService) ctx.getBean("ddzTaokeReportService");

            SendReportEmailTask task = new SendReportEmailTask();
            task.setDdzReportMailService(ddzReportMailService);
            task.setDdzTaokeReportService(ddzTaokeReportService);
            task.setCondition(getSearchCondition(args));
            task.run();
            reportMailLog.info("send report email end.");
        } catch (Exception e) {
            logger.error("send report email error", e);
        }
        System.exit(0);
    }

    public void run() {
        List<DdzTaokeReportSettleDO> settleList = ddzTaokeReportService.getSettleReports(condition);
        ddzReportMailService.sendTaokeReportMails(settleList);
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
    public static DdzTaokeReportSettleSearchCondition getSearchCondition(String[] args) {
        DdzTaokeReportSettleSearchCondition condition = new DdzTaokeReportSettleSearchCondition();
        condition.setSettleStatus(SettleStatusEnums.SETTLE_SUCCESS.getValue());
        if (args.length > 0) {
            String type = args[0];
            if (StringUtils.equalsIgnoreCase(type, "U")) {
                condition.setEmailStatus(EmailStatusEnums.UNSEND.getValue());
                return condition;
            } else if (StringUtils.isNumeric(type)) {
                int inDays = Integer.valueOf(type);
                condition.setEmailStatus(EmailStatusEnums.NULL.getValue());
                condition.setSettleInDays(inDays);
                return condition;
            }
        }
        // 默认三天内打款成功
        condition.setEmailStatus(EmailStatusEnums.NULL.getValue());
        condition.setSettleInDays(3);
        return condition;
    }

    public void setDdzReportMailService(DdzReportMailService ddzReportMailService) {
        this.ddzReportMailService = ddzReportMailService;
    }

    public void setDdzTaokeReportService(DdzTaokeReportService ddzTaokeReportService) {
        this.ddzTaokeReportService = ddzTaokeReportService;
    }

    public void setCondition(DdzTaokeReportSettleSearchCondition condition) {
        this.condition = condition;
    }

}
