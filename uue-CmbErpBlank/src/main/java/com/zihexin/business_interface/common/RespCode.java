package com.zihexin.business_interface.common;

/**
 * ��Ӧ��
 */
public enum RespCode {
    ABCRespCode("0000");//����
    private String AbcRespCode;  // ���б���

    public String getAbcRespCode() {
        return AbcRespCode;
    }

    private RespCode(String respCode){
        this.AbcRespCode = respCode;
    }
}
