package com.zihexin.business_interface.common;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * 工具类
 * User: Administrator

 */
public class Utils {
    public  static  void main(String args[]){
        //System.out.println(Utils.strLength(2300));
    }


    /**
     * JAVA从指定位置开始截取指定长度的字符串
     *
     * @param input 输入字符串
     * @param index 截取位置，左侧第一个字符索引值是1
     * @param count 截取长度
     * @return 截取字符串
     */
    public static String middle(String input, int index, int count) {

        int[] aa={1,10,4,6};
        for(int i=0;i< input.length(); i++){

            //input = input.substring(input.length() - aa[i]);
            //input.substring(aa[i] - 1, index + count - 1);
            for(int j=0;j<i;j++){
                index = aa[i];

                System.out.println(input.substring(index-1,  aa[j+1]+1));
               // input = input.substring(index-1,  aa[j+1]- 1);
            }
            //count = (count > input.length() - index + 1) ? input.length() - index + 1 : count;


        }

        //count = (count > input.length() - index + 1) ? input.length() - index + 1 :
          //      count;
    return "";
        //return input.substring(index - 1, index + count - 1);
    }

    public static  String SYSTEM_TIMEOUT = "0001";
    
    private static HashMap hexmap = new HashMap();
    /**
     * 系统编码来源
     */
    public static final String SOURCESYSCODE = "sys001";

    /**
     * 字符编码
     */
    public static final  String CHARSET_FORNAME  = "gb2312";

    /**
     * 币种 如：1:人民币,2:英镑,3:美元
     */
    public static final String  DBCUR = "1";



    /**
     * 银行交易代码
     */
    public static final String command_ENDIIMPT = "ENDIIMPT";

    /**
     * 银行—— 报文头为1个字节的是否为加密包标志，加上6个字节的字符表示数据包的长度，如果长度不足6位则右边用空格补足，比如：“1234  ”。
     */
    public static final String ABCBANK_BODYMARK = "0";



    /**
     * 返回内部系统的报文分割标志
     */
    public static final String SYS_DELIMITER = "|";


    /**
     * 签约银行模板路径
     */

    public static  final String ENDIIMPT  = "/com/zihexin/business_interface/resources/icbc/ENDIIMPTRequest.xml";

    /**
     * 扣款银行模板路径
     */
    public static  final String PERDIS  = "/com/zihexin/business_interface/resources/icbc/PERDISRequest.xml";
    
    /**
     * 扣款查询银行模板路径
     */
    public static  final String QPERDIS  = "/com/zihexin/business_interface/resources/icbc/QPERDISRequest.xml";
    
    /**
     * 签约银行模板路径
     */
    public static  final String QENDIIMPT  = "/com/zihexin/business_interface/resources/icbc/QENDIIMPTRequest.xml";
    
    /**
     * 退款模板路径
     */
    public static  final String RETENDS  = "/com/zihexin/business_interface/resources/icbc/RETENDSRequest.xml";

    
    /**
     * 查询退款模板路径
     */
    public static  final String QRETENDS  = "/com/zihexin/business_interface/resources/icbc/QRETENDSRequest.xml";

    /**
     * 去掉XML头
     * @param xml
     * @return
     */
    public static String formatXML(String xml) {
        xml = xml.replaceAll("\n","");
        if(xml.indexOf("<?xml") != -1 && xml.indexOf("?>") != -1){
             xml = xml.substring(xml.indexOf("?>")+2);
        }
        return xml;
    }


    /**
     * BCD编码转换为字符串
     * @param bytes
     * @return
     */
    public static String bcd2Str(byte[] bytes)
    {
        char temp[] = new char[bytes.length*2], val;

        for(int i = 0; i < bytes.length; i++){
            val = (char)(((bytes[i]& 0xf0) >> 4)&0x0f);
            temp[i * 2] = (char)(val > 9 ? val + 'A' - 10 : val + '0');

            val = (char)(bytes[i]& 0x0f);
            temp[i * 2 + 1] = (char)(val > 9 ? val + 'A' - 10 : val + '0');
        }
        return new String(temp);
    }


    /**
     * 字符串编码转换为字节数组类型
     * @param str
     * @return
     * @throws NumberFormatException
     */
    public static byte[] hexStr2byte(String str)
            throws NumberFormatException{
        byte b[] = new byte[str.length() / 2];
        String item = null;
        for (int n = 0; n < str.length() / 2; n++)
        {
            item = str.substring(2 * n, 2 * n + 2);
            b[n] = (byte)Integer.parseInt(item, 16);
        }

        return b;
    }

  
    /**
     * 将字符串转换成二进制字符串
     * @param str
     * @return
     */
    public static String StrToBinstr(String str) {
        char[] strChar=str.toCharArray();
        String result="";
        for(int i=0;i<strChar.length;i++){
            result +=Integer.toBinaryString(strChar[i]);
        }
        return result;
    }


    /**
     * 将字符串转换为12位的金额
     * @param amt
     * @return
     */
    public static String StrToAmt(String amt) {

        char[] ary1 = amt.toCharArray();
        char[] ary2 = {'0','0','0','0','0','0','0','0','0','0','0','0'};
        System.arraycopy(ary1, 0, ary2, ary2.length-ary1.length, ary1.length);
        String result = new String(ary2);
        return result;
    }

    /**
     * 把多行xml处理为一行
     * @param xml
     * @return
     */
    public static String getSingleXml(String xml){
        xml = xml.replaceAll("\\n","");
        xml = xml.replaceAll(" ","");
        return xml;
    }


    /**
     * 定长字符串，长度不足的左补0
     * @param string
     * @param strLength
     * @return
     */
    public static String stringLeftZero(String string, int strLength) {
       int strLen = string.length();

        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(string);//左补0
                //   sb.append(str).append("0");//右补0
                string = sb.toString();
                strLen = string.length();
            }
        } 

        return string;
    }
    
    
    /**
     * 获取字符串的长度
     * @param str
     * @return
     */
    public static String getStrLength(String str){

        
        String string = null;
        try {
        	string = new String(str.getBytes("GBK"), "ISO8859_1");
        }
        catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
        }
        
       return String.valueOf(string.length());
       
    }
    
    /**
     * 定长字符串，长度不足的右补空格
     * @param string
     * @param strLength
     * @return
     */
    public static String stringRightNull(String string, int strLength) {
        int strLen = string.length();

        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append(string).append(" ");
                string = sb.toString();
                strLen = string.length();
            }
        }

        return string;
    }

    /**
     * 修改第二个字符为1
     * @param str
     * @return
     */
    public static String updateStr(String str){
    	 char [] ch  = str.toCharArray();
    	 ch[2] = '1';
    	 return String.valueOf(ch);
    }

}
