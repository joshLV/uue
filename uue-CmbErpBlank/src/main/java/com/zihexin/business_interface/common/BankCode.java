package com.zihexin.business_interface.common;

/**
 * ���б���
 * User: Administrator
 * Date: 12-4-26
 * Time: ����11:28
 * To change this template use File | Settings | File Templates.
 */
public enum BankCode {

    ABC("ABC"), //����
    BOC("BOC"), //����
    CMB("CMB"); //��������
    private String bankCode;  // ���б���

    public String getBankCode() {
        return bankCode;
    }
    private BankCode(String code){
        this.bankCode = code;
    }
}
