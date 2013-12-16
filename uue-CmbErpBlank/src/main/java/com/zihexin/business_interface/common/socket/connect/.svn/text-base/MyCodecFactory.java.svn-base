package com.zihexin.business_interface.common.socket.connect;

import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * <style type="text/css">body{background:#C7EDCC;}</style>
 * 这个类继承自TextLineCodecFactory, 表示对mina的数据的解析方式. 本来可以直接用TextLineCodecFactory的,
 * 但这个类的目的是表示我们可以有自己的方式来解析. MyCodecFactory.java
 *
 * @author yj
 */
public class MyCodecFactory extends TextLineCodecFactory {          // implements ProtocolCodecFactory {//
    private final static Logger logger = LoggerFactory.getLogger(MyCodecFactory.class);

    public MyCodecFactory() {
        //super(Charset.forName("UTF-8"));
        super(Charset.forName("GBK"));


    }

}
