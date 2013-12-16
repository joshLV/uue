package  com.zihexin.business_interface.WebService;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhx.tools.Tool;
import com.zihexin.business_interface.action.BusinessServer;
import com.zihexin.business_interface.dao.ReadPropertiesDao;
import com.zihexin.business_interface.domain.Message;
import com.zihexin.business_interface.domain.SendResult;


public class BankWebServiceImpl implements BankWebService
{
	private  Log log = LogFactory.getLog(getClass());
    
	private	BusinessServer businessServer = new BusinessServer();
	
	private Message reciveData = new Message();
	
	private SendResult sendResult = null;
	
	private ReadPropertiesDao readPropertiesDao;
	
	
	/**
	 * 发布银行接口服务
	 * @param marketCode
	 * @param optUser
	 * @param data
	 * @param marketSign
	 * @return
	 */
	public  String  WebService(String marketCode, String optUser,  String data, String marketSign) {
			
			String respInfo = "";
			String queryMarketSign = "";
			Map mapData = null;
			
		    log.info("接收的银行业务请求数据：" + data);
		    
		    try{
		    	
		    	Map map = readPropertiesDao.queryMarketCodeMap(marketCode);
				
				queryMarketSign = map.get("MARKET_KEY").toString();
				
				String strMd5 = marketCode+optUser+data+queryMarketSign;
				
				if(Tool.MD5encrypt(strMd5).equals(marketSign)){
					
			    	reciveData.setMsg(data);
			    	
					//执行代扣业务
					sendResult = businessServer.parseData(reciveData);
					respInfo = sendResult.getMsg();
					 
					if(StringUtils.isBlank(respInfo)){
						
						respInfo = "执行银行业务返回的数据为空";
						log.info(respInfo);
					}
				  
				}else{
					respInfo = "验签失败,MD5错误！";
					log.info(respInfo);
				 }
			    
				
		    }catch (Exception e) {
		    	respInfo = "BankWebService接口类处理业务请求异常！";
				log.error(respInfo);
				e.printStackTrace();
			}
			 return respInfo;
	}
	

	public ReadPropertiesDao getReadPropertiesDao() {
		return readPropertiesDao;
	}


	public void setReadPropertiesDao(ReadPropertiesDao readPropertiesDao) {
		this.readPropertiesDao = readPropertiesDao;
	}

	
	
}