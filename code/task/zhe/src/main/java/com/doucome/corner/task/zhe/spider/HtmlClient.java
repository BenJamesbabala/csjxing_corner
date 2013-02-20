package com.doucome.corner.task.zhe.spider;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.DefaultMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HtmlClient {

    private final static Log log = LogFactory.getLog(HtmlClient.class);

    public static String wgetPage(String baseUrl, Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return wgetPage(baseUrl);
        }

        StringBuilder sb = new StringBuilder(baseUrl);
        sb.append("?");
        for (Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return wgetPage(sb.toString());
    }

    public static String wgetPage(String url) {
        if (url == null || url.trim().isEmpty()) {
            return null;
        }

        HttpClient client = new HttpClient();
        //设置agent信息  伪装一下
        client.getParams().setParameter(HttpMethodParams.USER_AGENT,"Mozilla/5.0 (Windows NT 6.1; rv:15.0) Gecko/20100101 Firefox/15.0.1");
        client.setConnectionTimeout(3000);
        GetMethod method = new GetMethod(url);

        DefaultMethodRetryHandler retryhandler = new DefaultMethodRetryHandler();
        retryhandler.setRequestSentRetryEnabled(false);
        retryhandler.setRetryCount(3);
        method.setMethodRetryHandler(retryhandler);

        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                log.error("Method failed: " + method.getStatusLine());

                return null;
            }

            // Read the response body.
            byte[] responseBody = method.getResponseBody();

            return new String(responseBody, "UTF-8");
        } catch (IOException e) {
            log.error("Failed to download file.", e);
        } finally {
            // Release the connection.
            method.releaseConnection();
        }

        return null;
    }

}
