package com.zihexin.business_interface.common;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * ������
 * User: Administrator

 */
public class Utils {
    public  static  void main(String args[]){
        //System.out.println(Utils.strLength(2300));
    }


    /**
     * JAVA��ָ��λ�ÿ�ʼ��ȡָ�����ȵ��ַ���
     *
     * @param input �����ַ���
     * @param index ��ȡλ�ã�����һ���ַ�����ֵ��1
     * @param count ��ȡ����
     * @return ��ȡ�ַ���
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
     * ϵͳ������Դ
     */
    public static final String SOURCESYSCODE = "sys001";

    /**
     * �ַ�����
     */
    public static final  String CHARSET_FORNAME  = "gb2312";

    /**
     * ���� �磺1:�����,2:Ӣ��,3:��Ԫ
     */
    public static final String  DBCUR = "1";



    /**
     * ���н��״���
     */
    public static final String command_ENDIIMPT = "ENDIIMPT";

    /**
     * ���С��� ����ͷΪ1���ֽڵ��Ƿ�Ϊ���ܰ���־������6���ֽڵ��ַ���ʾ���ݰ��ĳ��ȣ�������Ȳ���6λ���ұ��ÿո��㣬���磺��1234  ����
     */
    public static final String ABCBANK_BODYMARK = "0";



    /**
     * �����ڲ�ϵͳ�ı��ķָ��־
     */
    public static final String SYS_DELIMITER = "|";


    /**
     * ǩԼ����ģ��·��
     */

    public static  final String ENDIIMPT  = "/com/zihexin/business_interface/resources/icbc/ENDIIMPTRequest.xml";

    /**
     * �ۿ�����ģ��·��
     */
    public static  final String PERDIS  = "/com/zihexin/business_interface/resources/icbc/PERDISRequest.xml";
    
    /**
     * �ۿ��ѯ����ģ��·��
     */
    public static  final String QPERDIS  = "/com/zihexin/business_interface/resources/icbc/QPERDISRequest.xml";
    
    /**
     * ǩԼ����ģ��·��
     */
    public static  final String QENDIIMPT  = "/com/zihexin/business_interface/resources/icbc/QENDIIMPTRequest.xml";
    
    /**
     * �˿�ģ��·��
     */
    public static  final String RETENDS  = "/com/zihexin/business_interface/resources/icbc/RETENDSRequest.xml";

    
    /**
     * ��ѯ�˿�ģ��·��
     */
    public static  final String QRETENDS  = "/com/zihexin/business_interface/resources/icbc/QRETENDSRequest.xml";

    /**
     * ȥ��XMLͷ
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
     * BCD����ת��Ϊ�ַ���
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
     * �ַ�������ת��Ϊ�ֽ���������
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
     * ���ַ���ת���ɶ������ַ���
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
     * ���ַ���ת��Ϊ12λ�Ľ��
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
     * �Ѷ���xml����Ϊһ��
     * @param xml
     * @return
     */
    public static String getSingleXml(String xml){
        xml = xml.replaceAll("\\n","");
        xml = xml.replaceAll(" ","");
        return xml;
    }


    /**
     * �����ַ��������Ȳ������0
     * @param string
     * @param strLength
     * @return
     */
    public static String stringLeftZero(String string, int strLength) {
       int strLen = string.length();

        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(string);//��0
                //   sb.append(str).append("0");//�Ҳ�0
                string = sb.toString();
                strLen = string.length();
            }
        } 

        return string;
    }
    
    
    /**
     * ��ȡ�ַ����ĳ���
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
     * �����ַ��������Ȳ�����Ҳ��ո�
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
     * �޸ĵڶ����ַ�Ϊ1
     * @param str
     * @return
     */
    public static String updateStr(String str){
    	 char [] ch  = str.toCharArray();
    	 ch[2] = '1';
    	 return String.valueOf(ch);
    }

}
