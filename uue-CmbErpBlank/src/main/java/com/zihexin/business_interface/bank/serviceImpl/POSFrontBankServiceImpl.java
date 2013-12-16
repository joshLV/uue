package com.zihexin.business_interface.bank.serviceImpl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zihexin.business_interface.action.BusinessServer;
import com.zihexin.business_interface.bank.service.POSFrontBankService;
import com.zihexin.business_interface.common.ErrorCode;
import com.zihexin.business_interface.common.RespCode;
import com.zihexin.business_interface.common.SendSms;
import com.zihexin.business_interface.common.Utils;
import com.zihexin.business_interface.dao.FinanceDao;
import com.zihexin.business_interface.dao.ReadPropertiesDao;
import com.zihexin.business_interface.dao.XshTestBaseDao;
import com.zihexin.business_interface.domain.Command;
import com.zihexin.business_interface.domain.Message;
import com.zihexin.business_interface.domain.POSTransRecode;
import com.zihexin.business_interface.domain.SendResult;


/**
/**
 * 处理POS前置请求的业务接口实现类
 * User: Administrator
 *
 */
public class POSFrontBankServiceImpl implements POSFrontBankService {
    private final static Logger logger = LoggerFactory.getLogger(POSFrontBankServiceImpl.class);

     private final static String BODY_TYPE_REQ = "1";//报文类别;1:请求报文
	
	private final static String BODY_TYPE_RES = "2";//报文类别;2：响应报文
	
	private final static String TRANS_TYPE_CONSUME = "0200000100"; //消费交易
	
	private final static String TRANS_TYPE_RECHARGE  = "0200000700"; //充值交易
	
	private Command _command = new Command();
	private Message message =  new Message();
	
	private	BusinessServer businessServer = new BusinessServer();
	private Message reciveData = new Message();
	
	private ReadPropertiesDao readPropertiesDao;
	
	private XshTestBaseDao xshTestDao;
	
	private FinanceDao financeDao;
	

	private String bankCode = "";
	
	private String cordNo = "";
	
	private String  phone = ""; 
	
	Map map = new HashMap();
	HashMap paramMap = new HashMap();
	
	public String  amount = "";
	
	private String maskCardNo = ""; //银行卡4位尾号
	
    public String excute(Map mapData,String data){
    	
    	SendResult sendResult = null;
    	String strReq = "";
    	String lowered = "";
    	HashMap returnMapCardNo = null;
    	Map bus_inter_paramMap = null;
    	try{
    			map = mapData;
    			
    			//获取系统参数
	    		List paramList = financeDao.getQueryPAramList();
	    		for(int i = 0; i < paramList.size() ; i++){
	    			HashMap map = (HashMap) paramList.get(i);
	    			paramMap.put(map.get("PARAM_KEY"), map.get("PARAM_VALUE"));
	    		}
	    		
	    		
	    		HashMap returnMap = financeDao.getAcctStatus("02",mapData.get("card_no").toString().trim());
	    	
	    		if(returnMap != null && "06".equals(returnMap.get("ACCT_STATUS").toString())){
		 		
			 		HashMap returnMapAcctId = financeDao.queryNtacctMediumByAcctId("01",returnMap.get("ACCT_ID").toString());
			 		
			 		if(returnMapAcctId != null && !"".equals(returnMap.get("MEDIUM_ID").toString().trim())){

			 		   returnMapCardNo = financeDao.queryCardNoByMedId(returnMapAcctId.get("MEDIUM_ID").toString().trim());
			 		   
			 		}else{
			 			
			 			lowered = "获取账户ID错误";
						logger.info(lowered);
						return lowered;
			 		}
			 		
			 		if(TRANS_TYPE_RECHARGE.equals(map.get("msg_type").toString()+map.get("process_cod").toString())){
			 			
			 			//bus_inter_paramMap = readPropertiesDao.queryMarketCodeMap(TRANS_TYPE_RECHARGE);
			 			
			 			//执行充值请求报文
						strReq = getProcAndFunReq(bus_inter_paramMap,data);
			 			
			 		}else if(TRANS_TYPE_CONSUME.equals(map.get("msg_type").toString()+map.get("process_cod").toString())){
			 			
						//处理请求报文
						strReq = getReq(returnMapCardNo,data);
						
						reciveData.setMsg(strReq);
						
						//执行代扣业务
						sendResult = businessServer.parseData(reciveData);
						
						lowered = getRespBody(sendResult);
						
			 		}else{
			 			lowered = "暂不支持该pos交易";
						logger.info(lowered);
						return lowered;
			 		}
			 		
					if("".equals(strReq)){
						lowered = "处理请求报文错误";
						logger.info(lowered);
						return lowered;
					}
		 	}else{
		 		
		 		lowered = "账户状态不为激活状态";
		 		logger.info(lowered);
		 		return lowered;
	
		 	}
	 	} catch (Exception e) {
	 		lowered = "处理请求报文异常";
			logger.error(lowered);
			e.printStackTrace();
		}
	 	
	 	return lowered;
    }
    
   
	
    /**
	 * 处理充值请求报文
	 * 
	 * @return
	 */
	public String getProcAndFunReq(Map bus_inter_paramMap,String data) {
		
		Date requestDate = new Date();//请求时间
		String collection_acct_no; //收款账号
		StringBuffer stringBuffer =  new StringBuffer();
		try {
			
            //记录POS交易流水发送报文日志
	        logger.info("开始生成POS交易流水发送报文日志");
	        message.setMsg(data);
	        _command.setMessage(message);
	        _command.setRequestDt(requestDate);
	        _command.setBodyType(BODY_TYPE_REQ);
	        _command.setCommandSrc("sys002");
	        boolean logSuccess = readPropertiesDao.savePOSTransLog(_command);
	        
	        logger.info("结束生成POS交易流水发送报文日志");
	        amount = map.get("amount").toString();
	        //bankCode = returnMapCardNo.get("BANK_EN_CODE").toString();
	        //cordNo = returnMapCardNo.get("CARD_NO").toString();
	        //phone = returnMapCardNo.get("ACCT_MOBILE_NO").toString();
	        //String jdbc = bus_inter_paramMap.get("JDBC").toString();
	        //String input_param = bus_inter_paramMap.get("INPUT_PARAM").toString();
	        //collection_acct_no = paramMap.get("ABC_CFRT73_AcctNo").toString();
	        
	        if("".equals(amount)){
	        	
		 		stringBuffer.append("处理请求报文参数为空！");
		 		logger.error(stringBuffer.toString()); 
		 		
		 		return stringBuffer.toString();
		 	}
	        
	        HashMap<String,String> rechargeParam = new HashMap<String,String>();
	        
	        /* rechargeParam.put("v_opt_type", "00");
	        rechargeParam.put("v_recharge_card_no", v_recharge_card_no);
	        rechargeParam.put("v_recharge_account", "1000000149");
	        rechargeParam.put("v_be_recharged_card_no", v_be_recharged_card_no);
	        rechargeParam.put("v_be_recharged_account", "1000000149");
	        rechargeParam.put("v_charge_amount", amount);
	        rechargeParam.put("v_pos_code", v_pos_code);
	        rechargeParam.put("v_pos_water_num", v_pos_water_num);
	        rechargeParam.put("v_swap_type", v_swap_type);
	        rechargeParam.put("v_unit_no", v_unit_no);*/
	        
			//调用充值函数
	        String returnResult = xshTestDao.exeRechargeFun(rechargeParam);
	        String[] rtnR = returnResult.split(":");
	        
	        if(!"000".equals(rtnR[0])){
	        	stringBuffer.append(returnResult);
	        }
			logger.info("充值完成："+returnResult.toString());
		} catch (Exception e) {
			
			logger.error("交易子系统充值异常");
			e.printStackTrace();
		}

        return stringBuffer.toString();
	}
	
	

    /**
	 * 处理消费请求报文
	 * 
	 * @return
	 */
	public String getReq(HashMap returnMapCardNo,String data) {
		
		Date requestDate = new Date();//请求时间
		String collection_acct_no; //收款账号
		StringBuffer stringBuffer =  new StringBuffer();
		try {
			
            //记录POS交易流水发送报文日志
	        logger.info("开始生成POS交易流水发送报文日志");
	        message.setMsg(data);
	        _command.setMessage(message);
	        _command.setRequestDt(requestDate);
	        _command.setBodyType(BODY_TYPE_REQ);
	        _command.setCommandSrc("sys002");
	        boolean logSuccess = readPropertiesDao.savePOSTransLog(_command);
	        
	        logger.info("结束生成POS交易流水发送报文日志");
	        amount = map.get("amount").toString();
	        bankCode = returnMapCardNo.get("BANK_EN_CODE").toString();
	        cordNo = returnMapCardNo.get("CARD_NO").toString();
	        phone = returnMapCardNo.get("ACCT_MOBILE_NO").toString();
	        maskCardNo = returnMapCardNo.get("MASK_CARD_NO").toString();
	        
	        String acct_true_name = returnMapCardNo.get("ACCT_TRUE_NAME").toString();
	        collection_acct_no = paramMap.get("ABC_CFRT73_AcctNo").toString();
	        
	        if("".equals(amount) || "".equals(bankCode) || "".equals(cordNo) || "".equals("phone") || "".equals(collection_acct_no) || "".equals(acct_true_name)){
	        	
		 		stringBuffer.append("处理请求报文参数为空！");
		 		logger.error(stringBuffer.toString()); 
		 		
		 		return stringBuffer.toString();
		 	}
		 	
		 	
	 		//处理银行————— 单笔代扣交易报文 CFRT73|sys001|ABC|0.01|6228480010340919611||01|220101040022675|11|01||于新兰||0|1|000000|扣款测试|rAHaJx8VBco=
			 
			  stringBuffer.append("CFRT73")
						.append(Utils.SYS_DELIMITER)
						.append("sys002")
						.append(Utils.SYS_DELIMITER)
						.append(bankCode)
						.append(Utils.SYS_DELIMITER)
						.append(Double.parseDouble(amount)/100) //扣款金额
						.append(Utils.SYS_DELIMITER)
						.append(cordNo)	//扣款卡号	
						.append(Utils.SYS_DELIMITER)
						.append(Utils.SYS_DELIMITER)
						.append("01")//币种
						.append(Utils.SYS_DELIMITER)
						.append(collection_acct_no) //收款卡号220101040022675
						.append(Utils.SYS_DELIMITER)
						.append("11|01||"+acct_true_name+"||0|1|000000|扣款测试|"+ map.get("systrace").toString()+"|yKlCPSkWU+o=");
			  
			 
			logger.info("解析完整的以竖线分割的报文："+stringBuffer.toString());
			return stringBuffer.toString();
		} catch (Exception e) {
			
			logger.error("处理请求报文异常");
			e.printStackTrace();
		}

        return "";
	}
	
	
	/**
	 * 处理消费响应报文
	 * @return
	 */
	public String getRespBody(SendResult sendResult){
		
		//返回编码
		String code = "";
		
		//授权码
		String auth = "";
		StringBuffer stringBuffer =  new StringBuffer();
		
		//请求时间
		Date requestDate = new Date();
		POSTransRecode posTransRecode = new POSTransRecode();
		
		try{
						
				String respCode = RespCode.ABCRespCode.getAbcRespCode();
				stringBuffer.append(map.get("tpdu"));
				stringBuffer.append(Utils.updateStr(map.get("msg_type").toString()));
				stringBuffer.append(map.get("process_cod"));
				stringBuffer.append(map.get("card_no"));
				stringBuffer.append(map.get("passwd"));
				stringBuffer.append(map.get("gwc_card_no"));
				stringBuffer.append(map.get("amount"));
				stringBuffer.append(map.get("systrace"));
				stringBuffer.append(map.get("date"));
				stringBuffer.append(map.get("time"));
				stringBuffer.append(map.get("mode"));
				stringBuffer.append(map.get("pos"));
				stringBuffer.append(map.get("merchant"));
					
				//ref
				stringBuffer.append(map.get("ref"));
				String[] respResult = sendResult.getMsg().split("\\|");
				if(ErrorCode.SYS_SUCCESS.equals(sendResult.getCode())){
				
					 //设置响应编码
			        if(respCode.equals(respResult[0])){
			        	code = "00";
			        	stringBuffer.append(code);
			        	
			        	String  smsText = paramMap.get("ABC_CFRT73_smsText").toString();
						smsText = smsText.replaceAll("maskCardNo", maskCardNo);
						smsText = smsText.replaceAll("amount", amount);
						
			        	//发送短信
			        	SendSms.sendSms(phone,smsText);
			        }else{
			        	code = String.valueOf(map.get("code"));
			        	stringBuffer.append(code);
			        }
		        
			        //授权码
			        auth = "000000";
					stringBuffer.append(auth);
					
				}else{
					stringBuffer.append("01"); //扣款失败
					stringBuffer.append("      ");
				}
			
	        logger.info("开始生成POS交易流水发送报文日志");
	         message.setMsg(stringBuffer.toString());
	        _command.setMessage(message);
	        _command.setRequestDt(requestDate);
	        _command.setBodyType(BODY_TYPE_RES);
	        _command.setCommandSrc("sys001");
	        boolean logSuccess = readPropertiesDao.savePOSTransLog(_command);
	        logger.info("结束生成POS交易流水发送报文日志");
	        
	        
	        logger.info("开始生成POS交易流水信息");
	        posTransRecode.setPos_code(String.valueOf(map.get("pos")));
	        posTransRecode.setPos_water_num(String.valueOf(map.get("systrace")));
	        posTransRecode.setMarket_id("sys002");
	        posTransRecode.setCard_no(String.valueOf(map.get("card_no")).trim());
	        posTransRecode.setLoan_amount(Double.parseDouble(map.get("amount").toString())/100);
	        posTransRecode.setTrace_code(String.valueOf(map.get("ref")));
	        posTransRecode.setSwap_type(String.valueOf(map.get("msg_type")));
	        posTransRecode.setSwap_date(String.valueOf(map.get("date"))+String.valueOf(map.get("time")));
	        posTransRecode.setAuth_code(auth);
	        posTransRecode.setReturn_code(code);
	        posTransRecode.setLog_date(requestDate);
	        
	        //银行编码
	        posTransRecode.setBankCode(bankCode);
	        
	        //银行卡号
	        posTransRecode.setBankAccountNumber1(cordNo);
	        
	        boolean posTransRecodeSuccess = readPropertiesDao.savePosTransRecode(posTransRecode);
	        
	        logger.info("结束POS交易流水信息");
		}catch(Exception e){
			
			logger.error("处理响应报文异常！");
			e.printStackTrace();
		}
		logger.info("响应的POS报文："+stringBuffer.toString());
		return  stringBuffer.toString();
	}
	

	public FinanceDao getFinanceDao() {
		return financeDao;
	}



	public void setFinanceDao(FinanceDao financeDao) {
		this.financeDao = financeDao;
	}



	public ReadPropertiesDao getReadPropertiesDao() {
		return readPropertiesDao;
	}



	public void setReadPropertiesDao(ReadPropertiesDao readPropertiesDao) {
		this.readPropertiesDao = readPropertiesDao;
	}



	public XshTestBaseDao getXshTestDao() {
		return xshTestDao;
	}



	public void setXshTestDao(XshTestBaseDao xshTestDao) {
		this.xshTestDao = xshTestDao;
	}



	
	
	
}
