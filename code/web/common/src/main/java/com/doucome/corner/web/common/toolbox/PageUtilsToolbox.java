package com.doucome.corner.web.common.toolbox;

import org.apache.commons.lang.StringUtils;

/**
 * ��PageUtilsToolbox.java��ʵ����������ҳ����
 * 
 * @author ib 2012-4-22 ����04:29:33
 */
public class PageUtilsToolbox {

    public static final int DEFAULT_PAGE_SIZE = 20;

    public static PageDisplayItem getPageDisplayItem(long totalRecord, long currPage, int pageSize) {
        long recordBegin = 0;
        long recordLimit = pageSize;
        long totalPage = (totalRecord + pageSize - 1) / pageSize;
        if (currPage > totalPage) currPage = totalPage;
        if (currPage < 1) currPage = 1;
        recordBegin = (currPage - 1) * pageSize;
        if (currPage * pageSize > totalRecord) recordLimit = totalRecord - recordBegin;
        return new PageDisplayItem(totalRecord, totalPage, currPage, recordBegin, recordLimit, pageSize);
    }

    /**
     * Ĭ������ʾ20����¼
     * 
     * @param totalRecord �ܼ�¼��
     * @param currPage ��ǰҪ��ʾҳ��
     * @return
     */
    public static PageDisplayItem getPageDisplayItem(long totalRecord, long currPage) {
        return getPageDisplayItem(totalRecord, currPage, DEFAULT_PAGE_SIZE);
    }

    public static String getPageURL(String url) {
        if (StringUtils.contains(url, '?')) {
            return url;
        } else {
            return url + '?';
        }
    }
}
