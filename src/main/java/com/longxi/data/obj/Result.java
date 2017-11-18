package com.longxi.data.obj;

import java.io.Serializable;

/**
 * Created by hanyou.hww on 2016/3/12.
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 7403416560805213485L;

    /**
     *
     */
    private boolean success;
    /**
     *
     */
    private String errorCode;
    /**
     *
     */
    private String errorInfo;
    /**
     *
     */
    private T resultData;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }
}
