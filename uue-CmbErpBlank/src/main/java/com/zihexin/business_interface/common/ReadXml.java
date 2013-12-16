/*
 * <p>Title:       �򵥵ı���</p>
 * <p>Description: ����ϸ��˵��</p>
 * <p>Copyright:   Copyright (c) 2004</p>
 * <p>Company:     ����ɷ����޹�˾</p>
 */
package com.zihexin.business_interface.common;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ReadXml {

    /**
     * ����xml
     * @param xmlHead
     * @param xmlBody
     * @return
     * @throws DocumentException
     */
    public static String getTransInfo(String xmlHead, String xmlBody) throws DocumentException {
        Document doc = DocumentHelper.parseText(xmlHead);
        Element parentElement = (Element) doc.getRootElement().selectSingleNode("/ap");
       // for (int i = 0; i < 5; i++) {
            Element ele1 = DocumentHelper.createElement(xmlBody);
            parentElement.add(ele1);
        //}
        String xml = doc.asXML().replaceAll("<<","<");
        xml = xml.replaceAll("/>/>","/>");
        xml = xml.replaceAll(">/>",">");
        return xml;
    }


    public static void main(String args[]) {
        try {
            //SAXReader reader = new SAXReader();
           // String path = Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(1);
            String xml = "<ap><CCTransCode>CQRA06</CCTransCode><ProductID>ICC</ProductID><ChannelType>ERP</ChannelType><CorpNo>6611501037955719</CorpNo><OpNo>0006</OpNo><AuthNo/><ReqSeqNo/><ReqDate/><ReqTime/></ap>";
            System.out.println(getTransInfo(xml,"<Sign/>"));
            //Document doc = reader.read(new File("src/com/zihexin/business_interface/resources/abc/AbcBankRequest.xml"));
            //Element root = doc.getRootElement();
            //System.out.println(ReadXml.class.getClassLoader().getResource("./").getPath()+"src/com/zihexin/business_interface/resources/abc/AbcBankRequest.xml");
            //getTransInfo2("s");



           /* Element el_root = doc.getRootElement();//����ȡ���ݣ���ȡxml�ĸ��ڵ㡣
            Iterator it = el_root.elementIterator();//�Ӹ��ڵ������α�������ȡ���ڵ��������ӽڵ�

            while(it.hasNext()){//�������ӽڵ�

                Object o = it.next();//�ٻ�ȡ���ӽڵ��µ��ӽڵ�
                Element el_row = (Element)o;
                String s = el_row.getText();
                 System.out.println(s);
                Iterator it_row = el_row.elementIterator();

                while(it_row.hasNext()){//�����ڵ�

                    Element el_ename = (Element)it_row.next();//��ȡ�ýڵ��µ��������ݡ�
                    System.out.println(el_ename.getText());
                }
                System.out.println(o);
            } */


    } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
