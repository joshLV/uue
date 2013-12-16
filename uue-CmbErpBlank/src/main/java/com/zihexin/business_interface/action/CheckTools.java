package com.zihexin.business_interface.action;


import com.zihexin.business_interface.domain.Command;
import com.zihexin.business_interface.domain.Field;
import com.zihexin.business_interface.exception.ErrorException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: 12-4-16
 * Time: ����1:28
 * To change this template use File | Settings | File Templates.
 */
public class CheckTools {
    private final static boolean RECORD_LOG = true;
    private final static boolean UN_RECORD_LOG = false;

    public boolean businessFieldCheck(Command _command) throws ErrorException {
        boolean isPass = false;
        List fieldsList = _command.getCommandBusinessParameter();
        for (int i = 0; i < fieldsList.size(); i++) {
            Field field = (Field) fieldsList.get(i);
            int currentLength = field.getValue().length();
            if (currentLength > field.getFieldMaxLength()) {

                throw new ErrorException("ҵ���ֶ�[" + field.getFieldName() + "]���ȳ���:>" + field.getFieldMaxLength(), UN_RECORD_LOG);
            }
        }
        isPass = true;
        return isPass;
    }

    public boolean baseCheck(Command _command) throws ErrorException {
        boolean isPass = false;
        //ָ�����У��
//        if (_command == null) {
//            throw new ErrorException("���Ĳ������Ϸ���ָ����Ϣ", UN_RECORD_LOG);
//        }
//
//        //�ֶ���У��
//        if (_command.getCommandFieldsCnt() != fields.length) {
//            throw new ErrorException("�����ֶ�����ƥ��", UN_RECORD_LOG);
//        }
//
//        //KEYУ��:��Դϵͳ��У��λУ��
//
//        if (!KeyCheck.check(_command.getCommandSrc(), _command.getCommandCheck())) {
//            throw new ErrorException("δ��Ȩ", RECORD_LOG);
//        }
//        isPass = true;
        return isPass;
    }
}
