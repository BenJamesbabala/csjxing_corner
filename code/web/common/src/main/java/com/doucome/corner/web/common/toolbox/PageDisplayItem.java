package com.doucome.corner.web.common.toolbox;

import java.util.List;

public class PageDisplayItem {

    public static final int DEFAULT_ITEM_SIZE = 6;
    public static final int DEFAULT_HALF_SIZE = DEFAULT_ITEM_SIZE / 2;
    // 总记录数
    private long            totalRecord;

    // 每页显示的记录数目
    private int             pageSize          = 2;

    // 总页数
    private long            totalPage;

    // 当前显示页码
    private long            currPage;

    // 要显示的第一条记录数
    private long            recordBegin;

    // 要显示的记录数
    private long            recordLimit;

    private List            list              = null;
    /** 显示使用 */
    private boolean         hasPrevItem       = false;
    private boolean         hasNextItem       = false;
    private long            prevPage;
    private long            nextPage;
    private long            startPageIndex;
    private long            endPageIndex;

    private PageDisplayItem(){

    }

    public PageDisplayItem(long totalRecord, long totalPage, long currPage, long recordBegin, long recordLimit,
                           int pageSize){
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
        this.currPage = currPage;
        this.recordBegin = recordBegin;
        this.recordLimit = recordLimit;
        this.pageSize = pageSize;
        generate();
    }

    public void generate() {
        if (currPage < DEFAULT_HALF_SIZE) {
            if (totalPage <= DEFAULT_ITEM_SIZE) {
                startPageIndex = 1;
                endPageIndex = totalPage;

            } else {
                hasNextItem = true;
                nextPage = currPage + 1;
                startPageIndex = 1;
                endPageIndex = DEFAULT_ITEM_SIZE;
            }
        } else {
            hasPrevItem = true;
            prevPage = currPage - 1;
            if (totalPage > (currPage + DEFAULT_HALF_SIZE)) {
                startPageIndex = currPage - DEFAULT_HALF_SIZE + 1;
                endPageIndex = currPage + DEFAULT_HALF_SIZE;
                hasNextItem = true;
                nextPage = currPage + 1;
            } else {
                endPageIndex = totalPage;
                startPageIndex = totalPage - DEFAULT_ITEM_SIZE + 1;
            }
        }
        
        if (endPageIndex < 1) {
            endPageIndex = 1;
        }
        if (totalPage < 1) {
            totalPage = 1;
        }
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        if (totalRecord < 0) {
            this.totalRecord = 0;
        } else {
            this.totalRecord = totalRecord;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 0) {
            this.pageSize = 2;
        } else {
            this.pageSize = pageSize;
        }
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getCurrPage() {
        return currPage;
    }

    public void setCurrPage(long currPage) {
        this.currPage = currPage;
    }

    public long getRecordBegin() {
        return recordBegin;
    }

    public void setRecordBegin(long recordBegin) {
        this.recordBegin = recordBegin;
    }

    public long getRecordLimit() {
        return recordLimit;
    }

    public void setRecordLimit(long recordLimit) {
        this.recordLimit = recordLimit;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public long getNextPage() {
        return nextPage;
    }

    public void setNextPage(long nextPage) {
        this.nextPage = nextPage;
    }

    public long getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(long prevPage) {
        this.prevPage = prevPage;
    }

    public boolean isHasPrevItem() {
        return hasPrevItem;
    }

    public void setHasPrevItem(boolean hasPrevItem) {
        this.hasPrevItem = hasPrevItem;
    }

    public boolean isHasNextItem() {
        return hasNextItem;
    }

    public void setHasNextItem(boolean hasNextItem) {
        this.hasNextItem = hasNextItem;
    }

    public long getStartPageIndex() {
        return startPageIndex;
    }

    public void setStartPageIndex(long startPageIndex) {
        this.startPageIndex = startPageIndex;
    }

    public long getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(long endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

}
