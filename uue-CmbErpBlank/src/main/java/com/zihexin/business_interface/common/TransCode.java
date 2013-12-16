package com.zihexin.business_interface.common;

/**
 * 响应吗
 * User: Administrator
 *
 */
public enum TransCode {

    //自定义 银行交易码
    BOCTransCode("000001");

    // 银行编码
    private String bocTransCode;

    public String getBocTransCode() {
        return bocTransCode;
    }

    private TransCode(String respCode){
        this.bocTransCode = respCode;
    }
}
