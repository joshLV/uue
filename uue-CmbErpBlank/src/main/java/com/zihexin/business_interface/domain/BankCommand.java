package com.zihexin.business_interface.domain;

import java.io.Serializable;

/**
 * 银行指令信息
 * User: Administrator
 * Date: 12-4-27
 * Time: 上午11:20
 * To change this template use File | Settings | File Templates.
 */
public class BankCommand implements Serializable {

    private static final long serialVersionUID = -2386381252195481958L;

    //指令ID
    private Long Id;

    //指令名称
    private String commandCode;

    //字段名称
    private String fieldName;

    //文件路径
    private String filePath;

    public String getCommandCode() {
        return commandCode;
    }

    public void setCommandCode(String commandCode) {
        this.commandCode = commandCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
