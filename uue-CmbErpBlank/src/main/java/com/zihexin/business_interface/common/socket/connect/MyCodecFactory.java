package com.zihexin.business_interface.common.socket.connect;

import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * <style type="text/css">body{background:#C7EDCC;}</style>
 * �����̳���TextLineCodecFactory, ��ʾ��mina�����ݵĽ�����ʽ. ��������ֱ����TextLineCodecFactory��,
 * ��������Ŀ���Ǳ�ʾ���ǿ������Լ��ķ�ʽ������. MyCodecFactory.java
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
