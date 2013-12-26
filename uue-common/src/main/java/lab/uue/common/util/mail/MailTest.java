/**
 * Project Name:common-service
 * File Name:MailTest.java
 * Package Name:lab.s2jh.common.util.mail
 * Date:2013-12-26上午9:47:22
 * Copyright (c) 2013, Unistrong All Rights Reserved.
 *
*/

package lab.uue.common.util.mail;
/**
 * ClassName:MailTest <br/>
 * Reason:	 发送邮件测试类. <br/>
 * Date:     2013-12-26 上午9:47:22 <br/>
 * @author   邢凌霄
 * @version  1.0
 * @since    JDK 1.6
 * @see 	 
 */
public class MailTest
{
    public static void main(String[] args){   
        //这个类主要是设置邮件   
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setMailServerHost("smtp.163.com");    
     mailInfo.setMailServerPort("25");    
     mailInfo.setValidate(true);    
     mailInfo.setUserName("smiles_man@163.com");    
     mailInfo.setPassword("xinglingxiao521+");//您的邮箱密码    
     mailInfo.setFromAddress("smiles_man@163.com");    
     mailInfo.setToAddress("xlx@hkcx.com.cn");    
     mailInfo.setSubject("设置邮箱标题 如http://www.guihua.org 中国桂花网");    
     mailInfo.setContent("设置邮箱内容 如http://www.guihua.org 中国桂花网 是中国最大桂花网站==");    
        //这个类主要来发送邮件   
     SimpleMailSender sms = new SimpleMailSender();   
         sms.sendTextMail(mailInfo);//发送文体格式    
         sms.sendHtmlMail(mailInfo);//发送html格式   
   }  
}

