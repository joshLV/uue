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
 * ����ִ��ҵ��ӿ���
 * User: Administrator
 *
 */
public class AbcBankServiceImpl implements AbcBankService {
    private final static Logger logger = LoggerFactory.getLogger(AbcBankServiceImpl.class);

    //�����������Ӷ���
    private AbcConnectorSupport bankConnectorSupport;

    //�������ݿ����Ӷ���
    private ReadPropertiesDao readPropertiesDao;

    //����������ˮ����
    private TransRecode transRecode = new TransRecode();

    //��������ָ�����
    private BankCommand bankCommand   = new  BankCommand();
    
    
    private LinkedList bankCommandList;
    private Field field = null;
    private  String  xmlResponse;
    private SocketRequest socketRequest = new SocketRequest();
 
    
    private  Map  fieldMap = new HashMap();
    private String commandId = "";
    private  LinkedHashMap  bankCommandMap = new LinkedHashMap();

    //������
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
                         logger.info("����ֵΪ��"+bankCommand.getFieldName());
                    }
                   
                }
            }else{
            	sendResult.setCode(ErrorCode.BODY_ERROE);
	            sendResult.setMsg("��ʼ��������!");
	            logger.error(sendResult.getMsg());
	            return sendResult;
            }

            if("AgentRequest".equals(commandId)){
            	
    			// ����������
    			String data = socketRequest.getRequestStr(commandId,fieldMap,bankCommandMap);
    			System.out.println(data);
    			if(StringUtils.isBlank(data)){
    				  	sendResult.setCode(ErrorCode.BODY_ERROE);
    		            sendResult.setMsg("��װ����ERP������Ϊ��");
    		            logger.error(sendResult.getMsg());
    		            return sendResult;
    			}
    			String strLen = String.valueOf(data.getBytes().length) + "        ";
    			reqBody = (strLen.substring(0, 8) + data).getBytes();
    			
                //�����з��Ϳۿ�ҵ����
                xmlResponse = ((BankConnectorSupport)bankConnectorSupport).requestProcessor(reqBody,command.getSrc_water_num());

                //�������з��ص����ݴ�����
                sendResult = getBanAgentRequestResult(xmlResponse);

            } else if("GetAgentInfo".equals(commandId)){
            	
    			// ����������
    			String data = socketRequest.getRequestGetAgentInfo(commandId,fieldMap,bankCommandMap);
    			if(StringUtils.isBlank(data)){
				  	sendResult.setCode(ErrorCode.BODY_ERROE);
		            sendResult.setMsg("��װ����ERP������Ϊ��");
		            logger.error(sendResult.getMsg());
		            return sendResult;
    			}
    			String strLen = String.valueOf(data.getBytes().length) + "        ";
    			reqBody = (strLen.substring(0, 8) + data).getBytes();
    			
                //�����з��Ͳ�ѯ���׸�Ҫ��Ϣҵ����
                xmlResponse = ((BankConnectorSupport)bankConnectorSupport).requestProcessor(reqBody,command.getSrc_water_num());

                //�������з��ص����ݴ�����
                sendResult = getBankGetAgentInfoResult(xmlResponse);

            } else if("GetAgentDetail".equals(commandId)){
            	
    			// ����������
    			String data = socketRequest.getRequestGetAgentDetail(commandId,fieldMap,bankCommandMap);
    			if(StringUtils.isBlank(data)){
				  	sendResult.setCode(ErrorCode.BODY_ERROE);
		            sendResult.setMsg("��װ����ERP������Ϊ��");
		            logger.error(sendResult.getMsg());
		            return sendResult;
    			}
    			String strLen = String.valueOf(data.getBytes().length) + "        ";
    			reqBody = (strLen.substring(0, 8) + data).getBytes();
    			
                //�����з��Ͳ�ѯ������ϸ��Ϣҵ����
                xmlResponse = ((BankConnectorSupport)bankConnectorSupport).requestProcessor(reqBody,command.getSrc_water_num());

                //�������з��ص����ݴ�����
                sendResult = getBankGetAgentDetailResult(xmlResponse);

            }else if("Payment".equals(commandId)){
            	
            	//����֧��������
            	String data = socketRequest.getRequestPayment(commandId,fieldMap,bankCommandMap);
    			System.out.println(data);
    			if(StringUtils.isBlank(data)){
    				  	sendResult.setCode(ErrorCode.BODY_ERROE);
    		            sendResult.setMsg("��װ����ERP������Ϊ��");
    		            logger.error(sendResult.getMsg());
    		            return sendResult;
    			}
    			String strLen = String.valueOf(data.getBytes().length) + "        ";
    			reqBody = (strLen.substring(0, 8) + data).getBytes();
    			
                //�����з��Ϳۿ�ҵ����
                xmlResponse = ((BankConnectorSupport)bankConnectorSupport).requestProcessor(reqBody,command.getSrc_water_num());

                //�������з��ص����ݴ�����
                sendResult = getBanAgentRequestResult(xmlResponse);
            	
            }else if("GetTransInfo".equals(commandId)){
            	
            	//���ɲ�ѯ�˻�������Ϣ������
            	String data = socketRequest.getRequestGetTransInfo(commandId,fieldMap,bankCommandMap);
    			System.out.println(data);
    			if(StringUtils.isBlank(data)){
    				  	sendResult.setCode(ErrorCode.BODY_ERROE);
    		            sendResult.setMsg("��װ����ERP������Ϊ��");
    		            logger.error(sendResult.getMsg());
    		            return sendResult;
    			}
    			String strLen = String.valueOf(data.getBytes().length) + "        ";
    			reqBody = (strLen.substring(0, 8) + data).getBytes();
    			
                //�����з��Ϳۿ�ҵ����    	
    			
                xmlResponse = ((BankConnectorSupport)bankConnectorSupport).requestProcessor(reqBody,command.getSrc_water_num());

                //�������з��ص����ݴ�����
                sendResult = getBanAgentRequestResult(xmlResponse);
            	
            }
            
            else {
                sendResult.setCode(ErrorCode.SYS_FAILURE);
                sendResult.setMsg("���н��ױ������");
                return sendResult;
            }

        } catch (Exception e) {
            sendResult.setCode(ErrorCode.BODY_ERROE);
            sendResult.setMsg("���͵����е����屨�ķ��س���");
            logger.error("���͵����е����屨�ķ��س���");
            e.printStackTrace();
        }finally {
	    	
            transRecode.setBankCode(fieldMap.get("bank").toString());

            //�������е��˺�
            transRecode.setAccountNo(String.valueOf(fieldMap.get("ACCNBR")));
            transRecode.setDbCur(Utils.DBCUR);
            transRecode.setSourceSys(command.getCommandSrc());
            
            if(null != fieldMap.get("TRSAMT")){
                transRecode.setAmt( Double.valueOf(fieldMap.get("TRSAMT").toString()));
            }
            transRecode.setReqDate(DateUtils.getRequestDate());
            transRecode.setTransType(commandId);
            transRecode.setSrc_water_num(command.getSrc_water_num());
            
 	       //������ˮ
 	       saveTransRecode(transRecode);
 	      
 	    }
        return sendResult;
    }

    
   
    /**
     * �������� �������з��ص�����
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
             sendResult.setMsg("������Ӧ����Ϊ�գ�");
             logger.error("������Ӧ����Ϊ�գ�");
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
		             sendResult.setMsg("֧��δ֪�쳣�����ѯ֧�����ȷ��֧��״̬��������Ϣ��"+pktRsp.getERRMSG());
		             logger.error(sendResult.getMsg());
		        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
		             return   sendResult;
				} else {
					 map.put("RespCode",ErrorCode.BODY_ERROE);
					 sendResult.setCode(ErrorCode.BODY_ERROE);
		             sendResult.setMsg("֧��ʧ�ܣ�"+pktRsp.getERRMSG());
		             logger.error(sendResult.getMsg());
		        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
		             return   sendResult;
				}
			} else {
				 map.put("RespCode",ErrorCode.BODY_ERROE);
				 sendResult.setCode(ErrorCode.BODY_ERROE);
	             sendResult.setMsg("��Ӧ���Ľ���ʧ��");
	             logger.error(sendResult.getMsg());
	        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
	             return   sendResult;
			}
		}
        
		map.put("RespInfo",pktRsp.getERRMSG());
		
        logger.info("�յ��������Ϣ:��Ӧ����" + map.get("RespCode"));
        logger.info("�յ��������Ϣ:������Ϣ��" + map.get("RespInfo"));
       
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
     * ��ѯ���׸�Ҫ��Ϣ �������з��ص�����
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
             sendResult.setMsg("������Ӧ����Ϊ�գ�");
             logger.error("������Ӧ����Ϊ�գ�");
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
					System.out.println("��ѯ�����ϸ����"+size);
					for(int i=0; i<size; i++){
						Map propDtl = pktRsp.getProperty("NTQATSQYZ", i);
						System.out.println("��������:" + propDtl.get("OPRDAT") 
								+ " ��������:"+ propDtl.get("EPTDAT") 
								+ " ����ʵ����:" + propDtl.get("REQNBR")
								+ " �������ʹ���:" + propDtl.get("TRSTYP")
								+ " ת���˺�:" + propDtl.get("ACCNBR")
								+ " �����ܽ��:" + propDtl.get("TOTAMT"));
					}
				}
			} else {
				 map.put("RespCode",ErrorCode.BODY_ERROE);
				 sendResult.setCode(ErrorCode.BODY_ERROE);
	             sendResult.setMsg("��Ӧ���Ľ���ʧ��");
	             logger.error(sendResult.getMsg());
	        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
	             return   sendResult;
			}
		}
        
		map.put("RespInfo",pktRsp.getERRMSG());
		
        logger.info("�յ��������Ϣ:��Ӧ����" + map.get("RespCode"));
        logger.info("�յ��������Ϣ:������Ϣ��" + map.get("RespInfo"));
       
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
     * ��ѯ������ϸ��Ϣ �������з��ص�����
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
             sendResult.setMsg("������Ӧ����Ϊ�գ�");
             logger.error("������Ӧ����Ϊ�գ�");
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
					System.out.println("��ѯ�����ϸ����"+size);
					for(int i=0; i<size; i++){
						Map propDtl = pktRsp.getProperty("NTQATDQYZ", i);
						System.out.println("�˺�:" + propDtl.get("ACCNBR") 
								+ " ����:" + propDtl.get("CLTNAM")
								+ " ���:" + propDtl.get("TRSAMT")
								+ " ע��:" + propDtl.get("TRSDSP")
								+ " ״̬:" + propDtl.get("STSCOD")
								);
					}
				}
			} else {
				 map.put("RespCode",ErrorCode.BODY_ERROE);
				 sendResult.setCode(ErrorCode.BODY_ERROE);
	             sendResult.setMsg("��Ӧ���Ľ���ʧ��");
	             logger.error(sendResult.getMsg());
	        	 transRecode.setRespCode(Utils.SYSTEM_TIMEOUT);
	             return   sendResult;
			}
		}
        
		map.put("RespInfo",pktRsp.getERRMSG());
		
        logger.info("�յ��������Ϣ:��Ӧ����" + map.get("RespCode"));
        logger.info("�յ��������Ϣ:������Ϣ��" + map.get("RespInfo"));
       
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
     * ���潻����ˮ����
     * @param transRecode
     * @return
     */
    private boolean saveTransRecode(TransRecode transRecode){
        boolean ret = false;
        try{
        	
            //��ȡ��ǰoracle����
            String transRecodeSeq = readPropertiesDao.getTransRecodeSeq().trim();

            //��ˮ������
            transRecode.setTransRecodeNo(transRecodeSeq);

            //������Ϣ�����ݿ�
            ret = readPropertiesDao.saveTransRecode(transRecode);

        }catch (Exception e) {
            logger.error("����ˮ������ˮʧ�ܣ�");
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
