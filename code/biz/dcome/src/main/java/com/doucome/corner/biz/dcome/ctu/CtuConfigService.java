package com.doucome.corner.biz.dcome.ctu;


/**
 * 类CtuConfigService.java的实现描述：监控相关配置
 * 
 * @author ib 2012-8-19 下午2:51:19
 */
public class CtuConfigService {

    /**
     * 每增加多少投票计算一次
     */
    private int calculateFrequency;
    /**
     * 一个IP的投票次数限制
     */
    private int rateLimitForIP;
    /**
     * IP限制时间跨度（秒）
     */
    private int ipLimitSeconds;

    /**
     * 根据多少分钟以内的数据进行行为分析
     */
    private int calculateRateInMinutes;

    /**
     * 成功投票多少次，必须验证码
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
