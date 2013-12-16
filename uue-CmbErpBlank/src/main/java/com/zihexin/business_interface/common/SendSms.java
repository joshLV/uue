package com.zihexin.business_interface.common;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * 发送短信工具类
 * @author Administrator
 *
 */
public class SendSms {

	private  static  JaxWsDynamicClientFactory jwdcf = JaxWsDynamicClientFactory.newInstance();
	private static	Client client = null;
	private static String URL = "http://10.0.10.157:6666/SMS_HSMService/Services?wsdl";
	public static String sendSms(String phone,String smsText ) {
		String returnReuslt = "";
		try {
			
			//设置webService服务端的地址
			if(client == null){
				client = jwdcf.createClient("URL");
			}
			
			String _sendSms_marketCode = "100000";// 固定
			String _sendSms_marketKey = "c6ddfe7017cc08391940b9daecb90d3a";// 固定
			String _sendSms_optUser = "1390"; // 可以随便填写
			String _sendSms_phone = phone;// 手机号
			String _sendSms_smsTxt = smsText;// 短信内容
			String _sendSms_type = "NET_LC";// 固定
			String sign =_sendSms_type+_sendSms_phone+_sendSms_smsTxt+_sendSms_marketCode+_sendSms_optUser+_sendSms_marketKey;
			Object[] res = new Object[] {_sendSms_type ,_sendSms_phone,_sendSms_smsTxt, _sendSms_marketCode, _sendSms_optUser,Tool.MD5encrypt(sign)};

			Object[] ss = client.invoke("SendSms", res);
			returnReuslt = ss[0].toString();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		return returnReuslt;
	}

}
