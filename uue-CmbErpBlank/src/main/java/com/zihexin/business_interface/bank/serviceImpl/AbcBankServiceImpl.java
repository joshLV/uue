package com.zihexin.business_interface.bank.serviceImpl;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zihexin.business_interface.bank.bankRequest.SocketRequest;
import com.zihexin.business_interface.bank.bankRequest.XmlPacket;
import com.zihexin.business_interface.bank.service.AbcBankService;
import com.zihexin.business_interface.common.DateUtils;
import com.zihexin.business_interface.common.ErrorCode;
import com.zihexin.business_interface.common.Loader;
import com.zihexin.business_interface.common.Utils;
import com.zihexin.business_interface.common.socket.connect.AbcConnectorSupport;
import com.zihexin.business_interface.common.socket.connect.BankConnectorSupport;
import com.zihexin.business_interface.dao.ReadPropertiesDao;
import com.zihexin.business_interface.domain.BankCommand;
import com.zihexin.business_interface.domain.Command;
import com.zihexin.business_interface.domain.Field;
import com.zihexin.business_interface.domain.SendResult;
import com.zihexin.business_interface.domain.TransRecode;


/**
/**
 * 银行执行业务接口类
 * User: Administrator
 *
 */
public class AbcBankServiceImpl implements AbcBankService {
    private final static Logger logger = LoggerFactory.getLogger(AbcBankServiceImpl.class);

    //声明银行连接对象
    private AbcConnectorSupport bankConnectorSupport;

    //声明数据库连接对象
    private ReadPropertiesDao readPropertiesDao;

    //声明交易流水对象
    private TransRecode transRecode = new TransRecode();

    //声明银行指令对象
    private BankCommand bankCommand   = new  BankCommand();
    
    
    private LinkedList bankCommandList;
    private Field field = null;
    private  String  xmlResponse;
    private SocketRequest socketRequest = new SocketRequest();
 
    
    private  Map  fieldMap = new HashMap();
    private String commandId = "";
    private  LinkedHashMap  bankCommandMap = new LinkedHashMap();

    //处理结果
    private  SendResult sendResult = new SendResult();

    public SendResult excute(Command command) {
        byte[]   reqBody = null;
        try{
            commandId = command.getCommandId();
            logger.info("commandId:"+commandId);
            
            for(int i=0;i < command.getCommandBusinessParameter().size(); i++){
            	Field field = (Field)  command.getCommandBusinessParameter().get(i);
            	fieldMap.put(field.getFieldName().toString().trim(),field.getValue().toString().trim());
            }
            
            bankCommandList = Loader.getBankCommandList(commandId);
            if(bankCommandList.size() > 0) {
                for(int i = 0;i < bankCommandList.size();i++ ){
                    bankCommand = (BankCommand) bankCommandList.get(i);
                    if(bankCommand.getCommandCode().toString().equals(commandId) ){
                    	 bankCommandMap.put(bankCommand.getFieldName(), bankCommand.getFieldName() == null ? "" : bankCommand.getFieldName().toString().trim());
                         logger.info("参数值为："+bankCommand.getFieldName());
                    }
                   
                }
            }else{
            	sendResult.setCode(ErrorCode.BODY_ERROE);
	            sendResult.setMsg("初始参数错误!");
	            logger.error(sendResult.getMsg());
	            return sendResult;
            }

            if("AgentRequest".equals(commandId)){
            	
    			// 生成请求报文
    			String data = socketRequest.getRequestStr(commandId,fieldMap,bankCommandMap);
    			System.out.println(data);
    			if(StringUtils.isBlank(data)){
    				  	sendResult.setCode(ErrorCode.BODY_ERROE);
    		            sendResult.setMsg("组装招商ERP请求报文为空");
    		            logger.error(sendResult.getMsg());
    		            return sendResult;
    			}
    			String strLen = String.valueOf(data.getBytes().length) + "        ";
    			reqBody = (strLen.substring(0, 8) + data).getBytes();
    			
                //向银行发送扣款业务报文
                xmlResponse = ((BankConnectorSupport)bankConnectorSupport).requestProcessor(reqBody,command.getSrc_water_num());

                //调用银行返回的数据处理方法
                sendResult = getBanAgentRequestResult(xmlResponse);

            } else if("GetAgentInfo".equals(commandId)){
            	
    			// 生成请求报文
    			String data = socketRequest.getRequestGetAgentInfo(commandId,fieldMap,bankCommandMap);
    			if(StringUtils.isBlank(data)){
				  	sendResult.setCode(ErrorCode.BODY_ERROE);
		            sendResult.setMsg("组装招商ERP请求报文为空");
		            logger.error(sendResult.getMsg());
		            return sendResult;
    			}
    			String strLen = String.valueOf(data.getBytes().length) + "        ";
    			reqBody = (strLen.substring(0, 8) + data).getBytes();
    			
                //向银行发送查询交易概要信息业务报文
                xmlResponse = ((BankConnectorSupport)bankConnectorSupport).requestProcessor(reqBody,command.getSrc_water_num());

                //调用银行返回的数据处理方法
                sendResult = getBankGetAgentInfoResult(xmlResponse);

            } else if("GetAgentDetail".equals(commandId)){
            	
    			// 生成请求报文
    			String data = socketRequest.getRequestGetAgentDetail(commandId,fieldMap,bankCommandMap);
    			if(StringUtils.isBlank(data)){
				  	sendResult.setCode(ErrorCode.BODY_ERROE);
		            sendResult.setMsg("组装招商ERP请求报文为空");
		            logger.error(sendResult.getMsg());
		            return sendResult;
    			}
    			String strLen = String.valueOf(data.getBytes().length) + "        ";
    			reqBody = (strLen.substring(0, 8) + data).getBytes();
    			
                //向银行发送查询交易明细信息业务报文
                xmlResponse = ((BankConnectorSupport)bankConnectorSupport).requestProcessor(reqBody,command.getSrc_water_num());

                //调用银行返回的数据处理方法
                sendResult = getBankGetAgentDetailResult(xmlResponse);

            }else if("Payment".equals(commandId)){
            	
            	//生成支付请求报文
            	String data = socketRequest.getRequestPayment(commandId,fieldMap,bankCommandMap);
    			System.out.println(data);
    			if(StringUtils.isBlank(data)){
    				  	sendResult.setCode(ErrorCode.BODY_ERROE);
    		            sendResult.setMsg("组装招商ERP请求报文为空");
    		            logger.error(sendResult.getMsg());
    		            return sendResult;
    			}
    			String strLen = String.valueOf(data.getBytes().length) + "        ";
    			reqBody = (strLen.substring(0, 8) + data).getBytes();
    			
                //向银行发送扣款业务报文
                xmlResponse = ((BankConnectorSupport)bankConnectorSupport).requestProcessor(reqBody,command.getSrc_water_num());

                //调用银行返回的数据处理方法
                sendResult = getBanAgentRequestResult(xmlResponse);
            	
            }else if("GetTransInfo".equals(commandId)){
            	
            	//生成查询账户交易信息请求报文
            	String data = socketRequest.getRequestGetTransInfo(commandId,fieldMap,bankCommandMap);
    			System.out.println(data);
    			if(StringUtils.isBlank(data)){
    				  	sendResult.setCode(ErrorCode.BODY_ERROE);
    		            sendResult.setMsg("组装招商ERP请求报文为空");
    		            logger.error(sendResult.getMsg());
    		            return sendResult;
    			}
    			String strLen = String.valueOf(data.getBytes().length) + "        ";
    			reqBody = (strLen.substring(0, 8) + data).getBytes();
    			
                //向银行发送扣款业务报文    	
    			
                xmlResponse = ((BankConnectorSupport)bankConnectorSupport).requestProcessor(reqBody,command.getSrc_water_num());

                //调用银行返回的数据处理方法
                sendResult = getBanAgentRequestResult(xmlResponse);
            	
            }
            
            else {
                sendResult.setCode(ErrorCode.SYS_FAILURE);
                sendResult.setMsg("银行交易编码错误！");
                return sendResult;
            }

        } catch (Exception e) {
            sendResult.setCode(ErrorCode.BODY_ERROE);
            sendResult.setMsg("发送到银行的整体报文返回出错");
            logger.error("发送到银行的整体报文返回出错！");
            e.printStackTrace();
        }finally {
	    	
            transRecode.setBankCode(fieldMap.get("bank").toString());

            //请求银行的账号
            transRecode.setAccountNo(String.valueOf(fieldMap.get("ACCNBR")));
            transRecode.setDbCur(Utils.DBCUR);
            transRecode.setSourceSys(command.getCommandSrc());
            
            if(null != fieldMap.get("TRSAMT")){
                transRecode.setAmt( Double.valueOf(fieldMap.get("TRSAMT").toString()));
            }
            transRecode.setReqDate(DateUtils.getRequestDate());
            transRecode.setTransType(commandId);
            transRecode.setSrc_water_num(command.getSrc_water_num());
            
 	       //保存流水
 	       saveTransRecode(transRecode);
 	      
 	    }
        return sendResult;
    }

    
   
    /**
     * 代发代扣 处理银行返回的数据
     * @param xmlResponse
     * @return
     * @throws Exception
     */
    private   SendResult getBanAgentRequestResult(String result) throws Exception{

    try{
        Map map = new HashMap();
        XmlPacket pktRsp = new XmlPacket(); 
        if (StringUtils.isBlank(result) ||  result.length() <= 0) {
			 sendResult.setCode(ErrorCode.BODY_ERROE);
             sendResult.setMsg("银行响应数据为空！");
             logger.error("银行响应数据为空！");
        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
             return   sendResult;
		} else{
			 pktRsp = XmlPacket.valueOf(result);
			if (pktRsp != null) {
				String sRetCod = pktRsp.getRETCOD();
				if (sRetCod.equals("0")) {
					sendResult.setCode(ErrorCode.SYS_SUCCESS);
					 map.put("RespCode","0000");
				} else if (sRetCod.equals("-9")) {
					 map.put("RespCode",sRetCod);
					 sendResult.setCode(ErrorCode.BODY_ERROE);
		             sendResult.setMsg("支付未知异常，请查询支付结果确认支付状态，错误信息："+pktRsp.getERRMSG());
		             logger.error(sendResult.getMsg());
		        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
		             return   sendResult;
				} else {
					 map.put("RespCode",ErrorCode.BODY_ERROE);
					 sendResult.setCode(ErrorCode.BODY_ERROE);
		             sendResult.setMsg("支付失败："+pktRsp.getERRMSG());
		             logger.error(sendResult.getMsg());
		        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
		             return   sendResult;
				}
			} else {
				 map.put("RespCode",ErrorCode.BODY_ERROE);
				 sendResult.setCode(ErrorCode.BODY_ERROE);
	             sendResult.setMsg("响应报文解析失败");
	             logger.error(sendResult.getMsg());
	        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
	             return   sendResult;
			}
		}
        
		map.put("RespInfo",pktRsp.getERRMSG());
		
        logger.info("收到服务端消息:响应码是" + map.get("RespCode"));
        logger.info("收到服务端消息:返回信息是" + map.get("RespInfo"));
       
        StringBuffer stringBuffer = new StringBuffer();
        
        stringBuffer.append(map.get("RespCode"))
        			.append(Utils.SYS_DELIMITER)
        			.append("")
                    .append(Utils.SYS_DELIMITER)
                    .append("")
                    .append(Utils.SYS_DELIMITER)
                    .append(map.get("RespInfo"))
                     .append(Utils.SYS_DELIMITER)
                    .append("")
                     .append(Utils.SYS_DELIMITER)
                    .append("");
        sendResult.setMsg(stringBuffer.toString());
        transRecode.setRespDate(DateUtils.getRequestDate());
        transRecode.setRespCode(map.get("RespCode").toString());
        transRecode.setRespInfo(pktRsp.getERRMSG());
       
	    }catch (Exception e) {
	        e.printStackTrace();
	    } 
        return   sendResult;
    }

    
    /**
     * 查询交易概要信息 处理银行返回的数据
     * @param xmlResponse
     * @return
     * @throws Exception
     */
    private   SendResult getBankGetAgentInfoResult(String result) throws Exception{

    try{
        Map map = new HashMap();
        XmlPacket pktRsp = new XmlPacket(); 
        if (StringUtils.isBlank(result) ||  result.length() <= 0) {
			 sendResult.setCode(ErrorCode.BODY_ERROE);
             sendResult.setMsg("银行响应数据为空！");
             logger.error("银行响应数据为空！");
        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
             return   sendResult;
		} else{
			 pktRsp = XmlPacket.valueOf(result);
			if (pktRsp != null) {
				
				if (pktRsp.isError()) {
					 map.put("RespCode",ErrorCode.BODY_ERROE);
					 sendResult.setCode(ErrorCode.BODY_ERROE);
		             sendResult.setMsg(pktRsp.getERRMSG());
		             logger.error(sendResult.getMsg());
		        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
		             return   sendResult;
				} else {
					sendResult.setCode(ErrorCode.SYS_SUCCESS);
					 map.put("RespCode","0000");
					int size = pktRsp.getSectionSize("NTQATSQYZ");
					System.out.println("查询结果明细数："+size);
					for(int i=0; i<size; i++){
						Map propDtl = pktRsp.getProperty("NTQATSQYZ", i);
						System.out.println("经办日期:" + propDtl.get("OPRDAT") 
								+ " 期望日期:"+ propDtl.get("EPTDAT") 
								+ " 流程实例号:" + propDtl.get("REQNBR")
								+ " 交易类型代码:" + propDtl.get("TRSTYP")
								+ " 转出账号:" + propDtl.get("ACCNBR")
								+ " 交易总金额:" + propDtl.get("TOTAMT"));
					}
				}
			} else {
				 map.put("RespCode",ErrorCode.BODY_ERROE);
				 sendResult.setCode(ErrorCode.BODY_ERROE);
	             sendResult.setMsg("响应报文解析失败");
	             logger.error(sendResult.getMsg());
	        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
	             return   sendResult;
			}
		}
        
		map.put("RespInfo",pktRsp.getERRMSG());
		
        logger.info("收到服务端消息:响应码是" + map.get("RespCode"));
        logger.info("收到服务端消息:返回信息是" + map.get("RespInfo"));
       
        StringBuffer stringBuffer = new StringBuffer();
        
        stringBuffer.append(map.get("RespCode"))
        			.append(Utils.SYS_DELIMITER)
        			.append("")
                    .append(Utils.SYS_DELIMITER)
                    .append("")
                    .append(Utils.SYS_DELIMITER)
                    .append(map.get("RespInfo"))
                     .append(Utils.SYS_DELIMITER)
                    .append("")
                     .append(Utils.SYS_DELIMITER)
                    .append("");
        sendResult.setMsg(stringBuffer.toString());
        transRecode.setRespDate(DateUtils.getRequestDate());
        transRecode.setRespCode(map.get("RespCode").toString());
        transRecode.setRespInfo(pktRsp.getERRMSG());
       
	    }catch (Exception e) {
	        e.printStackTrace();
	    } 
        return   sendResult;
    }

    
    /**
     * 查询交易明细信息 处理银行返回的数据
     * @param xmlResponse
     * @return
     * @throws Exception
     */
    private   SendResult getBankGetAgentDetailResult(String result) throws Exception{

    try{
        Map map = new HashMap();
        XmlPacket pktRsp = new XmlPacket(); 
        if (StringUtils.isBlank(result) ||  result.length() <= 0) {
			 sendResult.setCode(ErrorCode.BODY_ERROE);
             sendResult.setMsg("银行响应数据为空！");
             logger.error("银行响应数据为空！");
        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
             return   sendResult;
		} else{
			 pktRsp = XmlPacket.valueOf(result);
			if (pktRsp != null) {
				if (pktRsp.isError()) {
					 map.put("RespCode",ErrorCode.BODY_ERROE);
					 sendResult.setCode(ErrorCode.BODY_ERROE);
		             sendResult.setMsg(pktRsp.getERRMSG());
		             logger.error(sendResult.getMsg());
		        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
		             return   sendResult;
				} else {
					 sendResult.setCode(ErrorCode.SYS_SUCCESS);
					 map.put("RespCode","0000");
					int size = pktRsp.getSectionSize("NTQATDQYZ");
					System.out.println("查询结果明细数："+size);
					for(int i=0; i<size; i++){
						Map propDtl = pktRsp.getProperty("NTQATDQYZ", i);
						System.out.println("账号:" + propDtl.get("ACCNBR") 
								+ " 户名:" + propDtl.get("CLTNAM")
								+ " 金额:" + propDtl.get("TRSAMT")
								+ " 注释:" + propDtl.get("TRSDSP")
								+ " 状态:" + propDtl.get("STSCOD")
								);
					}
				}
			} else {
				 map.put("RespCode",ErrorCode.BODY_ERROE);
				 sendResult.setCode(ErrorCode.BODY_ERROE);
	             sendResult.setMsg("响应报文解析失败");
	             logger.error(sendResult.getMsg());
	        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
	             return   sendResult;
			}
		}
        
		map.put("RespInfo",pktRsp.getERRMSG());
		
        logger.info("收到服务端消息:响应码是" + map.get("RespCode"));
        logger.info("收到服务端消息:返回信息是" + map.get("RespInfo"));
       
        StringBuffer stringBuffer = new StringBuffer();
        
        stringBuffer.append(map.get("RespCode"))
        			.append(Utils.SYS_DELIMITER)
        			.append("")
                    .append(Utils.SYS_DELIMITER)
                    .append("")
                    .append(Utils.SYS_DELIMITER)
                    .append(map.get("RespInfo"))
                     .append(Utils.SYS_DELIMITER)
                    .append("")
                     .append(Utils.SYS_DELIMITER)
                    .append("");
        sendResult.setMsg(stringBuffer.toString());
        transRecode.setRespDate(DateUtils.getRequestDate());
        transRecode.setRespCode(map.get("RespCode").toString());
        transRecode.setRespInfo(pktRsp.getERRMSG());
       
	    }catch (Exception e) {
	        e.printStackTrace();
	    } 
        return   sendResult;
    }
    
    
    /**
     * 保存交易流水数据
     * @param transRecode
     * @return
     */
    private boolean saveTransRecode(TransRecode transRecode){
        boolean ret = false;
        try{
        	
            //获取当前oracle序列
            String transRecodeSeq = readPropertiesDao.getTransRecodeSeq().trim();

            //流水号序列
            transRecode.setTransRecodeNo(transRecodeSeq);

            //保存信息到数据库
            ret = readPropertiesDao.saveTransRecode(transRecode);

        }catch (Exception e) {
            logger.error("记流水请求流水失败！");
            e.printStackTrace();
        }
        return ret;
    }

    public ReadPropertiesDao getReadPropertiesDao() {
        return readPropertiesDao;
    }

    public void setReadPropertiesDao(ReadPropertiesDao readPropertiesDao) {
        this.readPropertiesDao = readPropertiesDao;
    }
    public AbcConnectorSupport getBankConnectorSupport() {
        return bankConnectorSupport;
    }

    public void setBankConnectorSupport(AbcConnectorSupport bankConnectorSupport) {
        this.bankConnectorSupport = bankConnectorSupport;
    }
}
