package com.zihexin.business_interface.domain;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: 12-4-12
 * Time: 下午5:15
 * To change this template use File | Settings | File Templates.
 */
public class Command {
    /**
     * 指令ID
     */
    private String commandId;

    /**
     * 指令名称
     */
    private String commandName;

    /**
     * 指令字段数
     */
    private Long commandFieldsCnt;

    /**
     * 指令状态
     */
    private String commandStatus;

    /**
     * 源系统
     */
    private String commandSrc;

    /**
     * 校验位
     */
    private String commandCheck;

    /**
     * 业务数据字段配置
     */
    private List commandBusinessParameter;

    /**
     * 其它数据字段配置
     */
    private List<Field> commandOtherParameter;

    /**
     * 指令日志表
     */
    private String commandLog;

    /**
     * 报文信息
     */
    private Message message;

    /**
     * 内部系统请求时间
     */
    private Date requestDt;

    /**
     * 银行响应时间
     */
    private Date responseDt;

    /**
     * 最终处理状态
     */
    private String finalStatus;

    /**
     * 指令处理类
     */
    private String commandClass;
    
    private String bodyType;
    
    
    private String src_water_num;
    

    public String getCommandClass() {
        return commandClass;
    }

    public void setCommandClass(String commandClass) {
        this.commandClass = commandClass;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getCommandLog() {
        return commandLog;
    }

    public void setCommandLog(String commandLog) {
        this.commandLog = commandLog;
    }

    public Date getRequestDt() {
        return requestDt;
    }

    public void setRequestDt(Date requestDt) {
        this.requestDt = requestDt;
    }

    public Date getResponseDt() {
        return responseDt;
    }

    public void setResponseDt(Date responseDt) {
        this.responseDt = responseDt;
    }

    public String getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
    }

    public String getCommondLog() {
        return commandLog;
    }

    public void setCommondLog(String commandLog) {
        this.commandLog = commandLog;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getCommandSrc() {
        return commandSrc;
    }

    public void setCommandSrc(String commandSrc) {
        this.commandSrc = commandSrc;
    }

    public String getCommandCheck() {
        return commandCheck;
    }

    public void setCommandCheck(String commandCheck) {
        this.commandCheck = commandCheck;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public Long getCommandFieldsCnt() {
        return commandFieldsCnt;
    }

    public void setCommandFieldsCnt(Long commandFieldsCnt) {
        this.commandFieldsCnt = commandFieldsCnt;
    }

    public String getCommandStatus() {
        return commandStatus;
    }

    public void setCommandStatus(String commandStatus) {
        this.commandStatus = commandStatus;
    }

    public List getCommandBusinessParameter() {
        return commandBusinessParameter;
    }

    public void setCommandBusinessParameter(List commandBusinessParameter) {
        this.commandBusinessParameter = commandBusinessParameter;
    }

    public List getCommandOtherParameter() {
        return commandOtherParameter;
    }

    public void setCommandOtherParameter(List commandOtherParameter) {
        this.commandOtherParameter = commandOtherParameter;
    }

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public String getSrc_water_num() {
		return src_water_num;
	}

	public void setSrc_water_num(String srcWaterNum) {
		src_water_num = srcWaterNum;
	}
    
    
}
