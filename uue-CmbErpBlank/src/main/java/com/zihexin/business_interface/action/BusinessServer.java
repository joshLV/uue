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
 * 类BusinessServer.java的实现描述：报文解析业务调度
 *
 */
public class BusinessServer {
    private final static Logger logger = LoggerFactory.getLogger(BusinessServer.class);
    private final static boolean RECORD_LOG = true;
    private final static boolean UN_RECORD_LOG = false;
    private final static String COMMAND_SRC = "DB";//DB:数据库 CACHE:缓存
    
    private final static String BODY_TYPE = "1";//报文类别;1:请求报文，2：响应报文

    /**
     * 报文分类解析调度
     *
     * @param reciveData 报文
     * @return 回执
     */
    public SendResult parseData(Message reciveData) {
        boolean isLog = RECORD_LOG;
        ReadPropertiesDao dao = (ReadPropertiesDao) Loader.getContext().getBean("readPropertiesDao");
        Date requestDate = new Date();//请求时间
        System.out.println(">>>>>>>"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(requestDate));
        Command _command = new Command();
        SendResult sendResult = new SendResult();
        //reciveData.setMsg("CFRT03|sys001|ABC|0.01|11|220101040022675|01|SalaryAbc.cmes1_0006.2012120503.FFS|0.01|1||测试2|rAHaJx8VBco=");
        //reciveData.setMsg("CQLT19|sys001|ABC|010092206454|rAHaJx8VBco=");
        //reciveData.setMsg("CFRT73|sys001|ABC|0.01|62284o80010340919611||01|220101040022675|11|01||于新兰||0|1|000000|扣款测试|rAHaJx8VBco=");
        
        //工行签约
      //  reciveData.setMsg("ENDIIMPT|sys010|ICBC|123456|102||20131118|113200515|20131118113200515|1|20131118113200515|001001|11|企业名称|企业账号|||指令顺序号|缴费编号|缴费客户姓名|缴费客户账号|0|证件号码|过期日期|请求备用字段3|请求备用字段4|rAHaJx8VBco=");
        
       
        //工行签约查询
      //  reciveData.setMsg("QENDIIMPT|sys010|ICBC|123456|102||20131118|113200515|20131118113200515|20131118|20131118|20131118113200515||提交备用字段1|提交备用字段2|rAHaJx8VBco=");
        
        //工行批扣
      //  reciveData.setMsg("PERDIS|sys010|ICBC|123456|102||20131118|113200515|20131118113200515|1|0|62284o80010340919611||01|220101040022675|11|01||于新兰||0|1|000000|扣款测试|rAHaJx8VBco=");
        //reciveData.setMsg("BANK_TYPE2|sys001|BOC|6222888888888888|12.5|rAHaJx8VBco=");
        
        //reciveData.setMsg("CFRT05|sys001|ABC|0.05|11|220101040022675|01||||cmes1_0006.20121206fybx4.FFS|0.05|1||测试4|rAHaJx8VBco=");
        //reciveData.setMsg("CQLT19|sys001|ABC|010092208452|rAHaJx8VBco="); 
        //reciveData.setMsg("CMRA00|sys001|ABC|11|220101040022675|01|100|100|100|100|100|100|100|100|20121231|21001231|rAHaJx8VBco="); 
        
      try {
	       
	        logger.info(reciveData.getLogId() + "报文处理开始");
       
            String split = Loader.getItem("SPLIT");
            logger.info(reciveData.getLogId() + "解析分隔符:" + split);
            String[] fields = reciveData.getMsg().split(split);
            
            //记录交易流水发送报文日志
	        logger.info(reciveData.getLogId() + "开始生成交易流水发送报文日志");
	        _command.setMessage(reciveData);
	        _command.setRequestDt(requestDate);
	        _command.setBodyType(BODY_TYPE);
	        _command.setCommandSrc(fields[1]);
	        _command.setSrc_water_num(fields[fields.length-2]);
	        boolean logSuccess = dao.saveTransLog(_command);
	        logger.info(reciveData.getLogId() + "流程日志：写入数据库" + (logSuccess == true ? "成功" : "失败"));
	        logger.info(reciveData.getLogId() + "结束生成交易流水发送报文日志");
	        

            //日志号
            if (reciveData.getId() == null || "".equals(reciveData.getId())) {
                throw new ErrorException("未生成日志号", UN_RECORD_LOG);
            }

            //空校验
            if (fields == null || fields.length <= 1) {
                throw new ErrorException("空报文或缺少指定域", UN_RECORD_LOG);
            }
            //超长校验
            if (reciveData.getMsg().length() > Integer.parseInt(Loader.getItem("MES_MAX_LENGTH"))) {
                throw new ErrorException("超长报文,长度超过" + Loader.getItem("MES_MAX_LENGTH"), UN_RECORD_LOG);
            }

            for (int i = 0; i < fields.length; i++) {
                logger.info(reciveData.getLogId() + "字段" + i + ":" + fields[i]);
            }

            //*********************读取接口配置
            logger.info(reciveData.getLogId() + "..开始装载接口配置");
            String commandId = fields[0];
            //读全局 上线后
            //Command _command = Loader.getCommandSet(commandId);
            //读数据库
            _command = dao.getCommandSet(commandId, COMMAND_SRC);
            //指令存在校验
            if (_command == null) {
                throw new ErrorException("报文不包含合法的指令信息", UN_RECORD_LOG);
            }
            _command.setRequestDt(requestDate);
            _command.setMessage(reciveData);
            _command.setCommandSrc(fields[1]);
            _command.setCommandCheck(fields[fields.length - 1]);
            _command.setSrc_water_num(fields[fields.length - 2]);
            logger.info(reciveData.getLogId() + "..结束装载接口配置");


            //*********************报文基础校验
            logger.info(reciveData.getLogId() + "开始报文基础校验");

            //来源系统校验
            if (!dao.getSrc(_command)) {
                throw new ErrorException("源系统无此指令权限", UN_RECORD_LOG);
            }

            //字段数校验
            if (_command.getCommandFieldsCnt() != fields.length) {
                throw new ErrorException("报文字段数不匹配", UN_RECORD_LOG);
            }
            //KEY校验:来源系统与校验位校验
            if (!KeyCheck.check(_command.getCommandSrc(), _command.getCommandCheck())) {
                throw new ErrorException("未授权", RECORD_LOG);
            }
            logger.info(reciveData.getLogId() + "结束报文基础校验");

            //*********************解析报文数据值
            logger.info(reciveData.getLogId() + "开始解析报文数据值");
            for (int i = 2; i < fields.length - 1; i++) {
                ((Field) _command.getCommandBusinessParameter().get(i - 2)).setValue(fields[i]);
            }
            logger.info(reciveData.getLogId() + "结束解析报文数据值");

            //*********************报文业务字段校验
            logger.info(reciveData.getLogId() + "开始业务字段校验");
            CheckTools checkTools = new CheckTools();
            checkTools.businessFieldCheck(_command);
            logger.info(reciveData.getLogId() + "结束业务字段校验");

            //*********************银行流程
            logger.info(reciveData.getLogId() + "开始执行银行业务流程");
            Class cls = Class.forName(Loader.getItem("BUSINESS_CLASS_PATH") + "." + _command.getCommandClass());
            Object obj = cls.newInstance();
            Method m = cls.getMethod("excute", new Class[]{Command.class});
            sendResult = (SendResult) m.invoke(obj, _command);
            logger.info(reciveData.getLogId() + "结束执行银行业务流程");


            //*********************写回执报文
            logger.info(reciveData.getLogId() + "开始生成回执报文");
            Date responseDate = new Date();//银行响应时间
            _command.setResponseDt(responseDate);
            _command.setFinalStatus(sendResult.getCode());
            logger.info(reciveData.getLogId() + "结束生成回执报文");

        } catch (ErrorException error) {
            //8:平台内部校验返回错误 9:平台内部系统异常
            //1:银行已处理返回成功 0：银行已处理返回失败 7：执行银行方操作中出现异常

            logger.info(reciveData.getLogId() + "校验异常:" + error.getMsg());
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
            logger.info(reciveData.getLogId() + "出现异常:" + e.getMessage());
            sendResult.setCode("9");
            sendResult.setMsg("Error:" + e.getMessage());
            if (_command == null) {
                _command = new Command();
            }
            _command.setFinalStatus(sendResult.getCode());
            isLog = UN_RECORD_LOG;
        } finally {
            //记录日志
           /* logger.info(reciveData.getLogId() + "日志开关:" + (isLog == true ? "开" : "关"));
            if (isLog) {
                logger.info(reciveData.getLogId() + "开始生成日志");
                boolean logSuccess = dao.log(_command);
                logger.info(reciveData.getLogId() + "流程日志：写入数据库" + (logSuccess == true ? "成功" : "失败"));
                logger.info(reciveData.getLogId() + "结束生成日志");
            }*/
            return sendResult;
        }
    }

    public static void main(String[] args) {

    }
}
