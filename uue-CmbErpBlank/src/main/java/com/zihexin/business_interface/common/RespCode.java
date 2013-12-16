package com.zihexin.business_interface.common;

/**
 * 响应吗
 */
public enum RespCode {
    ABCRespCode("0000");//银行
    private String AbcRespCode;  // 银行编码

    public String getAbcRespCode() {
        return AbcRespCode;
    }

    private RespCode(String respCode){
        this.AbcRespCode = respCode;
    }
}
