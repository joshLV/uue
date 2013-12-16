package com.zihexin.wtc.common;

import com.zihexin.wtc.dao.WTCReadPropertiesDao;
import com.zihexin.wtc.domain.Command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Hashtable;

public class Loader implements ApplicationContextAware {
    private final static Logger logger = LoggerFactory.getLogger(Loader.class);
    private static ApplicationContext context;//����һ����̬��������

    private static Hashtable commandTable;
    private static HashMap ITEMMAP;

    public void setApplicationContext(ApplicationContext contex)
            throws BeansException {
        this.context = contex;
//        logger.info("�ӿ�����װ����ϣ���ǰװ��ָ����:" + commandTable.size());
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
}
