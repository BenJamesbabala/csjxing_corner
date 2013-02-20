package com.doucome.corner.biz.zhe.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.enums.ReportStatusEnums;
import com.doucome.corner.biz.core.mail.SendMailService;
import com.doucome.corner.biz.core.mail.ToolsMapFactory;
import com.doucome.corner.biz.core.sms.SendSmsService;
import com.doucome.corner.biz.core.utils.EmailUtil;
import com.doucome.corner.biz.core.utils.MobileUtil;
import com.doucome.corner.biz.core.utils.ValidateUtil;
import com.doucome.corner.biz.dal.DdzTaokeReportSettleDAO;
import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;

/**
 * 类DdzReportMailServiceImpl.java的实现描述：DdzReportMailService的默认实现
 * 
 * @author ib 2012-5-7 下午11:34:15
 */
public abstract class DdzAbstractSendReportService implements DdzSendReportService {

    private static final Log              logger        = LogFactory.getLog(DdzAbstractSendReportService.class);
    protected static final Log            reportMailLog = LogFactory.getLog(LogConstant.reportMail_log);

    @Autowired
    protected SendMailService             sendMailService;
    @Autowired
    protected DdzTaokeReportService       ddzTaokeReportService;
    @Autowired
    protected DdzTaokeReportSettleService ddzTaokeReportSettleService;
    @Autowired
    protected DdzAccountService           ddzAccountService;
    @Autowired
    protected DdzUserService              ddzUserService;
    @Autowired
    protected DdzTaokeReportSettleDAO     ddzTaokeReportSettleDAO;
    
    @Autowired
    protected SendSmsService sendSmsService ;

    public int sendTaokeReportMails(List<DdzTaokeReportSettleDO> settleList) {
        List<Integer> mailSuccessList = new ArrayList<Integer>();
        List<Integer> smsSuccessList = new ArrayList<Integer>();
        List<Integer> failList = new ArrayList<Integer>();
        for (DdzTaokeReportSettleDO settleReport : settleList) {
            if (hasSended(settleReport)) {
                continue;
            }

            ReportStatusEnums result = sendTaokeReport(settleReport.getId());
            addResult2List(result, settleReport.getId().intValue(), mailSuccessList, smsSuccessList, failList);
        }

        updateStatus(mailSuccessList, smsSuccessList, failList);

        return mailSuccessList.size() + smsSuccessList.size();
    }

    public ReportStatusEnums sendTaokeReport(Long settleId) {
        ReportStatusEnums fail = ReportStatusEnums.SEND_FAIL;
        if (settleId == null) {
            return fail;
        }

        String emailAddress = null;
        String mobileNo = null;
        String userName = null;

        try {
            List<DdzTaokeReportDO> reportDOs = queryTaokeReport(settleId);
            DdzTaokeReportSettleDO settleDO = ddzTaokeReportSettleService.getById(settleId.longValue());

            if (!isCanSend(settleDO, reportDOs)) {
                return fail;
            }

            DdzAccountDO accountDO = ddzAccountService.queryAccountDOByAlipayId(settleDO.getSettleAlipay());
            if (accountDO != null && accountDO.getUid() != null) {
                DdzUserDO userDO = ddzUserService.getByUid(accountDO.getUid());
                if (userDO != null) {
                    if (ValidateUtil.checkIsEmail(userDO.getEmail())) {
                        emailAddress = userDO.getEmail();
                    }
                    if (ValidateUtil.checkIsMobile(userDO.getMobile())) {
                        mobileNo = userDO.getMobile();
                    }
                    userName = userDO.getLoginId();
                }
            }

            /**
             * 没有注册用户或用户信息中没有有效邮箱地址的话，取支付宝id
             */
            if (emailAddress == null && ValidateUtil.checkIsEmail(settleDO.getSettleAlipay())) {
                emailAddress = settleDO.getSettleAlipay();
            }
            /**
             * 没有注册用户或用户信息中没有有效手机号的话，取支付宝id
             */
            if (mobileNo == null && ValidateUtil.checkIsMobile(settleDO.getSettleAlipay())) {
                mobileNo = settleDO.getSettleAlipay();
            }

            /**
             * 两个途径都拿不到邮箱地址，无法发送邮件
             */
            if (emailAddress == null && mobileNo == null) {
                reportMailLog.info("[fail] send report,alipayId: " + settleDO.getSettleAlipay() + ", settleId "
                                   + settleId + ", username:" + userName);
                return fail;
            }

            String privateAlipayId = generalPrivateAlipayId(settleDO.getSettleAlipay());

            Map<String, Object> context = ToolsMapFactory.generateDefaultToolsMap();
            context.put("alipayId", privateAlipayId);
            context.put("reportDOs", reportDOs);
            context.put("settleDO", settleDO);
            context.put("emailAddress", emailAddress);

            if (mobileNo != null) {
                userName = (userName != null) ? userName : mobileNo;
                context.put("username", userName);
                return sendReportSms(mobileNo, context, settleDO);
            } else {
                userName = (userName != null) ? userName : emailAddress;
                context.put("username", userName);
                return sendReportEmail(emailAddress, context, settleDO);
            }
        } catch (Exception e) {
            reportMailLog.info("[error] send mail,settleId: " + settleId);
            logger.error("send report mail fail, settleid: " + settleId, e);
            return fail;
        }
    }

    private String generalPrivateAlipayId(String alipayId) {
        if (ValidateUtil.checkIsEmail(alipayId)) {
            return EmailUtil.getPrivateEmail(alipayId);
        } else {
            return MobileUtil.getPrivateMobile(alipayId);
        }

    }

    protected abstract void updateStatus(List<Integer> mailSuccessList, List<Integer> smsSuccessList,
                                         List<Integer> failList);

    /**
     * 保存发送结果
     * 
     * @param result
     * @param id
     * @param mailSuccessList
     * @param smsSuccessList
     * @param failList
     * @return true:发送成功，false：失败
     */
    protected abstract void addResult2List(ReportStatusEnums result, Integer id, List<Integer> mailSuccessList,
                                           List<Integer> smsSuccessList, List<Integer> failList);

    /**
     * 判断是否符合发送条件
     * 
     * @param settleReport
     * @return
     */
    protected abstract boolean isCanSend(DdzTaokeReportSettleDO settleDO, List<DdzTaokeReportDO> reportDOs);

    /**
     * 判断是否已经发送此类邮件
     * 
     * @param settleReport
     * @return
     */
    protected abstract boolean hasSended(DdzTaokeReportSettleDO settleReport);

    /**
     * 提取明细
     * 
     * @param settleId
     * @return
     */
    protected abstract List<DdzTaokeReportDO> queryTaokeReport(Long settleId);

    /**
     * 发送邮件
     * 
     * @param emailAddress
     * @param context
     * @param settleDO
     * @return
     */
    protected abstract ReportStatusEnums sendReportEmail(String emailAddress, Map<String, Object> context,
                                                         DdzTaokeReportSettleDO settleDO);

    /**
     * 发送短信
     * 
     * @param mobile
     * @param context
     * @param settleDO
     * @return
     */
    protected abstract ReportStatusEnums sendReportSms(String mobile, Map<String, Object> context,
                                                       DdzTaokeReportSettleDO settleDO);
}
