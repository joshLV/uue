package com.zihexin.business_interface.common.socket.connect;

import java.util.Date;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zihexin.business_interface.common.DateUtils;
import com.zihexin.business_interface.common.Utils;
import com.zihexin.business_interface.dao.ReadPropertiesDao;
import com.zihexin.business_interface.domain.Command;
import com.zihexin.business_interface.domain.Message;

/**
 * 与银行的socket服务端链接
 * User: Administrator
 *
 */
public class BankConnectorSupport extends AbcConnectorSupport {
    private final static Logger logger = LoggerFactory.getLogger(BankConnectorSupport.class);
    private static NioSocketConnector connector;
    private final static String BODY_TYPE = "2";//报文类别;1:请求报文，2：响应报文
    
    private ReadPropertiesDao readPropertiesDao;
    
     public BankConnectorSupport(DataAnalyze dataAnalyze) {
        super(dataAnalyze);
    }

    /**
     * 同步短连接，得到处理后的数据，完成会话后关闭连接，
     * @param
     * @return
     */
    /**
     * @param request
     * @param srcWaterNum
     * @return
     */
    public  String requestProcessor(byte [] request,String srcWaterNum){
    	
         Date requestDate = new Date();//请求时间
         Command _command = new Command();
         Message reciveData = new Message();
        IoSession session  = null;
        byte[]  xmlResponse = null;
        String xml = null;
        synchronized (this) {
            if(connector == null){
                connector = (NioSocketConnector) runInit();
            }
        }

       try {
           ConnectFuture connectFuture = connector.connect();
           connectFuture.awaitUninterruptibly();
           session = connectFuture.getSession();
           session.getConfig().setUseReadOperation(true);
           //session.getConfig().setReadBufferSize(4096) ;
           session.getFilterChain().remove("protocolCodeFilter") ;
           session.getFilterChain().addLast("protocolCodeFilter",  new ProtocolCodecFilter(null, new SpdbDecode())) ;
           logger.info("请求到银行的时间为："+DateUtils.now());
           session.write(request).awaitUninterruptibly();
           ReadFuture readFuture = session.read();
           readFuture.awaitUninterruptibly();
           
           xmlResponse = (byte[]) readFuture.getMessage();
           if(xmlResponse != null){
               xml = new String(xmlResponse, Utils.CHARSET_FORNAME);
           }
	        logger.info("银行响应的时间为："+DateUtils.now());
           return xml;
      } catch (Exception e){
    	  e.printStackTrace();
          logger.error("返回银行数据异常！");
          e.printStackTrace();
      }finally{
          if(session != null)  session.close(true);
           //if(connector != null) connector.dispose();
          
          	//记录交易流水银行响应报文日志
	        logger.info("开始生成交易流水银行响应报文日志");
	        reciveData.setMsg(xml);
	        _command.setMessage(reciveData);
	        _command.setRequestDt(requestDate);
	        _command.setBodyType(BODY_TYPE);
	        _command.setCommandSrc("sys009");
	        _command.setSrc_water_num(srcWaterNum);
	        boolean logSuccess = readPropertiesDao.saveTransLog(_command);
	        logger.info(reciveData.getLogId() + "流程日志：写入数据库" + (logSuccess == true ? "成功" : "失败"));
	        logger.info(reciveData.getLogId() + "结束生成交易流水发送报文日志");
      }
      return "";
    }

	public ReadPropertiesDao getReadPropertiesDao() {
		return readPropertiesDao;
	}

	public void setReadPropertiesDao(ReadPropertiesDao readPropertiesDao) {
		this.readPropertiesDao = readPropertiesDao;
	}
    
    
}
