package com.doucome.corner.biz.dcome.ctu;


/**
 * ��CtuConfigService.java��ʵ������������������
 * 
 * @author ib 2012-8-19 ����2:51:19
 */
public class CtuConfigService {

    /**
     * ÿ���Ӷ���ͶƱ����һ��
     */
    private int calculateFrequency;
    /**
     * һ��IP��ͶƱ��������
     */
    private int rateLimitForIP;
    /**
     * IP����ʱ���ȣ��룩
     */
    private int ipLimitSeconds;

    /**
     * ���ݶ��ٷ������ڵ����ݽ�����Ϊ����
     */
    private int calculateRateInMinutes;

    /**
     * �ɹ�ͶƱ���ٴΣ�������֤��
     */
    private int successTimeCheckCode;

    public int getCalculateFrequency() {
        return calculateFrequency;
    }

    public void setCalculateFrequency(int calculateFrequency) {
        this.calculateFrequency = calculateFrequency;
    }

    public int getRateLimitForIP() {
        return rateLimitForIP;
    }

    public void setRateLimitForIP(int rateLimitForIP) {
        this.rateLimitForIP = rateLimitForIP;
    }

    public int getIpLimitSeconds() {
        return ipLimitSeconds;
    }

    public void setIpLimitSeconds(int ipLimitSeconds) {
        this.ipLimitSeconds = ipLimitSeconds;
    }

    public int getCalculateRateInMinutes() {
        return calculateRateInMinutes;
    }

    public void setCalculateRateInMinutes(int calculateRateInMinutes) {
        this.calculateRateInMinutes = calculateRateInMinutes;
    }

    public int getSuccessTimeCheckCode() {
        return successTimeCheckCode;
    }

    public void setSuccessTimeCheckCode(int successTimeCheckCode) {
        this.successTimeCheckCode = successTimeCheckCode;
    }

}
