package test;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.zihexin.wtc.common.WTCTool;

public class Test3 {

	public static void main(String args[]) throws java.lang.Exception {

		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

		Client client = dcf.createClient("http://10.5.13.177:7001/WTC/wtcWebService?wsdl");

		//交易银行子系统编码
		String marketCode = "sys009";
		//交易银行子系统key
		String marketKey = "123456789";
		//交易银行子系统WebService调用方法
		String methodName = "WebService"; 

		String data = null ;
		

		if(marketCode.equals("sys004")){
			//农行测试
			//单笔代扣
			data = "CFRT73|sys001|ABC|0.01|6228480010340919611||01|220101040022675|11|01||于新兰||0|1|000000|扣款测试|1101001|rAHaJx8VBco=";
						
		}else if(marketCode.equals("sys010")){		
			//工行测试
			//工行签约
			data ="ENDIIMPT|sys010|ICBC|123456|102||20131118|113200515|20131118113200515|1|20131118113200515|001001|11|企业名称|企业账号|||指令顺序号|缴费编号|缴费客户姓名|缴费客户账号|0|证件号码|20181231|请求备用字段3|请求备用字段4|20131118113200515|Fo7nOJbIlU4=";
			
		}else if(marketCode.equals("sys009")){
			//WTC子系统—招商银行ERP直连子系统 ---测试招行——招行ERP——直接代发代扣 
			//data ="AgentRequest|sys009|CMB|123456|102||20131118|113200515|2013111815|1|20131118115|001001|11|企业名称|企业账号|||指令顺序号|缴费编号|缴费客户姓名|缴费客户账号|0|证件号码|20181231|请求备用字段3|请求备用字段4|20131118113200515|mLVLHJY20ig=";
			
			//业务类别：代发工资；交易类型：BYSA=代发工资;00001
			//data="AgentRequest|sys009|CMB|N03010||代发工资|代发工资|BYSA||591902896710201|59|福州|100|1|10|人民币|TEST00022|代发工资测试|6225880230001175|刘五|100|Y|||工资|20131118113200515|mLVLHJY20ig=" ;
			
			//业务类别：代发；交易类型：BYSA=代发工资（已完成调试）
			//                           |业务模式编码||交易代码名称|交易代码|期望日期|转出账号/转入账号|分行代码|分行名称|币种代码|业务参考号|用途|                          |注释|            
			//data="AgentRequest|sys009|CMB|N03020||代发|代发其他|BYBK|20131210|591902896710201|59|福州|100|1|10|人民币|TEST00022|代发工资测试|6225880230001175|刘五|100|Y|||工资|20131118113200515|mLVLHJY20ig=" ;
			
			//业务类别：代扣；交易类型：AYBK=代扣其他，（目前资和信没有代扣业务，且银行给予的两个账户均无代扣业务权限，故暂停此业务联调）
			//data="AgentRequest|sys009|CMB|N03030||代扣|代扣其他|AYBK|20131210|591902896810104|59|福州|100|1|10|人民币|TEST00022|代扣其他测试|6225880230001175|刘五|100|Y|||代扣|20131118113200515|mLVLHJY20ig=" ;
			
			//业务类别：招行ERP——查询交易概要信息（GetAgentInfo），查询代发（待银行反馈问题原因）
			//data="GetAgentInfo|sys009|CMB|N03020|20131209|20131210|A|||20131118113200515|mLVLHJY20ig=" ;
			
			//业务类别：招行ERP——查询交易明细信息（GetAgentDetail）（需要修改T_TRANSRECODE这张表的transtype字段长度为14以上，因为GetAgentDetail到达14位）
			//data="GetAgentDetail|sys009|CMB|0028394677|20131118113200515|mLVLHJY20ig=" ;
			
			//业务类别：招行ERP——支付（Pament）,BUSCODE=N02031(直接支付)，BUSMOD=00001（业务模式编码），业务模式名称为空，YURREF（业务参考号），EPTDAT（期望日）可为空,EPTTIM(期望时间)可为空，
						//DBTACC(付方账号)、C_DBTBBK(付方开户地区),TRSAMT(交易金额),C_CCYNBR(币种名称)，,C_STLCHN(结算方式：普通、快递)不可为空,
						//NUSAGE(用途)不可为空、CRTACC（收方账号）、CRTNAM（收方帐户名）、CRTBNK（收方开户行）、CRTPVC（收方省份）、CRTCTY（收方城市）、CRTSQN（收方编号）、TRSTYP（业务种类：100001=普通汇兑101001=慈善捐款,101002 =其他）
			//data="Payment|sys009|CMB|N02031|00001|TESTZF003|20131211||591902896710201|福州|98.16|人民币|普通|直接支付测试|6225880230001175|刘五|招商银行|重庆市|重庆市|APP060928001255|100001|20131118113200515|mLVLHJY20ig=" ;
			
			//业务类别：招行——查询账户交易信息
			//                      |分行号 |分行名称|账号|起始日期|结束日期|最小金额|最大金额|借贷码
			data="GetTransInfo|sys009|CMB|59|福州|591902896710201|20121026|20121126||||20131118113200515|mLVLHJY20ig=" ;
			
			
			
		}
		
		String sign = marketCode + methodName + data + marketKey;
		Object[] res = new Object[] { marketCode , methodName , data ,WTCTool.MD5encrypt(sign)};
		try {
			Object[] ss = client.invoke("reqWtcService", res);
			System.out.println(ss[0].toString() + ">>>>>>>>>>>");

			//System.out.println(WTCTool.MD5encrypt(sign)); 

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		

	}
}
