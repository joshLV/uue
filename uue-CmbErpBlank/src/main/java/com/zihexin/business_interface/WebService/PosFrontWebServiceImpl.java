package com.zihexin.business_interface.WebService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhx.tools.Tool;
import com.zihexin.business_interface.bank.service.POSFrontBankService;
import com.zihexin.business_interface.dao.ReadPropertiesDao;

public class PosFrontWebServiceImpl implements PosFrontWebService {
	
	private Log log = LogFactory.getLog(getClass());
	
	private POSFrontBankService posFrontBankService;
	
	private ReadPropertiesDao readPropertiesDao;
	
	/**
	 * 处理pos前置请求
	 * 
	 * @param marketCode
	 * @param optUser
	 * @param data
	 * @param marketSign
	 * @return
	 */
	public String posFrontReq(String marketCode, String optUser,String data,
			String marketSign) {

		String lowered = "";
		String queryMarketSign = "";
		StringBuffer inputData = new StringBuffer();
		Map mapData = null;

		log.info("接收的POS前置请求数据：" + data);

		
		try {
			
			Map map = readPropertiesDao.queryMarketCodeMap(marketCode);
			
			queryMarketSign = map.get("MARKET_KEY").toString();
			
			String strMd5 = marketCode + optUser + data + queryMarketSign;

			if (Tool.MD5encrypt(strMd5).equals(marketSign)) {

				// 解析请求的数据
				mapData = getDataReq(data.toString());

				if (mapData.size() == 0) {

					lowered = "解析请求的数据为空";
					log.info(lowered);
					return lowered;
				}

				lowered = posFrontBankService.excute(mapData, data);
				

			} else {
				lowered = "验签失败,MD5错误！";
				log.info(lowered);
			}

		} catch (Exception e) {
			lowered = "posFrontWebService接口类处理pos前置请求异常！";
			log.error(lowered);
			e.printStackTrace();
		}

		return lowered;
	}

	
	/**
	 * 解析数据
	 * 
	 * @param data
	 * @return
	 */
	public Map getDataReq(String data) {
		Map map = new HashMap();
		try {

			// 获取配置参数
			List list = readPropertiesDao.getT_AIMSCommandList();
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					HashMap hashMap = (HashMap) list.get(i);
					int length = Integer.parseInt(hashMap.get("DATALENGTH")
							.toString());
					log.info("解析的数据为：" + hashMap.get("NAME") + ">>>>"
							+ data.substring(0, length));
					map.put(hashMap.get("NAME").toString(), data.substring(0,
							length));
					data = data.substring(length);
				}
			}

		} catch (Exception e) {

			log.error("解析数据异常！");
			e.printStackTrace();
		}

		return map;
	}


	public POSFrontBankService getPosFrontBankService() {
		return posFrontBankService;
	}


	public void setPosFrontBankService(POSFrontBankService posFrontBankService) {
		this.posFrontBankService = posFrontBankService;
	}


	public ReadPropertiesDao getReadPropertiesDao() {
		return readPropertiesDao;
	}


	public void setReadPropertiesDao(ReadPropertiesDao readPropertiesDao) {
		this.readPropertiesDao = readPropertiesDao;
	}

	
	
	

}