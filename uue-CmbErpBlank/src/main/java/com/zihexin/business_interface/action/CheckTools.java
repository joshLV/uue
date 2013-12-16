package com.zihexin.business_interface.action;


import com.zihexin.business_interface.domain.Command;
import com.zihexin.business_interface.domain.Field;
import com.zihexin.business_interface.exception.ErrorException;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: 12-4-16
 * Time: 下午1:28
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

                throw new ErrorException("业务字段[" + field.getFieldName() + "]长度超限:>" + field.getFieldMaxLength(), UN_RECORD_LOG);
            }
        }
        isPass = true;
        return isPass;
    }

    public boolean baseCheck(Command _command) throws ErrorException {
        boolean isPass = false;
        //指令存在校验
//        if (_command == null) {
//            throw new ErrorException("报文不包含合法的指令信息", UN_RECORD_LOG);
//        }
//
//        //字段数校验
//        if (_command.getCommandFieldsCnt() != fields.length) {
//            throw new ErrorException("报文字段数不匹配", UN_RECORD_LOG);
//        }
//
//        //KEY校验:来源系统与校验位校验
//
//        if (!KeyCheck.check(_command.getCommandSrc(), _command.getCommandCheck())) {
//            throw new ErrorException("未授权", RECORD_LOG);
//        }
//        isPass = true;
        return isPass;
    }
}
