package com.longxi.data.obj;

import java.io.Serializable;

/**
 * Created by liumzh on 16/3/13.
 * 返回结果封装对象
 */
public class ResultDO<T> implements Serializable {
    private static final long serialVersionUID = 5646624577868133750L;

    private boolean success;

    private String errMessage;

    private String errCode;

    private T object;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrMessage() {
        if (this.success && errMessage == null) {
            return "success";
        }
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    @Override
    public String toString() {
        return "ResultDO{" +
            "success=" + success +
            ", errMessage='" + errMessage + '\'' +
            ", errCode='" + errCode + '\'' +
            ", object=" + object +
            '}';
    }
}
