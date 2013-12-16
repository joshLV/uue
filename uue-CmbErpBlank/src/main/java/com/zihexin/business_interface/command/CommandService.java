package com.zihexin.business_interface.command;

import com.zihexin.business_interface.domain.Command;
import com.zihexin.business_interface.domain.SendResult;

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: 12-4-16
 * Time: ионГ10:28
 * To change this template use File | Settings | File Templates.
 */
abstract class CommandService {
    abstract SendResult excute(Command command) throws Exception;
}
