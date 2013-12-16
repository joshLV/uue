/*
 * <p>Title:       简单的标题</p>
 * <p>Description: 稍详细的说明</p>
 * <p>Copyright:   Copyright (c) 2004</p>
 * <p>Company:     软件股份有限公司</p>
 */
package com.zihexin.business_interface.common;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ReadXml {

    /**
     * 处理xml
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



           /* Element el_root = doc.getRootElement();//向外取数据，获取xml的根节点。
            Iterator it = el_root.elementIterator();//从根节点下依次遍历，获取根节点下所有子节点

            while(it.hasNext()){//遍历该子节点

                Object o = it.next();//再获取该子节点下的子节点
                Element el_row = (Element)o;
                String s = el_row.getText();
                 System.out.println(s);
                Iterator it_row = el_row.elementIterator();

                while(it_row.hasNext()){//遍历节点

                    Element el_ename = (Element)it_row.next();//获取该节点下的所有数据。
                    System.out.println(el_ename.getText());
                }
                System.out.println(o);
            } */


    } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
