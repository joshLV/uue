package com.zihexin.business_interface.common.socket.connect;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * 编解码策略
 * User: Administrator
 * Date: 12-4-25
 * Time: 上午8:50
 * To change this template use File | Settings | File Templates.
 */

public interface DataAnalyze {
    /**
     * 解码策略
     * @param ioBuffer
     * @return
     */
    public Object decode(IoBuffer ioBuffer);
    /**
     * 解码策略
     * @param
     * @return
     */
    public IoBuffer encode(Object message);
}

