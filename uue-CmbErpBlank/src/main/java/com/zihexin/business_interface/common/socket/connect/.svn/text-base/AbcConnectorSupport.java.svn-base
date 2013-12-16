package com.zihexin.business_interface.common.socket.connect;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.*;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioProcessor;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.net.InetSocketAddress;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 银行连接类.
 * User: Administrator
 *
 */
public class AbcConnectorSupport extends IoHandlerAdapter implements InitializingBean {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AbcConnectorSupport.class);

    /** 连接超时 */
    private int connectSoTimeOut = 30000;

    /** 接收线程池配置 */
    private  int poolsize = 3 ;//单线程执行，保证有3条备用线程即可。
    private ExecutorService recivePool;

    /** 处理线程池 */
    private int processorPoolSize = 0; //照顾future的使用，默认为0，短连接不应当设置该值。

    /** 过滤器链 */
    private Map<String,IoFilter> ioFilterMap = new LinkedHashMap<String,IoFilter>();

    /** 监听器 */
    private LinkedList<IoServiceListener> listenerList = new LinkedList<IoServiceListener>();

    /** 发呆方向设置 */
    private IdleStatus idleStatu = IdleStatus.BOTH_IDLE;

    /** 会话发呆时间 */
    private int idleTime = 30;

    /** 读取缓冲区大小 */
    private int readBufferSize = 1024;

    /** 回应写入超时 */
    private int writeTimeout = 10000;

    /** 远程地址 */
    private String host ;

    /** 端口号 */
    private Integer port ;

    private  InetSocketAddress remoteAddress;

    private DataAnalyze dataAnalyze;

    public AbcConnectorSupport(DataAnalyze dataAnalyze){
        this.dataAnalyze = dataAnalyze;
    }

    public void afterPropertiesSet() throws Exception {
        recivePool = Executors.newFixedThreadPool(poolsize);
    }
    public IoConnector runInit(){
        //初始化绑定地址
        Assert.notNull(this.host,"host is null");
        Assert.notNull(port,"port is null");

        remoteAddress = new InetSocketAddress(host,port);

        IoConnector connector = new NioSocketConnector (new NioProcessor(recivePool));
        connector.setHandler(this);
        connector.getSessionConfig().setReadBufferSize(readBufferSize); //读缓冲区
        connector.getSessionConfig().setWriteTimeout(writeTimeout);//写超时
        connector.setDefaultRemoteAddress(remoteAddress);
        connector.setConnectTimeoutMillis(connectSoTimeOut);

        //设置发呆时间，默认为双向。
        if (idleTime > 0){
            connector.getSessionConfig().setIdleTime( idleStatu, idleTime);
        }

        //处理线程池大小设置
        if(processorPoolSize != 0){
            connector.getFilterChain().addLast("processorPoolSize",
                    new ExecutorFilter(Executors.newFixedThreadPool(processorPoolSize)));
        }else{
            connector.getFilterChain().addLast("processorPoolSize",
                    new ExecutorFilter());//照顾future的使用，必须使用
        }

        //编解码过滤器，获得完整报文。
       // connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new MyCodecFactory()));
        //connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("gb2312"))));

        //编解码过滤器，获得完整报文。
       connector.getFilterChain().addLast(
                "protocolCodeFilter",
                new ProtocolCodecFilter(
                        new DefaultEncode(dataAnalyze),
                        new DefaultDecode(dataAnalyze)
                )
        );

        //加入过滤器连，如果存在。
        if(ioFilterMap != null && ioFilterMap.size() > 0){
            for (Map.Entry < String, IoFilter > ioFilterEntry : ioFilterMap.entrySet()) {
                connector.getFilterChain().addLast(ioFilterEntry.getKey(), ioFilterEntry.getValue());
            }
        }

        //加入监听器，如果存在。
        List<IoServiceListener> listeners = listenerList;
        if(listeners != null && listeners.size() > 0){
            for (IoServiceListener ioServiceListener : listeners) {
                connector.addListener(ioServiceListener);
            }
        }
        return connector;
    }
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.info("远程连接已创建..."+session.getRemoteAddress());
    }
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logger.info("远程连接已断开...");
    }
    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        logger.warn("与"+session.getRemoteAddress()+"通信过程中出现错误....连接即将关闭....",cause);
        session.close(true);
    }
    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        logger.warn("会话闲置超过最大等待时间...即将关闭连接..");
        session.close(true);
    }

    private static class DefaultDecode extends  ProtocolDecoderAdapter{
        private DataAnalyze dataAnalyze;
        private DefaultDecode(DataAnalyze dataAnalyze){
            this.dataAnalyze = dataAnalyze;
        }

        public void decode(IoSession session, IoBuffer buffer,
                           ProtocolDecoderOutput out) throws Exception {
            out.write(dataAnalyze.decode(buffer));
        }
    }

    private static class DefaultEncode extends ProtocolEncoderAdapter{
        private  DataAnalyze dataAnalyze;
        private DefaultEncode(DataAnalyze dataAnalyze){
            this.dataAnalyze = dataAnalyze;
        }

        public void encode(IoSession session, Object message,
                           ProtocolEncoderOutput out) throws Exception {
            out.write(dataAnalyze.encode(message));
        }
    }

    public int getPoolsize() {
        return poolsize;
    }

    public void setPoolsize(int poolsize) {
        this.poolsize = poolsize;
    }

    public int getProcessorPoolSize() {
        return processorPoolSize;
    }

    public void setProcessorPoolSize(int processorPoolSize) {
        this.processorPoolSize = processorPoolSize;
    }

    public Map<String, IoFilter> getIoFilterMap() {
        return ioFilterMap;
    }

    public void setIoFilterMap(Map<String, IoFilter> ioFilterMap) {
        this.ioFilterMap = ioFilterMap;
    }

    public LinkedList<IoServiceListener> getListenerList() {
        return listenerList;
    }

    public void setListenerList(LinkedList<IoServiceListener> listenerList) {
        this.listenerList = listenerList;
    }

    public IdleStatus getIdleStatu() {
        return idleStatu;
    }

    public void setIdleStatu(IdleStatus idleStatu) {
        this.idleStatu = idleStatu;
    }

    public int getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(int idleTime) {
        this.idleTime = idleTime;
    }

    public int getReadBufferSize() {
        return readBufferSize;
    }

    public void setReadBufferSize(int readBufferSize) {
        this.readBufferSize = readBufferSize;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(InetSocketAddress remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public DataAnalyze getDataAnalyze() {
        return dataAnalyze;
    }

    public void setDataAnalyze(DataAnalyze dataAnalyze) {
        this.dataAnalyze = dataAnalyze;
    }

    public int getConnectSoTimeOut() {
        return connectSoTimeOut;
    }

    public void setConnectSoTimeOut(int connectSoTimeOut) {
        this.connectSoTimeOut = connectSoTimeOut;
    }
}
