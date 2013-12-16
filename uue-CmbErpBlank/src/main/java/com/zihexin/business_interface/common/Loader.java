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
    private static ApplicationContext context;//声明一个静态变量保存

    private static Hashtable commandTable;
    private static HashMap ITEMMAP;
    private static LinkedList bankCommandList;
    private static HashMap bankCommandMap = new HashMap();
    
    public void setApplicationContext(ApplicationContext contex)
            throws BeansException {
        this.context = contex;
       
        logger.info("正在装载参数及指令配置");
        ReadPropertiesDao dao = (ReadPropertiesDao) context.getBean("readPropertiesDao");
        ITEMMAP = dao.getCommon();
        logger.info("系统参数装载完毕，装载个数：" + ITEMMAP.size());
        commandTable = dao.getCommandList();
        logger.info("指令配置装载完毕，装载个数：" + commandTable.size());
        bankCommandList = dao.getBankCommandList("");
       logger.info("装载银行配置参数信息完毕，当前装载信息数:" + bankCommandList.size());
    }

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 获取系统参数
     *
     * @param item 参数项
     * @return 参数值
     */
    public static String getItem(String item) {
        String split = (String) ITEMMAP.get(item);
        if ("|".equals(split)) {
            split = "\\|";
        }
        return split;
    }

    /**
     * 获取指令配置
     *
     * @param commandId 指令ID
     * @return 指令配置
     */
    public static Command getCommandSet(String commandId) {
        return (Command) commandTable.get(commandId);
    }
    
    /**
     * 获取指令配置
     *
     * @param commandId 指令ID
     * @return 指令配置
     */
    public static LinkedList getBankCommandList(String commandId) {
        return (LinkedList) bankCommandList;
    }
}
