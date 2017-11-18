package com.longxi.data.obj;

public class Query<T> {

    final static int DEFAULT_PAGE_SIZE = 10;

    final static int DEFAULT_PAGE_NUM = 1;

    T module;

    boolean success;

    boolean isAsc;

    int pageSize = DEFAULT_PAGE_SIZE;

    int totalPage;

    int pageNum = DEFAULT_PAGE_NUM;

    int totalSize;

    private String orderBy = "id";

    private boolean hasLimit = true;

    public boolean hasLimit() {
        return hasLimit;
    }

    public void setHasLimit(boolean hasLimit) {
        this.hasLimit = hasLimit;
    }

    public T getModule() {
        return module;
    }

    public void setModule(T moudle) {
        this.module = moudle;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getStart() {
        return (pageNum - 1) * pageSize;
    }

    public String getOrderBy() {
        if (isAsc) {
            return orderBy + " ASC ";
        }
        return orderBy + " DESC ";
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setAsc(boolean isAsc) {
        this.isAsc = isAsc;
    }

}
