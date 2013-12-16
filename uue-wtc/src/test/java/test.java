package test;



import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;


import com.zihexin.wtc.common.WTCTool;

public final class test {

	private test() {
	}

	public static void main(String args[]) throws java.lang.Exception {

		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

		Client client = dcf
				.createClient("http://10.5.13.36:7001/WTC/wtcWebService?wsdl");

		// for(int i=0;i< 1000000;i++){
		String marketCode = "sys005";// 固定
		String marketKey = "123456789";// 固定
		String optUser = "reqWtcService"; // 可以随便填写
		//String data = "CFRT21|sys001|ABC|0.01|11|220101040022675||01||6228480050440972813||01|0||||||0|柳春||工资|测试|rAHaJx8VBco=";
		//单笔代扣
		String data = "CFRT73|sys001|ABC|0.01|6228480010340919611||01|220101040022675|11|01||于新兰||0|1|000000|扣款测试|rAHaJx8VBco=";
		String sign = marketCode + optUser + data + marketKey;

		Object[] res = new Object[] { marketCode , optUser , data ,WTCTool.MD5encrypt(sign)};
		try {
			Object[] ss = client.invoke("reqWtcService", res);
			System.out.println(ss[0].toString() + ">>>>>>>>>>>");

			//System.out.println(WTCTool.MD5encrypt(sign)); 

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// }
		

	}

}
