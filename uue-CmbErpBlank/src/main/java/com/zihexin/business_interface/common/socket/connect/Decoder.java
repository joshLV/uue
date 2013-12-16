package com.zihexin.business_interface.common.socket.connect;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: 12-4-10
 * Time: ÏÂÎç2:05
 * To change this template use File | Settings | File Templates.
 */
public class Decoder extends CumulativeProtocolDecoder {
    private final static Logger logger = LoggerFactory.getLogger(Decoder.class);
    private final static String EOF = "</ap>";


    private boolean checkEOF(String msg) {
        if (EOF.equals(msg)) {
            return true;
        }
        int msgLength = msg.length();
        int EOFLength = EOF.length();
        if (msgLength < EOFLength) {
            return false;
        }
        String subString = msg.substring(msgLength - EOFLength, msgLength);
        if (EOF.equals(subString)) {
            return true;
        }
        return false;
    }


    @Override
    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
