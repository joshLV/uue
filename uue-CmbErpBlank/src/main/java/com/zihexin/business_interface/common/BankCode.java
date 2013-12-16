package com.zihexin.business_interface.common;

/**
 * 银行编码
 * User: Administrator
 * Date: 12-4-26
 * Time: 上午11:28
 * To change this template use File | Settings | File Templates.
 */
public enum BankCode {

    ABC("ABC"), //银行
    BOC("BOC"), //银行
    CMB("CMB"); //招商银行
    private String bankCode;  // 银行编码

    public String getBankCode() {
        return bankCode;
    }
    private BankCode(String code){
        this.bankCode = code;
    }
}
