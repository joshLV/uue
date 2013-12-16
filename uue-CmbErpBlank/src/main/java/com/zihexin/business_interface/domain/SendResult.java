package com.zihexin.business_interface.domain;

/**
 * Created by IntelliJ IDEA.
 * User: yj
 * Date: 12-3-30
 * Time: ÏÂÎç1:48
 * To change this template use File | Settings | File Templates.
 */
public class SendResult {
    private String code;
    private String msg;


    public SendResult() {
        code = "0";//0Ê§°Ü   1:³É¹¦
        msg = "";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
