package com.zihexin.business_interface.domain;

import java.io.Serializable;

/**
 * ����ָ����Ϣ
 * User: Administrator
 * Date: 12-4-27
 * Time: ����11:20
 * To change this template use File | Settings | File Templates.
 */
public class BankCommand implements Serializable {

    private static final long serialVersionUID = -2386381252195481958L;

    //ָ��ID
    private Long Id;

    //ָ������
    private String commandCode;

    //�ֶ�����
    private String fieldName;

    //�ļ�·��
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
