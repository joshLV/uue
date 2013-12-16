package com.zihexin.business_interface.action;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {
    private final static Logger logger = LoggerFactory.getLogger(InitListener.class);
//    public static String ROOT_NAME = "";
//    public static String INTERFACE_PATH = "";

    public void contextDestroyed(ServletContextEvent arg0) {
    }

    public void contextInitialized(ServletContextEvent sce) {
        new ClassPathXmlApplicationContext("applicationContext-minaServer.xml");
//        ROOT_NAME = ReadXml.getCommonRootName();
//        INTERFACE_PATH = ReadXml.getCommonIfNamePath();
//        logger.info("**��ʼ�� root_name:" + ROOT_NAME);
//        logger.info("**��ʼ�� interface_path" + INTERFACE_PATH);

        // ��tomcat������������,�ῴ������̨��ӡ�����.
        logger.info("********mina server starting over*********");
    }
}
