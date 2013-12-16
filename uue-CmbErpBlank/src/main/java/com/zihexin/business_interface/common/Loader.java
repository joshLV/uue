package com.zihexin.business_interface.common;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.zihexin.business_interface.dao.ReadPropertiesDao;
import com.zihexin.business_interface.domain.Command;

public class Loader implements ApplicationContextAware {
    private final static Logger logger = LoggerFactory.getLogger(Loader.class);
    private static ApplicationContext context;//����һ����̬��������

    private static Hashtable commandTable;
    private static HashMap ITEMMAP;
    private static LinkedList bankCommandList;
    private static HashMap bankCommandMap = new HashMap();
    
    public void setApplicationContext(ApplicationContext contex)
            throws BeansException {
        this.context = contex;
       
        logger.info("����װ�ز�����ָ������");
        ReadPropertiesDao dao = (ReadPropertiesDao) context.getBean("readPropertiesDao");
        ITEMMAP = dao.getCommon();
        logger.info("ϵͳ����װ����ϣ�װ�ظ�����" + ITEMMAP.size());
        commandTable = dao.getCommandList();
        logger.info("ָ������װ����ϣ�װ�ظ�����" + commandTable.size());
        bankCommandList = dao.getBankCommandList("");
       logger.info("װ���������ò�����Ϣ��ϣ���ǰװ����Ϣ��:" + bankCommandList.size());
    }

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * ��ȡϵͳ����
     *
     * @param item ������
     * @return ����ֵ
     */
    public static String getItem(String item) {
        String split = (String) ITEMMAP.get(item);
        if ("|".equals(split)) {
            split = "\\|";
        }
        return split;
    }

    /**
     * ��ȡָ������
     *
     * @param commandId ָ��ID
     * @return ָ������
     */
    public static Command getCommandSet(String commandId) {
        return (Command) commandTable.get(commandId);
    }
    
    /**
     * ��ȡָ������
     *
     * @param commandId ָ��ID
     * @return ָ������
     */
    public static LinkedList getBankCommandList(String commandId) {
        return (LinkedList) bankCommandList;
    }
}
