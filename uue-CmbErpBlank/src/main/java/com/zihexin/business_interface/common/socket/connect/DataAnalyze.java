package com.zihexin.business_interface.common.socket.connect;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * ��������
 * User: Administrator
 * Date: 12-4-25
 * Time: ����8:50
 * To change this template use File | Settings | File Templates.
 */

public interface DataAnalyze {
    /**
     * �������
     * @param ioBuffer
     * @return
     */
    public Object decode(IoBuffer ioBuffer);
    /**
     * �������
     * @param
     * @return
     */
    public IoBuffer encode(Object message);
}

