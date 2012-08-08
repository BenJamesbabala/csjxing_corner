package com.doucome.corner.biz.zhe.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.enums.EmailStatusEnums;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.mail.SendMailService;
import com.doucome.corner.biz.core.mail.ToolsMapFactory;
import com.doucome.corner.biz.core.utils.EmailUtil;
import com.doucome.corner.biz.core.utils.MobileUtil;
import com.doucome.corner.biz.core.utils.ValidateUtil;
import com.doucome.corner.biz.dal.DdzTaokeReportSettleDAO;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.biz.zhe.service.DdzReportMailService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;
import com.doucome.corner.biz.zhe.service.DdzUserService;

/**
 * 类DdzReportMailServiceImpl.java的实现描述：DdzReportMailService的默认实现
 * 
 * @author ib 2012-5-7 下午11:34:15
 */
public class DdzReportMailServiceImpl implements DdzReportMailService {

    private static final Log            logger        = LogFactory.getLog(DdzReportMailServiceImpl.class);
    private static final Log            reportMailLog = LogFactory.getLog(LogConstant.reportMail_log);

    @Autowired
    private SendMailService             sendMailService;
    @Autowired
    private DdzTaokeReportService       ddzTaokeReportService;
    @Autowired
    private DdzTaokeReportSettleService ddzTaokeReportSettleService;
    @Autowired
    private DdzAccountService           ddzAccountService;
    @Autowired
    private DdzUserService              ddzUserService;
    @Autowired
    private DdzTaokeReportSettleDAO     ddzTaokeReportSettleDAO;

    @Override
    public int sendTaokeReportMailsForIds(List<Integer> settleIdList) {
        int count = 0;
        for (Integer settleId : settleIdList) {
            if (sendTaokeReportMail(settleId.longValue())) {
                count++;
            }
        }
        return count;
    }

    public int sendTaokeReportMails(List<DdzTaokeReportSettleDO> settleList) {
        int count = 0;
        List<Integer> successList = new ArrayList<Integer>();
        List<Integer> failList = new ArrayList<Integer>();
        for (DdzTaokeReportSettleDO settleReport : settleList) {
            if (settleReport.getEmailStatus() != null
                && settleReport.getEmailStatus().equals(EmailStatusEnums.SEND_SUCCESS)) {
                continue;
            }
            if (sendTaokeReportMail(settleReport.getId())) {
                successList.add(settleReport.getId().intValue());
                count++;
            } else {
                failList.add(settleReport.getId().intValue());
            }
        }
        if (successList.size() > 0) {
            ddzTaokeReportSettleDAO.updateEmailStatus(successList, EmailStatusEnums.SEND_SUCCESS.getValue());
        }
        if (failList.size() > 0) {
            ddzTaokeReportSettleDAO.updateEmailStatus(failList, EmailStatusEnums.SEND_FAIL.getValue());
        }
        return count;
    }

    @Override
    public boolean sendTaokeReportMail(Long settleId) {
        String emailAddress = null;
        String userName = null;
        if (settleId == null) {
            return false;
        }
        try {
            TaokeReportSearchCondition condition = new TaokeReportSearchCondition();
            condition.setSettleId(settleId);
            condition.setSettleStatus(SettleStatusEnums.SETTLE_SUCCESS.getValue());
            List<DdzTaokeReportDO> reportDOs = ddzTaokeReportService.getReports(condition);
            if (reportDOs == null || reportDOs.size() < 1) {
                return false;
            }
            DdzTaokeReportSettleDO settleDO = ddzTaokeReportSettleService.getById(settleId.longValue());
            // 不为null，为成功状态
            if (settleDO == null
                || !StringUtils.equals(settleDO.getSettleStatus(), SettleStatusEnums.SETTLE_SUCCESS.getValue())
                || StringUtils.isEmpty(settleDO.getSettleAlipay())) {
                return false;
            }

            DdzAccountDO accountDO = ddzAccountService.queryAccountDOByAlipayId(settleDO.getSettleAlipay());
            if (accountDO != null && accountDO.getUid() != null) {
                DdzUserDO userDO = ddzUserService.getByUid(accountDO.getUid());
                if (userDO != null) {
                    if (StringUtils.isNotBlank(userDO.getEmail())) {
                        emailAddress = userDO.getEmail();
                    }
                    userName = userDO.getLoginId();
                }
            }

            /**
             * 没有注册用户或用户信息中没有有效地址的话，取支付宝id
             */
            if (emailAddress == null && ValidateUtil.checkIsEmail(settleDO.getSettleAlipay())) {
                emailAddress = settleDO.getSettleAlipay();
            }

            /**
             * 两个途径都拿不到邮箱地址，无法发送邮件
             */
            if (emailAddress == null) {
                reportMailLog.info("[fail] send mail,alipayId: " + settleDO.getSettleAlipay() + ", settleId "
                                   + settleId + ", username:" + userName);
                return false;
            }
            userName = (userName != null) ? userName : emailAddress;

            String privateAlipayId = null;
            if (ValidateUtil.checkIsEmail(settleDO.getSettleAlipay())) {
                privateAlipayId = EmailUtil.getPrivateEmail(settleDO.getSettleAlipay());
            } else {
                privateAlipayId = MobileUtil.getPrivateMobile(settleDO.getSettleAlipay());
            }
            Map<String, Object> context = ToolsMapFactory.generateDefaultToolsMap();
            context.put("username", userName);
            context.put("alipayId", privateAlipayId);
            context.put("reportDOs", reportDOs);
            context.put("settleDO", settleDO);
            context.put("emailAddress", emailAddress);
            if (sendMailService.send("email-rebate-notice", emailAddress, context)) {
                Thread.sleep(2000);
                // sendMailService.send("email-rebate-notice", "diandianzhetest@163.com", context);
                reportMailLog.info("[success] send mail to " + emailAddress + " ,alipayId: "
                                   + settleDO.getSettleAlipay() + ", settleId: " + settleId + " ,username:" + userName);
                return true;
            } else {
                reportMailLog.info("[error] send mail to " + emailAddress + " ,alipayId: " + settleDO.getSettleAlipay()
                                   + " ,settleId: " + settleId + " ,username:" + userName);
                return false;
            }
        } catch (Exception e) {
            reportMailLog.info("[error] send mail,settleId: " + settleId);
            logger.error("send report mail fail, settleid: " + settleId, e);
            return false;
        }
    }
}
