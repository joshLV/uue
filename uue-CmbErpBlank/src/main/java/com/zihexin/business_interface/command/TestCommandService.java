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
 * ҵ���߼�ʵ�ֲ�
 * User: admin
 *
 */
public class TestCommandService extends CommandService {
    private final static Logger logger = LoggerFactory.getLogger(TestCommandService.class);

    //��������ҵ�������
    private AbcBankService abcBankService = (AbcBankService) Loader.getContext().getBean("abcBankService");

    private Field field = null;
    private Field fieldAccount = null;
    private Field fieldAmt = null;

    //������
    private  SendResult sendResult = new SendResult();

    @Override
    public SendResult excute(Command command) throws Exception {

         //����ҵ������
        List businessParameter = command.getCommandBusinessParameter();

        try {

            //��ȡҵ������
            if(businessParameter.size() > 0) {
                field = (Field) businessParameter.get(0);
                logger.info("�����ݿ��ȡ�����б��룺");
                logger.info(field.getFieldName() + ":");
                logger.info(field.getValue());

                //ִ������ҵ��
               // if(BankCode.ABC.getBankCode().equals(field.getValue())){

                    //ִ�����е�ҵ��
                    sendResult = abcBankService.excute(command);
                //}
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendResult.setCode("7");
            sendResult.setMsg("ִ�����з������г����쳣");
            return sendResult;
        }
        return sendResult;
    }

}
