/**
 * Project Name:common-service
 * File Name:MyAuthenticator.java
 * Package Name:lab.s2jh.common.util.mail
 * Date:2013-12-26上午9:17:02
 * Copyright (c) 2013, Unistrong All Rights Reserved.
 *
*/

package lab.uue.common.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * ClassName:MyAuthenticator <br/>
 * Description: 邮件身份验证. <br/>
 * Date:     2013-12-26 上午9:17:02 <br/>
 * @author   邢凌霄
 * @version  1.0
 * @since    JDK 1.6
 * @see 	 
 */
public class MyAuthenticator extends Authenticator
{
    String userName=null;   
    String password=null;   
        
    public MyAuthenticator(){   
    }   
    public MyAuthenticator(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }    
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }   
}

