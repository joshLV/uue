package com.zihexin.business_interface.common;

/**
 * ��Ӧ��
 * User: Administrator
 *
 */
public enum TransCode {

    //�Զ��� ���н�����
    BOCTransCode("000001");

    // ���б���
    private String bocTransCode;

    public String getBocTransCode() {
        return bocTransCode;
    }

    private TransCode(String respCode){
        this.bocTransCode = respCode;
    }
}
