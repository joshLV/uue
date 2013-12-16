package com.zihexin.business_interface.bank.service;

import com.zihexin.business_interface.domain.Command;
import com.zihexin.business_interface.domain.Field;
import com.zihexin.business_interface.domain.SendResult;

/**
 * 银行执行业务接口类
 * User: Administrator
 *
 */
public interface AbcBankService {

    /**
     * 银行执行业务处理
     * @param command
     * @return
     */
    public SendResult excute(Command command);
}
