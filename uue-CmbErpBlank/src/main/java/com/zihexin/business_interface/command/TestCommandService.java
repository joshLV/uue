package com.zihexin.business_interface.command;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zihexin.business_interface.bank.service.AbcBankService;
import com.zihexin.business_interface.common.Loader;
import com.zihexin.business_interface.domain.Command;
import com.zihexin.business_interface.domain.Field;
import com.zihexin.business_interface.domain.SendResult;

/**
 * 业务逻辑实现层
 * User: admin
 *
 */
public class TestCommandService extends CommandService {
    private final static Logger logger = LoggerFactory.getLogger(TestCommandService.class);

    //声明银行业务处理对象
    private AbcBankService abcBankService = (AbcBankService) Loader.getContext().getBean("abcBankService");

    private Field field = null;
    private Field fieldAccount = null;
    private Field fieldAmt = null;

    //处理结果
    private  SendResult sendResult = new SendResult();

    @Override
    public SendResult excute(Command command) throws Exception {

         //银行业务数据
        List businessParameter = command.getCommandBusinessParameter();

        try {

            //读取业务数据
            if(businessParameter.size() > 0) {
                field = (Field) businessParameter.get(0);
                logger.info("从数据库获取的银行编码：");
                logger.info(field.getFieldName() + ":");
                logger.info(field.getValue());

                //执行银行业务
               // if(BankCode.ABC.getBankCode().equals(field.getValue())){

                    //执行银行的业务
                    sendResult = abcBankService.excute(command);
                //}
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendResult.setCode("7");
            sendResult.setMsg("执行银行方操作中出现异常");
            return sendResult;
        }
        return sendResult;
    }

}
