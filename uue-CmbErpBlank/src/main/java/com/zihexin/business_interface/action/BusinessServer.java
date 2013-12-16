package com.zihexin.business_interface.action;



import com.zihexin.business_interface.common.KeyCheck;
import com.zihexin.business_interface.common.Loader;
import com.zihexin.business_interface.dao.ReadPropertiesDao;
import com.zihexin.business_interface.domain.Command;
import com.zihexin.business_interface.domain.Field;
import com.zihexin.business_interface.domain.Message;
import com.zihexin.business_interface.domain.SendResult;
import com.zihexin.business_interface.exception.ErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ��BusinessServer.java��ʵ�����������Ľ���ҵ�����
 *
 */
public class BusinessServer {
    private final static Logger logger = LoggerFactory.getLogger(BusinessServer.class);
    private final static boolean RECORD_LOG = true;
    private final static boolean UN_RECORD_LOG = false;
    private final static String COMMAND_SRC = "DB";//DB:���ݿ� CACHE:����
    
    private final static String BODY_TYPE = "1";//�������;1:�����ģ�2����Ӧ����

    /**
     * ���ķ����������
     *
     * @param reciveData ����
     * @return ��ִ
     */
    public SendResult parseData(Message reciveData) {
        boolean isLog = RECORD_LOG;
        ReadPropertiesDao dao = (ReadPropertiesDao) Loader.getContext().getBean("readPropertiesDao");
        Date requestDate = new Date();//����ʱ��
        System.out.println(">>>>>>>"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(requestDate));
        Command _command = new Command();
        SendResult sendResult = new SendResult();
        //reciveData.setMsg("CFRT03|sys001|ABC|0.01|11|220101040022675|01|SalaryAbc.cmes1_0006.2012120503.FFS|0.01|1||����2|rAHaJx8VBco=");
        //reciveData.setMsg("CQLT19|sys001|ABC|010092206454|rAHaJx8VBco=");
        //reciveData.setMsg("CFRT73|sys001|ABC|0.01|62284o80010340919611||01|220101040022675|11|01||������||0|1|000000|�ۿ����|rAHaJx8VBco=");
        
        //����ǩԼ
      //  reciveData.setMsg("ENDIIMPT|sys010|ICBC|123456|102||20131118|113200515|20131118113200515|1|20131118113200515|001001|11|��ҵ����|��ҵ�˺�|||ָ��˳���|�ɷѱ��|�ɷѿͻ�����|�ɷѿͻ��˺�|0|֤������|��������|�������ֶ�3|�������ֶ�4|rAHaJx8VBco=");
        
       
        //����ǩԼ��ѯ
      //  reciveData.setMsg("QENDIIMPT|sys010|ICBC|123456|102||20131118|113200515|20131118113200515|20131118|20131118|20131118113200515||�ύ�����ֶ�1|�ύ�����ֶ�2|rAHaJx8VBco=");
        
        //��������
      //  reciveData.setMsg("PERDIS|sys010|ICBC|123456|102||20131118|113200515|20131118113200515|1|0|62284o80010340919611||01|220101040022675|11|01||������||0|1|000000|�ۿ����|rAHaJx8VBco=");
        //reciveData.setMsg("BANK_TYPE2|sys001|BOC|6222888888888888|12.5|rAHaJx8VBco=");
        
        //reciveData.setMsg("CFRT05|sys001|ABC|0.05|11|220101040022675|01||||cmes1_0006.20121206fybx4.FFS|0.05|1||����4|rAHaJx8VBco=");
        //reciveData.setMsg("CQLT19|sys001|ABC|010092208452|rAHaJx8VBco="); 
        //reciveData.setMsg("CMRA00|sys001|ABC|11|220101040022675|01|100|100|100|100|100|100|100|100|20121231|21001231|rAHaJx8VBco="); 
        
      try {
	       
	        logger.info(reciveData.getLogId() + "���Ĵ���ʼ");
       
            String split = Loader.getItem("SPLIT");
            logger.info(reciveData.getLogId() + "�����ָ���:" + split);
            String[] fields = reciveData.getMsg().split(split);
            
            //��¼������ˮ���ͱ�����־
	        logger.info(reciveData.getLogId() + "��ʼ���ɽ�����ˮ���ͱ�����־");
	        _command.setMessage(reciveData);
	        _command.setRequestDt(requestDate);
	        _command.setBodyType(BODY_TYPE);
	        _command.setCommandSrc(fields[1]);
	        _command.setSrc_water_num(fields[fields.length-2]);
	        boolean logSuccess = dao.saveTransLog(_command);
	        logger.info(reciveData.getLogId() + "������־��д�����ݿ�" + (logSuccess == true ? "�ɹ�" : "ʧ��"));
	        logger.info(reciveData.getLogId() + "�������ɽ�����ˮ���ͱ�����־");
	        

            //��־��
            if (reciveData.getId() == null || "".equals(reciveData.getId())) {
                throw new ErrorException("δ������־��", UN_RECORD_LOG);
            }

            //��У��
            if (fields == null || fields.length <= 1) {
                throw new ErrorException("�ձ��Ļ�ȱ��ָ����", UN_RECORD_LOG);
            }
            //����У��
            if (reciveData.getMsg().length() > Integer.parseInt(Loader.getItem("MES_MAX_LENGTH"))) {
                throw new ErrorException("��������,���ȳ���" + Loader.getItem("MES_MAX_LENGTH"), UN_RECORD_LOG);
            }

            for (int i = 0; i < fields.length; i++) {
                logger.info(reciveData.getLogId() + "�ֶ�" + i + ":" + fields[i]);
            }

            //*********************��ȡ�ӿ�����
            logger.info(reciveData.getLogId() + "..��ʼװ�ؽӿ�����");
            String commandId = fields[0];
            //��ȫ�� ���ߺ�
            //Command _command = Loader.getCommandSet(commandId);
            //�����ݿ�
            _command = dao.getCommandSet(commandId, COMMAND_SRC);
            //ָ�����У��
            if (_command == null) {
                throw new ErrorException("���Ĳ������Ϸ���ָ����Ϣ", UN_RECORD_LOG);
            }
            _command.setRequestDt(requestDate);
            _command.setMessage(reciveData);
            _command.setCommandSrc(fields[1]);
            _command.setCommandCheck(fields[fields.length - 1]);
            _command.setSrc_water_num(fields[fields.length - 2]);
            logger.info(reciveData.getLogId() + "..����װ�ؽӿ�����");


            //*********************���Ļ���У��
            logger.info(reciveData.getLogId() + "��ʼ���Ļ���У��");

            //��ԴϵͳУ��
            if (!dao.getSrc(_command)) {
                throw new ErrorException("Դϵͳ�޴�ָ��Ȩ��", UN_RECORD_LOG);
            }

            //�ֶ���У��
            if (_command.getCommandFieldsCnt() != fields.length) {
                throw new ErrorException("�����ֶ�����ƥ��", UN_RECORD_LOG);
            }
            //KEYУ��:��Դϵͳ��У��λУ��
            if (!KeyCheck.check(_command.getCommandSrc(), _command.getCommandCheck())) {
                throw new ErrorException("δ��Ȩ", RECORD_LOG);
            }
            logger.info(reciveData.getLogId() + "�������Ļ���У��");

            //*********************������������ֵ
            logger.info(reciveData.getLogId() + "��ʼ������������ֵ");
            for (int i = 2; i < fields.length - 1; i++) {
                ((Field) _command.getCommandBusinessParameter().get(i - 2)).setValue(fields[i]);
            }
            logger.info(reciveData.getLogId() + "����������������ֵ");

            //*********************����ҵ���ֶ�У��
            logger.info(reciveData.getLogId() + "��ʼҵ���ֶ�У��");
            CheckTools checkTools = new CheckTools();
            checkTools.businessFieldCheck(_command);
            logger.info(reciveData.getLogId() + "����ҵ���ֶ�У��");

            //*********************��������
            logger.info(reciveData.getLogId() + "��ʼִ������ҵ������");
            Class cls = Class.forName(Loader.getItem("BUSINESS_CLASS_PATH") + "." + _command.getCommandClass());
            Object obj = cls.newInstance();
            Method m = cls.getMethod("excute", new Class[]{Command.class});
            sendResult = (SendResult) m.invoke(obj, _command);
            logger.info(reciveData.getLogId() + "����ִ������ҵ������");


            //*********************д��ִ����
            logger.info(reciveData.getLogId() + "��ʼ���ɻ�ִ����");
            Date responseDate = new Date();//������Ӧʱ��
            _command.setResponseDt(responseDate);
            _command.setFinalStatus(sendResult.getCode());
            logger.info(reciveData.getLogId() + "�������ɻ�ִ����");

        } catch (ErrorException error) {
            //8:ƽ̨�ڲ�У�鷵�ش��� 9:ƽ̨�ڲ�ϵͳ�쳣
            //1:�����Ѵ����سɹ� 0�������Ѵ�����ʧ�� 7��ִ�����з������г����쳣

            logger.info(reciveData.getLogId() + "У���쳣:" + error.getMsg());
            sendResult.setCode("8");
            sendResult.setMsg("Error:" + error.getMsg());
            if (_command == null) {
                _command = new Command();
            }
            _command.setFinalStatus(sendResult.getCode());
            isLog = error.isLog();
            if (isLog) {
                for (int i = 0; i < _command.getCommandBusinessParameter().size(); i++) {
                    ((Field) _command.getCommandBusinessParameter().get(i)).setValue(null);
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            logger.info(reciveData.getLogId() + "�����쳣:" + e.getMessage());
            sendResult.setCode("9");
            sendResult.setMsg("Error:" + e.getMessage());
            if (_command == null) {
                _command = new Command();
            }
            _command.setFinalStatus(sendResult.getCode());
            isLog = UN_RECORD_LOG;
        } finally {
            //��¼��־
           /* logger.info(reciveData.getLogId() + "��־����:" + (isLog == true ? "��" : "��"));
            if (isLog) {
                logger.info(reciveData.getLogId() + "��ʼ������־");
                boolean logSuccess = dao.log(_command);
                logger.info(reciveData.getLogId() + "������־��д�����ݿ�" + (logSuccess == true ? "�ɹ�" : "ʧ��"));
                logger.info(reciveData.getLogId() + "����������־");
            }*/
            return sendResult;
        }
    }

    public static void main(String[] args) {

    }
}
