package com.zihexin.business_interface.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 交易流水表
 * User: Administrator
 *
 */
public class TransRecode implements Serializable {

    private static final long serialVersionUID = 2762681365832601172L;

    //交易流水号
    private String transRecodeNo;

    //银行编码
    private String bankCode;

    //账号
    private String accountNo;

    //币种
    private String  dbCur;

    //金额
    private Double  amt;

    //请求时间
    private Date reqDate;

    //响应时间
    private Date respDate;

    //响应码
    private String respCode;

    //响应信息
    private String  respInfo;

    // 来源系统
    private String sourceSys;

    //ICS流水号
    private  String  cmeSeqNo;
    
    //交易类型
    private String transType;
    
    private String src_water_num;
    
    
    public String getSrc_water_num() {
		return src_water_num;
	}

	public void setSrc_water_num(String srcWaterNum) {
		src_water_num = srcWaterNum;
	}

	public String getTransRecodeNo() {
        return transRecodeNo;
    }

    public void setTransRecodeNo(String transRecodeNo) {
        this.transRecodeNo = transRecodeNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getDbCur() {
        return dbCur;
    }

    public void setDbCur(String dbCur) {
        this.dbCur = dbCur;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    public Date getRespDate() {
        return respDate;
    }

    public void setRespDate(Date respDate) {
        this.respDate = respDate;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespInfo() {
        return respInfo;
    }

    public void setRespInfo(String respInfo) {
        this.respInfo = respInfo;
    }

    public String getSourceSys() {
        return sourceSys;
    }

    public void setSourceSys(String sourceSys) {
        this.sourceSys = sourceSys;
    }

    public String getCmeSeqNo() {
        return cmeSeqNo;
    }

    public void setCmeSeqNo(String cmeSeqNo) {
        this.cmeSeqNo = cmeSeqNo;
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}
    
    
}
