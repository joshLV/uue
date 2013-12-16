package com.zihexin.business_interface.exception;

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: 12-4-16
 * Time: 下午1:10
 * To change this template use File | Settings | File Templates.
 */
public class ErrorException extends Exception {
    private String msg;
    private boolean isLog;

    public String getMsg() {
        if (msg == null || "".equals(msg)) {
            msg = "未知的内部错误";
        }
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isLog() {
        return isLog;
    }

    public void setLog(boolean log) {
        isLog = log;
    }

    public ErrorException(String msg, boolean isLog) {
        this.setMsg(msg);
        this.setLog(isLog);
    }
}
