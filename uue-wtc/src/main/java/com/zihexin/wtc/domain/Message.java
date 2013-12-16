package com.zihexin.wtc.domain;

import com.zihexin.wtc.common.UUIDGenerator;

import java.rmi.server.UID;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: 12-4-16
 * Time: ����11:27
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    private String id;
    private String msg;
    private String ip;

    public Message() {
        id = UUIDGenerator.getUUID();
    }

    public String getId() {
        return id;
    }

    public String getLogId() {
        return "LOGID[" + id + "]";
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
