package com.longxi.data.obj;

/**
 * @param <T>
 */
public class Result<T> extends BaseDO {
    /**
     *
     */
    private boolean success;
    /**
     *
     */
    private String errMsg;
    /**
     *
     */
    private String errCode;
    /**
     *
     */
    private T value;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrMsg() {
        if (this.success && null == errMsg) {
            return "success";
        }
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
