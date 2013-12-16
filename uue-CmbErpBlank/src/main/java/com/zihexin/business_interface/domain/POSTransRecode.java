package com.zihexin.business_interface.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * POS交易流水表
 * User: Administrator
 *
 */
public class POSTransRecode implements Serializable {

    private static final long serialVersionUID = 2762681365832601172L;

    //POS号
    private String  pos_code;
    
    //pos流水号
    private String  pos_water_num;
    
    //系统ID
    private String  market_id;	
    
    //卡号
    private String  card_no;	
    private String  ic_card;
    private Double  account_id;
    
    //交易金额
    private Double loan_amount;
    
    //充值金额
    private Double  debit_amount;
    
    //流水号
    private String  trace_code;
    
    //类型
    private String  swap_type;
    
    //交易时间
    private String  swap_date;
    
    private Date  log_date;
    private String cancel_flag;
    private String strike_flag;
    private Double now_balance;
    private Double inst_id;
    
    //授权码
    private String  auth_code;
    private String  operator_no;
    private String has_detail;
    
    //返回码
    private String return_code;
    
   //银行编码
    private String bankCode;
    
    //第一张绑定银行编码
    private String  bankType1;
  
    //第一张绑定银行卡号
    private String  BankAccountNumber1;
    
    //第二张绑定银行编码
    private String bankType2;
    
    //第二张绑定银行卡号
    private String  BankAccountNumber2;
    
    //第三张绑定银行编码
    private String  bankType3;
    
   //第三张绑定银行卡号
    private String  BankAccountNumber3;

    //扣费银行标示
    private String feeBankFlag;
    
	public String getPos_code() {
		return pos_code;
	}
	public void setPos_code(String posCode) {
		pos_code = posCode;
	}
	public String getPos_water_num() {
		return pos_water_num;
	}
	public void setPos_water_num(String posWaterNum) {
		pos_water_num = posWaterNum;
	}
	public String getMarket_id() {
		return market_id;
	}
	public void setMarket_id(String marketId) {
		market_id = marketId;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String cardNo) {
		card_no = cardNo;
	}
	public String getIc_card() {
		return ic_card;
	}
	public void setIc_card(String icCard) {
		ic_card = icCard;
	}
	public Double getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Double accountId) {
		account_id = accountId;
	}
	public Double getLoan_amount() {
		return loan_amount;
	}
	public void setLoan_amount(Double loanAmount) {
		loan_amount = loanAmount;
	}
	public Double getDebit_amount() {
		return debit_amount;
	}
	public void setDebit_amount(Double debitAmount) {
		debit_amount = debitAmount;
	}
	public String getTrace_code() {
		return trace_code;
	}
	public void setTrace_code(String traceCode) {
		trace_code = traceCode;
	}
	public String getSwap_type() {
		return swap_type;
	}
	public void setSwap_type(String swapType) {
		swap_type = swapType;
	}
	
	public String getSwap_date() {
		return swap_date;
	}
	public void setSwap_date(String swapDate) {
		swap_date = swapDate;
	}
	public Date getLog_date() {
		return log_date;
	}
	public void setLog_date(Date logDate) {
		log_date = logDate;
	}
	public String getCancel_flag() {
		return cancel_flag;
	}
	public void setCancel_flag(String cancelFlag) {
		cancel_flag = cancelFlag;
	}
	public String getStrike_flag() {
		return strike_flag;
	}
	public void setStrike_flag(String strikeFlag) {
		strike_flag = strikeFlag;
	}
	public Double getNow_balance() {
		return now_balance;
	}
	public void setNow_balance(Double nowBalance) {
		now_balance = nowBalance;
	}
	public Double getInst_id() {
		return inst_id;
	}
	public void setInst_id(Double instId) {
		inst_id = instId;
	}
	public String getAuth_code() {
		return auth_code;
	}
	public void setAuth_code(String authCode) {
		auth_code = authCode;
	}
	public String getOperator_no() {
		return operator_no;
	}
	public void setOperator_no(String operatorNo) {
		operator_no = operatorNo;
	}
	public String getHas_detail() {
		return has_detail;
	}
	public void setHas_detail(String hasDetail) {
		has_detail = hasDetail;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String returnCode) {
		return_code = returnCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankType1() {
		return bankType1;
	}
	public void setBankType1(String bankType1) {
		this.bankType1 = bankType1;
	}

	public String getBankType2() {
		return bankType2;
	}
	public void setBankType2(String bankType2) {
		this.bankType2 = bankType2;
	}
	
	public String getBankType3() {
		return bankType3;
	}
	public void setBankType3(String bankType3) {
		this.bankType3 = bankType3;
	}
	public String getBankAccountNumber1() {
		return BankAccountNumber1;
	}
	public void setBankAccountNumber1(String bankAccountNumber1) {
		BankAccountNumber1 = bankAccountNumber1;
	}
	public String getBankAccountNumber2() {
		return BankAccountNumber2;
	}
	public void setBankAccountNumber2(String bankAccountNumber2) {
		BankAccountNumber2 = bankAccountNumber2;
	}
	public String getBankAccountNumber3() {
		return BankAccountNumber3;
	}
	public void setBankAccountNumber3(String bankAccountNumber3) {
		BankAccountNumber3 = bankAccountNumber3;
	}
	public String getFeeBankFlag() {
		return feeBankFlag;
	}
	public void setFeeBankFlag(String feeBankFlag) {
		this.feeBankFlag = feeBankFlag;
	}
	
	
	
	
	
    
    

}
